'use strict';

angular.module('synctestApp')
    .factory('StudyOC', function ($resource) {
        return $resource('api/oc/studys', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('Study', function ($resource) {
        return $resource('api/studys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.date_planned_start = new Date(data.date_planned_start);
                    data.date_planned_end = new Date(data.date_planned_end);
                    data.date_created = new Date(data.date_created);
                    data.date_updated = new Date(data.date_updated);
                    data.protocol_date_verification = new Date(data.protocol_date_verification);
                    return data;
                }
            },
            'saveMany': { method: 'PUT', isArray: true}
        });
    });
