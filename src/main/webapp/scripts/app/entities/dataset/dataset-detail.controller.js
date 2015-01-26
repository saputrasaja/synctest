'use strict';

angular.module('synctestApp')
    .controller('DatasetDetailController', function ($scope, $stateParams, Dataset) {
        $scope.dataset = {};
        $scope.load = function (id) {
            Dataset.get({id: id}, function(result) {
              $scope.dataset = result;
            });
        };
        $scope.load($stateParams.id);
    });
