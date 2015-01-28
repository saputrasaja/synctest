'use strict';

angular.module('synctestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('studyEvent', {
                parent: 'entity',
                url: '/studyEvent',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/studyEvent/studyEvents.html',
                        controller: 'StudyEventController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('studyEvent');
                        return $translate.refresh();
                    }]
                }
            })
            .state('studyEventDetail', {
                parent: 'entity',
                url: '/studyEvent/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/studyEvent/studyEvent-detail.html',
                        controller: 'StudyEventDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('studyEvent');
                        return $translate.refresh();
                    }]
                }
            });
    });
