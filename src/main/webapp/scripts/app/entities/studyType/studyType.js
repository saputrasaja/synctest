'use strict';

angular.module('synctestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('studyType', {
                parent: 'entity',
                url: '/studyType',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/studyType/studyTypes.html',
                        controller: 'StudyTypeController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('studyType');
                        return $translate.refresh();
                    }]
                }
            })
            .state('studyTypeDetail', {
                parent: 'entity',
                url: '/studyType/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/studyType/studyType-detail.html',
                        controller: 'StudyTypeDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('studyType');
                        return $translate.refresh();
                    }]
                }
            });
    });
