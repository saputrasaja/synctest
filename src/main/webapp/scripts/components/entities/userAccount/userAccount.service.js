'use strict';

angular.module('synctestApp')
    .factory('UserAccountOC', function ($resource) {
        return $resource('api/oc/userAccounts', {}, {
            'query': { method: 'GET', isArray: true}
        });
    })
    .factory('UserAccount', function ($resource) {
        return $resource('api/userAccounts/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    data.date_created = new Date(data.date_created);
                    data.date_updated = new Date(data.date_updated);
                    data.date_lastvisit = new Date(data.date_lastvisit);
                    data.passwd_timestamp = new Date(data.passwd_timestamp);
                    return data;
                }
            },
            'saveMany': { method: 'PUT', isArray: true}
        });
    });
