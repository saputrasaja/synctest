'use strict';

angular.module('synctestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('dataset', {
                parent: 'entity',
                url: '/dataset',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/dataset/datasets.html',
                        controller: 'DatasetController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('dataset');
                        return $translate.refresh();
                    }]
                }
            })
            .state('datasetDetail', {
                parent: 'entity',
                url: '/dataset/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/dataset/dataset-detail.html',
                        controller: 'DatasetDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('dataset');
                        return $translate.refresh();
                    }]
                }
            });
    });
