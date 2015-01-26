'use strict';

angular.module('synctestApp')
    .controller('StudyController', function ($scope, Study, StudyOC) {
        var syncService = Study;
        var ocService = StudyOC;
        
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
            Study.save($scope.study,
                function () {
                    $scope.loadAll();
                    $('#saveStudyModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.study = Study.get({id: id});
            $('#saveStudyModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.study = Study.get({id: id});
            $('#deleteStudyConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Study.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteStudyConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.study = {parent_study_id: null, unique_identifier: null, secondary_identifier: null, name: null, summary: null, date_planned_start: null, date_planned_end: null, date_created: null, date_updated: null, owner_id: null, update_id: null, type_id: null, status_id: null, principal_investigator: null, facility_name: null, facility_city: null, facility_state: null, facility_zip: null, facility_country: null, facility_recruitment_status: null, facility_contact_name: null, facility_contact_degree: null, facility_contact_phone: null, facility_contact_email: null, protocol_type: null, protocol_description: null, protocol_date_verification: null, phase: null, expected_total_enrollment: null, sponsor: null, collaborators: null, medline_identifier: null, url: null, url_description: null, conditions: null, keywords: null, eligibility: null, gender: null, age_max: null, age_min: null, healthy_volunteer_accepted: null, purpose: null, allocation: null, masking: null, control: null, assignment: null, endpoint: null, interventions: null, duration: null, selection: null, timing: null, official_title: null, results_reference: null, oc_oid: null, old_status_id: null, id: null};
        };
    });
