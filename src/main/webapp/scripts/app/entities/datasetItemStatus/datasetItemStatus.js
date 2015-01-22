'use strict';

angular.module('synctestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('datasetItemStatus', {
                parent: 'entity',
                url: '/datasetItemStatus',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/datasetItemStatus/datasetItemStatuss.html',
                        controller: 'DatasetItemStatusController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('datasetItemStatus');
                        return $translate.refresh();
                    }]
                }
            })
            .state('datasetItemStatusDetail', {
                parent: 'entity',
                url: '/datasetItemStatus/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/datasetItemStatus/datasetItemStatus-detail.html',
                        controller: 'DatasetItemStatusDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('datasetItemStatus');
                        return $translate.refresh();
                    }]
                }
            });
    });
