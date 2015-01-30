'use strict';

angular.module('synctestApp')
    .controller('StudyEventController', function ($scope, $rootScope, StudyEvent, StudyEventOC) {
        console.log('rootScope : ', $rootScope);
        var syncService = StudyEvent;
        var ocService = StudyEventOC;
        
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

        $scope.downloadICSFile = function() {
            window.open('/api/oc/icsStudyEvents');
            // var cal = ics();
            // _.each($scope.tableData[0], function(se)
            //     {
            //         cal.addEvent(se.name, se.label, se.location, se.date_start, se.date_end);
            //     });
            // cal.download('Study Event');

        };

        $scope.create = function () {
            StudyEvent.save($scope.studyEvent,
                function () {
                    $scope.loadAll();
                    $('#saveStudyEventModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.studyEvent = StudyEvent.get({id: id});
            $('#saveStudyEventModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.studyEvent = StudyEvent.get({id: id});
            $('#deleteStudyEventConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            StudyEvent.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteStudyEventConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.studyEvent = {name: null, label: null, event_status: null, status: null, date_start: null, date_end: null, owner_id: null, id: null};
        };
    });
