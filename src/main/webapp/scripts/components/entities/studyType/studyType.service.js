'use strict';

angular.module('synctestApp')
    .factory('StudyTypeOC', function ($resource) {
        return $resource('api/oc/studyTypes', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('StudyType', function ($resource) {
        return $resource('api/studyTypes/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.date_start = new Date(data.date_start);
                    data.date_end = new Date(data.date_end);
                    return data;
                }
            },
            'saveMany': { method: 'PUT', isArray: true}
        });
    });
