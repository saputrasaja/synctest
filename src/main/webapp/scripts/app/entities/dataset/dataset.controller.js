'use strict';

angular.module('synctestApp')
    .controller('DatasetController', function ($scope, Dataset, DatasetOC) {
        var syncService = Dataset;
        var ocService = DatasetOC;
        
        $scope.datas = [];

        $scope.titleTable = [];
        $scope.tableData = [];
        $scope.diffData = [];
        $scope.tableState = [];
        $scope.toggleText = [];

        $scope.array = [0,1]

        $scope.loadAll = function() {
            syncService.query(function(r1) {
                $scope.datas[0] = r1;

                ocService.query(function(r2) {
                    $scope.datas[1] = r2;

                    $scope.titleTable[0] = "Not on openclinica";
                    $scope.titleTable[1] = "Not on synctest";

                    $scope.tableState[0] = true;
                    $scope.tableState[1] = true;

                    $scope.diffData[0] = generateTableData(0);
                    $scope.diffData[1] = generateTableData(1);

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
            Dataset.save($scope.dataset,
                function () {
                    $scope.loadAll();
                    $('#saveDatasetModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.dataset = Dataset.get({id: id});
            $('#saveDatasetModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.dataset = Dataset.get({id: id});
            $('#deleteDatasetConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Dataset.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteDatasetConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.dataset = {study_id: null, status_id: null, name: null, description: null, sql_statement: null, num_runs: null, date_start: null, date_end: null, date_created: null, date_updated: null, date_last_run: null, owner_id: null, approver_id: null, update_id: null, show_event_location: null, show_event_start: null, show_event_end: null, show_subject_dob: null, show_subject_gender: null, show_event_status: null, show_subject_status: null, show_subject_unique_id: null, show_subject_age_at_event: null, show_crf_status: null, show_crf_version: null, show_crf_int_name: null, show_crf_int_date: null, show_group_info: null, show_disc_info: null, odm_metadataversion_name: null, odm_metadataversion_oid: null, odm_prior_study_oid: null, odm_prior_metadataversion_oid: null, show_secondary_id: null, dataset_item_status_id: null, id: null};
        };
    });
