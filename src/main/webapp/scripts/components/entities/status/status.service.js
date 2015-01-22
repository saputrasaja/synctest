'use strict';

angular.module('synctestApp')
    .factory('SocSercice', function ($resource) {
        return $resource('api/oc/status', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('SsyncSercice', function ($resource) {
        return $resource('api/statuss/:id', {}, {
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
