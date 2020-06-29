myApp.controller('EventsController',['EventsFactory', '$window', '$scope', '$location', function(EventsFactory, $window, $scope, $location) {

var self = this;
self.message = "";

var getEvents = EventsFactory.getEventAll().then(function(response){
    self.events = response;
});


//redirect if user not in session
if ($window.localStorage.getItem('sessionUser') == null) {
	$window.location.href = '/#!/home';
};
//logout
$scope.logout=function() {
	$window.localStorage.removeItem('sessionUser');
	$window.location.href = '/#!/home';
};

$scope.addEvent=function() {
	$location.path('/event/');
};

$scope.deleteEvent=function(event_title) {
		EventsFactory.deleteEvent(event_title).then(function(response){
		    self.message = response;
				getEvents;
		});

};

$scope.viewEvent=function(event_title) {
	$location.path('/event/:' + event_title);
};







}]);
