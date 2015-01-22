'use strict';

angular.module('synctestApp')
    .controller('StatusController', function ($scope, SsyncSercice, SocSercice) {
        $scope.statuss = [];

        $scope.titleTable = [];
        $scope.tableData = [];
        $scope.diffData = [];
        $scope.tableState = [];
        $scope.toggleText = [];

        $scope.loadAll = function() {
            SsyncSercice.query(function(r1) {
                $scope.statuss[0] = r1;

                SocSercice.query(function(r2) {
                    $scope.statuss[1] = r2;

                    $scope.titleTable[0] = "Not on openclinica";
                    $scope.titleTable[1] = "Not on synctest";

                    $scope.tableState[0] = true;
                    $scope.tableState[1] = true;

                    $scope.diffData[0] = generateTableData(0);
                    $scope.diffData[1] = generateTableData(1);

                    $scope.toggleText[0] = 'Show All';
                    $scope.toggleText[1] = 'Show All';

                });
            });
        };

        function generateTableData(tableNumber)
        {
            var tnReverse = tableNumber == 1 ? 0 : 1;
            $scope.tableData[tableNumber] = _.filter($scope.statuss[tableNumber], function(sync)
            {
                var finded = _.find($scope.statuss[tnReverse], function(oc)
                {
                    return oc.id === sync.id;
                });
                return !finded;

            });
            return $scope.tableData[tableNumber];
        };

        $scope.loadAll();

        $scope.toggleTable = function (i) {
            if ($scope.tableState[i]){

                $scope.titleTable[i] = 'All row on ' + (i === 0 ? 'synctest' : 'openclinica');
                $scope.tableData[i] = $scope.statuss[i];
            } else {
                $scope.titleTable[i] = 'Not on ' + (i === 1 ? 'synctest' : 'openclinica');
                generateTableData(i);
            }
            $scope.tableState[i] = !$scope.tableState[i];

            $scope.toggleText[i] = 'Show ' + ($scope.tableState[i] ? 'All' : 'Diff' );
        };

        $scope.preSync = function() {
            $('#syncConfirmation').modal('show');
        };

        $scope.doSync = function() {
            var data = 
            {
                s : $scope.diffData[1],
                sOc:  $scope.diffData[0]
            };
            SsyncSercice.saveMany(data, 
                function(){
                    $scope.loadAll();
                    $scope.clear();
                    $('#syncConfirmation').modal('hide');
                });
        };

        $scope.create = function () {
            SsyncSercice.save($scope.status,
                function () {
                    $scope.loadAll();
                    $('#saveStatusModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.status = SsyncSercice.get({id: id});
            $('#saveStatusModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.status = SsyncSercice.get({id: id});
            $('#deleteStatusConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            SsyncSercice.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteStatusConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.status = {name: null, description: null, id: null};
        };
    });
