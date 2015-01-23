'use strict';

angular.module('synctestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('userAccount', {
                parent: 'entity',
                url: '/userAccount',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/userAccount/userAccounts.html',
                        controller: 'UserAccountController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('userAccount');
                        return $translate.refresh();
                    }]
                }
            })
            .state('userAccountDetail', {
                parent: 'entity',
                url: '/userAccount/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/userAccount/userAccount-detail.html',
                        controller: 'UserAccountDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('userAccount');
                        return $translate.refresh();
                    }]
                }
            });
    });
