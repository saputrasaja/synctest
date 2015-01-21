'use strict';

angular.module('synctestApp')
    .controller('OcConfigurationController', function ($scope, syncConfiguration, clinicaConfiguration) {
        $scope.confFromSync = [];
        $scope.confFromOc =[];
        $scope.tableOneConfs = [];
        $scope.tableTwoConfs = [];
        $scope.notOnOc = null;
        $scope.notOnSync = null;

        var tableTwoState = true;
        var tableOneState = true;

        $scope.titleTableOne = "Not on openclinica";
        $scope.titleTableTwo = "Not on synctest";

        $scope.loadAll = function() {
            syncConfiguration.query(function(r1) {
                $scope.confFromSync = r1;

                clinicaConfiguration.query(function(r2) {
                    $scope.confFromOc = r2;
                    
                    tableTwoState = true;
                    tableOneState = true;

                    $scope.notOnOc = generateTableOneConf();
                    $scope.notOnSync = generateTableTwoConf();
                    
                    $scope.titleTableOne = "Not on openclinica";
                    $scope.titleTableTwo = "Not on synctest";

                });
            });
        };

        function generateTableOneConf()
        {
            $scope.tableOneConfs = _.filter($scope.confFromSync, function(sync)
            {
                var finded = _.find($scope.confFromOc, function(oc)
                {
                    return oc.key === sync.key;
                });
                return !finded;

            });
            return $scope.tableOneConfs;
        };

        function generateTableTwoConf()
        {
            $scope.tableTwoConfs = _.filter($scope.confFromOc, function(oc)
            {
                var finded = _.find($scope.confFromSync, function(sync)
                {
                    return oc.key === sync.key;
                });
                return !finded;
            });
            return $scope.tableTwoConfs;
        };

        $scope.loadAll();

        $scope.preSync = function() {
            $('#syncConfirmation').modal('show');
        };

        $scope.doSync = function() {
            var data = 
            {
                confs : $scope.notOnOc,
                ocConfs:  $scope.notOnSync
            };
            syncConfiguration.saveMany(data, 
                function(){
                    $scope.loadAll();
                    $scope.clear();
                    $('#syncConfirmation').modal('hide');
                });
        };

        $scope.create = function () {
            syncConfiguration.save($scope.ocConfiguration,
                function () {
                    $scope.loadAll();
                    $('#saveOcConfigurationModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.ocConfiguration = syncConfiguration.get({id: id});
            $('#saveOcConfigurationModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.ocConfiguration = syncConfiguration.get({id: id});
            $('#deleteOcConfigurationConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            syncConfiguration.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteOcConfigurationConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.ocConfiguration = {key: null, value: null, description: null, version: null, id: null};
        };

        $scope.toggleTableOne = function () {
            if (tableOneState){
                $scope.titleTableOne = "All row on synctest";
                $scope.tableOneConfs = $scope.confFromSync;
                
            } else {
                $scope.titleTableOne = "Not on openclinica";
                generateTableOneConf();
            }
            tableOneState = !tableOneState;
        };
        
        $scope.toggleTableTwo = function () {
            if (tableTwoState){
                $scope.titleTableTwo = "All row on openclinica";
                $scope.tableTwoConfs = $scope.confFromOc;
            } else {
                $scope.titleTableTwo = "Not on synctest";
                generateTableTwoConf();
            }
            tableTwoState = !tableTwoState;
        };
    });
