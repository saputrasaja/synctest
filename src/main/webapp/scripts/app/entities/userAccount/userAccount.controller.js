'use strict';

angular.module('synctestApp')
    .controller('UserAccountController', function ($scope, UserAccount, UserAccountOC) {
        var syncService = UserAccount;
        var ocService = UserAccountOC;
        
        $scope.datas = [];

        $scope.titleTable = [];
        $scope.tableData = [];
        $scope.diffData = [];
        $scope.tableState = [];
        $scope.toggleText = [];

        $scope.loadAll = function() {
            syncService.query(function(r1) {
                $scope.datas[0] = r1;

                ocService.query(function(r2) {
                    $scope.datas[1] = r2;

                    console.log('datas : ', $scope.datas);

                    $scope.titleTable[0] = "Not on openclinica";
                    $scope.titleTable[1] = "Not on synctest";

                    $scope.tableState[0] = true;
                    $scope.tableState[1] = true;

                    $scope.diffData[0] = generateTableData(0);
                    $scope.diffData[1] = generateTableData(1);

                    console.log('$scope.tableData : ', $scope.tableData);

                    $scope.toggleText[0] = 'Show All';
                    $scope.toggleText[1] = 'Show All';

                });
            });
        };

        function generateTableData(tableNumber)
        {
            var tnReverse = tableNumber == 1 ? 0 : 1;
            $scope.tableData[tableNumber] = _.filter($scope.datas[tableNumber], function(sync)
            {
                var finded = _.find($scope.datas[tnReverse], function(oc)
                {
                    return oc.id === sync.id;
                });
                return !finded;

            });
            return $scope.tableData[tableNumber];
        };

        $scope.loadAll();

        $scope.toggleTable = function (i) {
            if ($scope.tableState[i]){

                $scope.titleTable[i] = 'All row on ' + (i === 0 ? 'synctest' : 'openclinica');
                $scope.tableData[i] = $scope.datas[i];
            } else {
                $scope.titleTable[i] = 'Not on ' + (i === 1 ? 'synctest' : 'openclinica');
                generateTableData(i);
            }
            $scope.tableState[i] = !$scope.tableState[i];

            $scope.toggleText[i] = 'Show ' + ($scope.tableState[i] ? 'All' : 'Diff' );
        };

        $scope.preSync = function() {
            $('#syncConfirmation').modal('show');
        };

        $scope.doSync = function() {
            var data = 
            {
                data : $scope.diffData[1],
                dataOc:  $scope.diffData[0]
            };
            syncService.saveMany(data, 
                function(){
                    $scope.loadAll();
                    $scope.clear();
                    $('#syncConfirmation').modal('hide');
                });
        };

        $scope.create = function () {
            UserAccount.save($scope.userAccount,
                function () {
                    $scope.loadAll();
                    $('#saveUserAccountModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.userAccount = UserAccount.get({id: id});
            $('#saveUserAccountModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.userAccount = UserAccount.get({id: id});
            $('#deleteUserAccountConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            UserAccount.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteUserAccountConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.userAccount = {user_name: null, passwd: null, first_name: null, last_name: null, email: null, active_study: null, institutional_affiliation: null, status_id: null, owner_id: null, date_created: null, date_updated: null, date_lastvisit: null, passwd_timestamp: null, passwd_challenge_question: null, passwd_challenge_answer: null, phone: null, user_type_id: null, update_id: null, enabled: null, account_non_locked: null, lock_counter: null, run_webservices: null, id: null};
        };
    });
