'use strict';

angular.module('synctestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('phoneBook', {
                parent: 'entity',
                url: '/phoneBook',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/phoneBook/phoneBooks.html',
                        controller: 'PhoneBookController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('phoneBook');
                        return $translate.refresh();
                    }]
                }
            })
            .state('phoneBookDetail', {
                parent: 'entity',
                url: '/phoneBook/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/phoneBook/phoneBook-detail.html',
                        controller: 'PhoneBookDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('phoneBook');
                        return $translate.refresh();
                    }]
                }
            });
    });
