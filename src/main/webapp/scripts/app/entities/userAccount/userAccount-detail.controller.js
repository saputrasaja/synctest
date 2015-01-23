'use strict';

angular.module('synctestApp')
    .controller('UserAccountDetailController', function ($scope, $stateParams, UserAccount) {
        $scope.userAccount = {};
        $scope.load = function (id) {
            UserAccount.get({id: id}, function(result) {
              $scope.userAccount = result;
            });
        };
        $scope.load($stateParams.id);
    });
