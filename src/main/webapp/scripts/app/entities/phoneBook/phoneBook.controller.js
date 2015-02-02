'use strict';

angular.module('synctestApp')
    .controller('PhoneBookController', function ($scope, PhoneBook) {
        $scope.phoneBooks = [];
        $scope.loadAll = function() {
            PhoneBook.query(function(result) {
               $scope.phoneBooks = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            PhoneBook.save($scope.phoneBook,
                function () {
                    $scope.loadAll();
                    $('#savePhoneBookModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.phoneBook = PhoneBook.get({id: id});
            $('#savePhoneBookModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.phoneBook = PhoneBook.get({id: id});
            $('#deletePhoneBookConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            PhoneBook.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deletePhoneBookConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.phoneBook = {name: null, phone_number: null, address: null, email: null, id: null};
        };
    });
