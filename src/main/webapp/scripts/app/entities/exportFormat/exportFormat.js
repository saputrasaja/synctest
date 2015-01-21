'use strict';

angular.module('synctestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('exportFormat', {
                parent: 'entity',
                url: '/exportFormat',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/exportFormat/exportFormats.html',
                        controller: 'ExportFormatController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('exportFormat');
                        return $translate.refresh();
                    }]
                }
            })
            .state('exportFormatDetail', {
                parent: 'entity',
                url: '/exportFormat/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/exportFormat/exportFormat-detail.html',
                        controller: 'ExportFormatDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('exportFormat');
                        return $translate.refresh();
                    }]
                }
            });
    });
