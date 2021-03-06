'use strict';

angular.module('synctestApp')
    .controller('UserTypeDetailController', function ($scope, $stateParams, UserType) {
        $scope.userType = {};
        $scope.load = function (id) {
            UserType.get({id: id}, function(result) {
              $scope.userType = result;
            });
        };
        $scope.load($stateParams.id);
    });
