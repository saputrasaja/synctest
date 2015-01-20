'use strict';

angular.module('synctestApp')
    .controller('OcConfigurationDetailController', function ($scope, $stateParams, syncConfiguration) {
        $scope.ocConfiguration = {};
        $scope.load = function (id) {
            syncConfiguration.get({id: id}, function(result) {
              $scope.ocConfiguration = result;
            });
        };
        $scope.load($stateParams.id);
    });
