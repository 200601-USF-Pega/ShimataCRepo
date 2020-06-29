myApp.controller('EventController',['EventsFactory', '$window', '$scope', '$location', function(EventsFactory, $window, $scope, $location) {

var self = this;
self.message = "";
self.event_title = $window.location.hash.split(':')[1].replace('%20', ' ');


$scope.eventview = {
	'title' : 'hello',
	'address_number' : '',
	'street_name' : '',
	'city' : '',
	'state' : '',
	'zip' : '',
	'number_helpers_needed' : '',
	'info_url' : '',
	'event_image_url' : ''
};

var getEvent = EventsFactory.getEvent(self.event_title).then(function(response){
    $scope.eventview = response;
});

$scope.viewAddSupplyToEvent=function() {
	$location.path('/event/');
};
$scope.viewAddPersonToEvent=function() {
	$location.path('/event/');
};

$scope.viewEventMessageThread=function() {
	$location.path('/event/:' + self.event_title);
};

$scope.addOrUpdateEvent=function() {
	if (self.event_title != null) {
		EventsFactory.updateEvent(self.event_title, $scope.eventview).then(function(response){
				self.message = response;
				getEvent;
		});
	}else {
		console.log('THE BODY' + $scope.eventview);
		EventsFactory.addEvent($scope.eventview).then(function(response){
		    self.message = response;
				getEvent;
		});
	}
};


// $scope.deleteEvent=function(event_title) {
// 		EventsFactory.deleteEvent(event_title).then(function(response){
// 		    self.message = response;
// 				getEvents;
// 		});
// };


//redirect if user not in session
if ($window.localStorage.getItem('sessionUser') == null) {
	$window.location.href = '/#!/home';
};
//logout
$scope.logout=function() {
	$window.localStorage.removeItem('sessionUser');
	$window.location.href = '/#!/home';
};


}]);
