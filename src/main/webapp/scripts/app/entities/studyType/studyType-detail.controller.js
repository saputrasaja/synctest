'use strict';

angular.module('synctestApp')
    .controller('StudyTypeDetailController', function ($scope, $stateParams, StudyType) {
        $scope.studyType = {};
        $scope.load = function (id) {
            StudyType.get({id: id}, function(result) {
              $scope.studyType = result;
            });
        };
        $scope.load($stateParams.id);
    });
