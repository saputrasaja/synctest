'use strict';

angular.module('synctestApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


