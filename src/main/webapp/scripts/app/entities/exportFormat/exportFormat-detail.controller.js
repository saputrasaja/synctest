'use strict';

angular.module('synctestApp')
    .controller('ExportFormatDetailController', function ($scope, $stateParams, ExportFormat) {
        $scope.exportFormat = {};
        $scope.load = function (id) {
            ExportFormat.get({id: id}, function(result) {
              $scope.exportFormat = result;
            });
        };
        $scope.load($stateParams.id);
    });
