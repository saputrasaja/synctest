'use strict';

angular.module('synctestApp')
    .controller('PhoneBookDetailController', function ($scope, $stateParams, PhoneBook) {
        $scope.phoneBook = {};
        $scope.load = function (id) {
            PhoneBook.get({id: id}, function(result) {
              $scope.phoneBook = result;
            });
        };
        $scope.load($stateParams.id);
    });
