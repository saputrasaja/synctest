'use strict';

angular.module('synctestApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
