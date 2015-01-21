'use strict';

angular.module('synctestApp')
    .controller('OcConfigurationController', function ($scope, ConfSyncService, ConfOcService) {

        $scope.configurations = [];

        $scope.titleTable = [];
        $scope.tableData = [];
        $scope.diffData = [];
        $scope.tableState = [];
        $scope.toggleText = [];

        $scope.loadAll = function() {
            ConfSyncService.query(function(r1) {
                $scope.configurations[0] = r1;

                ConfOcService.query(function(r2) {
                    $scope.configurations[1] = r2;

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
            $scope.tableData[tableNumber] = _.filter($scope.configurations[tableNumber], function(sync)
            {
                var finded = _.find($scope.configurations[tnReverse], function(oc)
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
                $scope.tableData[i] = $scope.configurations[i];
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
                ocConfs : $scope.diffData[1],
                confs:  $scope.diffData[0]
            };
            ConfSyncService.saveMany(data, 
                function(){
                    $scope.loadAll();
                    $scope.clear();
                    $('#syncConfirmation').modal('hide');
                });
        };

        $scope.create = function () {
            ConfSyncService.save($scope.ocConfiguration,
                function () {
                    $scope.loadAll();
                    $('#saveOcConfigurationModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.ocConfiguration = ConfSyncService.get({id: id});
            $('#saveOcConfigurationModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.ocConfiguration = ConfSyncService.get({id: id});
            $('#deleteOcConfigurationConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            ConfSyncService.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteOcConfigurationConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.ocConfiguration = {key: null, value: null, description: null, version: null, id: null};
        };
    });
