'use strict';

angular.module('synctestApp')
    .factory('EFocSercice', function ($resource) {
        return $resource('api/oc/exportformat', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('EFsyncService', function ($resource) {
        return $resource('api/exportFormats/:id', {}, {
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
