'use strict';

angular.module('synctestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('study', {
                parent: 'entity',
                url: '/study',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/study/studys.html',
                        controller: 'StudyController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('study');
                        return $translate.refresh();
                    }]
                }
            })
            .state('studyDetail', {
                parent: 'entity',
                url: '/study/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/study/study-detail.html',
                        controller: 'StudyDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('study');
                        return $translate.refresh();
                    }]
                }
            });
    });
