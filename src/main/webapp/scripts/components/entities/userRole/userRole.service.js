'use strict';

angular.module('synctestApp')
    .factory('UserRoleOC', function ($resource) {
        return $resource('api/oc/userRoles', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('UserRole', function ($resource) {
        return $resource('api/userRoles/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'saveMany': { method: 'PUT', isArray: true}
        });
    });
