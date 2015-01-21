'use strict';

angular.module('synctestApp')
    .factory('ConfOcService', function ($resource) {
        return $resource('api/oc/configurations', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('ConfSyncService', function ($resource) {
        return $resource('api/ocConfigurations/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': 
            {
                method: 'GET',
                transformResponse: function (data) 
                {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'saveMany': { method: 'PUT', isArray: true}
        });
    });
