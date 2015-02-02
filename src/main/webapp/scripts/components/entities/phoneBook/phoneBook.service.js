'use strict';

angular.module('synctestApp')
    .factory('PhoneBook', function ($resource) {
        return $resource('api/phoneBooks/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            }
        });
    });
