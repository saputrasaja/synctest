'use strict';

angular.module('synctestApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('userRole', {
                parent: 'entity',
                url: '/userRole',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/userRole/userRoles.html',
                        controller: 'UserRoleController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('userRole');
                        return $translate.refresh();
                    }]
                }
            })
            .state('userRoleDetail', {
                parent: 'entity',
                url: '/userRole/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/userRole/userRole-detail.html',
                        controller: 'UserRoleDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('userRole');
                        return $translate.refresh();
                    }]
                }
            });
    });
