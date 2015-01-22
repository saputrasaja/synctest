'use strict';

angular.module('synctestApp')
    .controller('DatasetItemStatusDetailController', function ($scope, $stateParams, DatasetItemStatus) {
        $scope.datasetItemStatus = {};
        $scope.load = function (id) {
            DatasetItemStatus.get({id: id}, function(result) {
              $scope.datasetItemStatus = result;
            });
        };
        $scope.load($stateParams.id);
    });
