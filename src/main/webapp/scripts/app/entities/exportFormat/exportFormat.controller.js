'use strict';

angular.module('synctestApp')
    .controller('ExportFormatController', function ($scope, ExportFormat, ExportFormatOC) {

        $scope.exportFormats = [];
        $scope.exportFormatsOC = [];

        $scope.tableOneDatas = [];
        $scope.tableTwoDatas = [];

        $scope.notOnOc = [];
        $scope.notOnSync = [];

        var tableTwoState = true;
        var tableOneState = true;

        $scope.titleTableOne = "Not on openclinica";
        $scope.titleTableTwo = "Not on synctest";

        $scope.loadAll = function() {
            ExportFormat.query(function(r1) {
               $scope.exportFormats = r1;

                ExportFormatOC.query(function(r2) {
                   $scope.exportFormatsOC = r2;

                    $scope.titleTableOne = "Not on openclinica";
                    $scope.titleTableTwo = "Not on synctest";

                    tableTwoState = true;
                    tableOneState = true;

                    $scope.notOnOc = generateTableOneEF();
                    $scope.notOnSync = generateTableTwoEF();
                });
            });
        };
        $scope.loadAll();

        function generateTableOneEF()
        {

            $scope.tableOneDatas = _.filter($scope.exportFormats, function(sync)
            {
                var finded = _.find($scope.exportFormatsOC, function(oc)
                {
                    return oc.id === sync.id;
                });
                return !finded;

            });
            return $scope.tableOneDatas;
        };

        function generateTableTwoEF()
        {
            $scope.tableTwoDatas = _.filter($scope.exportFormatsOC, function(sync)
            {
                var finded = _.find($scope.exportFormats, function(oc)
                {
                    return oc.id === sync.id;
                });
                return !finded;

            });
            return $scope.tableTwoDatas;
        };

        $scope.toggleTableOne = function () {
            if (tableOneState){
                $scope.titleTableOne = "All row on synctest";
                $scope.tableOneDatas = $scope.exportFormats;
            } else {
                $scope.titleTableOne = "Not on openclinica";
                generateTableOneEF();
            }
            tableOneState = !tableOneState;
        };

        $scope.toggleTableTwo = function () {
            if (tableTwoState){
                $scope.titleTableTwo = "All row on openclinica";
                $scope.tableTwoDatas = $scope.exportFormatsOC;
            } else {
                $scope.titleTableTwo = "Not on synctest";
                generateTableTwoEF();
            }
            tableTwoState = !tableTwoState;
        };

        $scope.preSync = function() {
            $('#syncConfirmation').modal('show');
        };

        $scope.doSync = function() {
            var data = 
            {
                ef : $scope.notOnSync,
                efoc:  $scope.notOnOc
            };
            ExportFormat.saveMany(data, 
                function(){
                    $scope.loadAll();
                    $scope.clear();
                    $('#syncConfirmation').modal('hide');
                });
        };

        $scope.create = function () {
            ExportFormat.save($scope.exportFormat,
                function () {
                    $scope.loadAll();
                    $('#saveExportFormatModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.exportFormat = ExportFormat.get({id: id});
            $('#saveExportFormatModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.exportFormat = ExportFormat.get({id: id});
            $('#deleteExportFormatConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            ExportFormat.delete({id: id},
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
