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
                    return data;
                }
            },
            'saveMany': { method: 'PUT', isArray: true}
        });
    });
