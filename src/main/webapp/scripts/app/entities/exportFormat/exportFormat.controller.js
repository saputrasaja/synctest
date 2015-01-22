'use strict';

angular.module('synctestApp')
    .controller('ExportFormatController', function ($scope, ExportFormat, ExpertFormatOC) {
        var EFsyncService = ExportFormat;

        $scope.exportFormats = [];

        $scope.titleTable = [];
        $scope.tableData = [];
        $scope.diffData = [];
        $scope.tableState = [];
        $scope.toggleText = [];

        $scope.loadAll = function() {
            EFsyncService.query(function(r1) {
               $scope.exportFormats[0] = r1;

                ExpertFormatOC.query(function(r2) {
                   $scope.exportFormats[1] = r2;
                   console.log('$scope.exportFormats : ', $scope.exportFormats);

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
            $scope.tableData[tableNumber] = _.filter($scope.exportFormats[tableNumber], function(sync)
            {
                var finded = _.find($scope.exportFormats[tnReverse], function(oc)
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
                $scope.tableData[i] = $scope.exportFormats[i];
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
                ef : $scope.diffData[1],
                efoc:  $scope.diffData[0]
            };
            EFsyncService.saveMany(data, 
                function(){
                    $scope.loadAll();
                    $scope.clear();
                    $('#syncConfirmation').modal('hide');
                });
        };

        $scope.create = function () {
            EFsyncService.save($scope.exportFormat,
                function () {
                    $scope.loadAll();
                    $('#saveExportFormatModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.exportFormat = EFsyncService.get({id: id});
            $('#saveExportFormatModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.exportFormat = EFsyncService.get({id: id});
            $('#deleteExportFormatConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            EFsyncService.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteExportFormatConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.exportFormat = {name: null, description: null, mime_type: null, id: null};
        };
    });
