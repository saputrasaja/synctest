'use strict';

angular.module('synctestApp')
    .controller('OcConfigurationController', function ($scope, OcConfiguration, $location, $rootScope) {
        $scope.confFromSync = [];
        $scope.confFromOc =[];
        $scope.tableOneConfs = [];
        $scope.tableTwoConfs = [];
        $scope.notOnOc = null;
        $scope.notOnSync = null;

        $scope.loadAll = function() {
            $.ajax({
                url: "http://" + $location.host() + ":" + $rootScope.ocPort + "/api/oc/configuration"
            }).done(function(result) {
                $scope.confFromOc = result;
                OcConfiguration.query(function(result) {
                    $scope.confFromSync = result;

                    $scope.notOnOc = generateTableOneConf();
                    $scope.notOnSync = generateTableTwoConf();
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
            console.log($scope.notOnSync);
            console.log($scope.notOnOc);
            OcConfiguration.saveMany($scope.notOnSync, 
                function(){
                    $scope.loadAll();
                    $scope.clear();
                    $('#syncConfirmation').modal('hide');
                });
        };

        $scope.create = function () {
            OcConfiguration.save($scope.ocConfiguration,
                function () {
                    $scope.loadAll();
                    $('#saveOcConfigurationModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.ocConfiguration = OcConfiguration.get({id: id});
            $('#saveOcConfigurationModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.ocConfiguration = OcConfiguration.get({id: id});
            $('#deleteOcConfigurationConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            OcConfiguration.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteOcConfigurationConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.ocConfiguration = {key: null, value: null, description: null, version: null, id: null};
        };

        $scope.titleTableOne = "Not on openclinica";
        var tableOneState = true;
        $scope.toggleTableOne = function () {
            var title;
            if (tableOneState){
                $scope.titleTableOne = "All row on synctest";
                $scope.tableOneConfs = $scope.confFromSync;
                
            } else {
                $scope.titleTableOne = "Not on openclinica";
                generateTableOneConf();
            }
            tableOneState = !tableOneState;
        };

        $scope.titleTableTwo = "Not on synctest";
        var tableTwoState = true;
        $scope.toggleTableTwo = function () {
            var title;
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
