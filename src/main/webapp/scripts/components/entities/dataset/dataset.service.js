'use strict';

angular.module('synctestApp')
    .factory('DatasetOC', function ($resource) {
        return $resource('api/oc/datasets', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('Dataset', function ($resource) {
        return $resource('api/datasets/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.date_start = new Date(data.date_start);
                    data.date_end = new Date(data.date_end);
                    data.date_created = new Date(data.date_created);
                    data.date_updated = new Date(data.date_updated);
                    data.date_last_run = new Date(data.date_last_run);
                    return data;
                }
            },
            'saveMany': { method: 'PUT', isArray: true}
        });
    });
