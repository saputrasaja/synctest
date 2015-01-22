'use strict';

angular.module('synctestApp')
    .factory('UserTypeOC', function ($resource) {
        return $resource('api/oc/userTypes', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('UserType', function ($resource) {
        return $resource('api/userTypes/:id', {}, {
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
