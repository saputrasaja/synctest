'use strict';

angular.module('synctestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('ocConfiguration', {
                parent: 'entity',
                url: '/ocConfiguration',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ocConfiguration/ocConfigurations.html',
                        controller: 'OcConfigurationController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ocConfiguration');
                        return $translate.refresh();
                    }]
                }
            })
            .state('ocConfigurationDetail', {
                parent: 'entity',
                url: '/ocConfiguration/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ocConfiguration/ocConfiguration-detail.html',
                        controller: 'OcConfigurationDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ocConfiguration');
                        return $translate.refresh();
                    }]
                }
            });
    });
