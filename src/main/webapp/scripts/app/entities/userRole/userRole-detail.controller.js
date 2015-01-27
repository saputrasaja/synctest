'use strict';

angular.module('synctestApp')
    .controller('UserRoleDetailController', function ($scope, $stateParams, UserRole) {
        $scope.userRole = {};
        $scope.load = function (id) {
            UserRole.get({id: id}, function(result) {
              $scope.userRole = result;
            });
        };
        $scope.load($stateParams.id);
    });
