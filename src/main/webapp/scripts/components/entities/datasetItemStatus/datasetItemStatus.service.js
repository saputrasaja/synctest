'use strict';

angular.module('synctestApp')
    .factory('DatasetItemStatusOC', function ($resource) {
        return $resource('api/oc/status', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('DatasetItemStatus', function ($resource) {
        return $resource('api/datasetItemStatuss/:id', {}, {
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
