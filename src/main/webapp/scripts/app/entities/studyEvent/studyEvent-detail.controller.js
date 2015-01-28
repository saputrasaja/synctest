'use strict';

angular.module('synctestApp')
    .controller('StudyEventDetailController', function ($scope, $stateParams, StudyEvent) {
        $scope.studyEvent = {};
        $scope.load = function (id) {
            StudyEvent.get({id: id}, function(result) {
              $scope.studyEvent = result;
            });
        };
        $scope.load($stateParams.id);
    });
