myApp.controller('EventsController',['EventsFactory', '$window', '$scope', '$location', function(EventsFactory, $window, $scope, $location) {

var self = this;

self.message = "";

EventsFactory.GetEventAll().then(function(response){
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

};

$scope.deleteEvent=function(event_title) {
	EventsFactory.deleteEvent().then(function(response){
	    self.message = response;
	});

};
$scope.viewEvent=function(event_title) {
	$location.path('/event/:' + event_title);
};





}]);
