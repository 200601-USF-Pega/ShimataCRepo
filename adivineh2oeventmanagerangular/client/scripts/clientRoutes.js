var myApp = angular.module('myApp', ['ngRoute']);

myApp.config(['$routeProvider', function($routeProvider) {

  //routes
    $routeProvider
        .when ('/home', {
          templateUrl: '/views/home.html',
          controller: 'HomeController',
          controllerAs: 'home'
        })
        .when ('/events', {
            templateUrl: '/views/events.html',
            controller: 'EventsController',
            controllerAs: 'events'
        })
        .when ('/people', {
            templateUrl: '/views/people.html',
            controller: 'PeopleController',
            controllerAs: 'people'
        })
				.when ('/login', {
					templateUrl: '/views/login.html',
					controller: 'LoginController',
					controllerAs: 'login'
				})
				.when ('/register', {
          templateUrl: '/views/register.html',
          controller: 'RegisterController',
          controllerAs: 'register'
        })
				.when ('/event', {
						templateUrl: '/views/event.html',
						controller: 'EventController',
						controllerAs: 'eventview'
				})
				.when ('/event/:event_title', {
            templateUrl: '/views/event.html',
            controller: 'EventController',
            controllerAs: 'eventview'
        })
				.when ('/person/:person_auto_id', {
            templateUrl: '/views/person.html',
            controller: 'PersonController',
            controllerAs: 'person'
        })
				.when ('/supplies/person/:person_auto_id', {
            templateUrl: '/views/supplies.html',
            controller: 'SuppliesController',
            controllerAs: 'supplies'
        })
				.when ('/supplies/event/:event_title', {
						templateUrl: '/views/supplies.html',
						controller: 'SuppliesController',
						controllerAs: 'supplies'
				})
				.when ('/supply/:supply_auto_id', {
						templateUrl: '/views/supply.html',
						controller: 'SupplyController',
						controllerAs: 'supply'
				})
				.when ('messagethreads/:person_auto_id', {
						templateUrl: '/views/messagethreads.html',
						controller: 'MessageThreadsController',
						controllerAs: 'message_threads'
				})
				.when ('messagethread/:title', {
						templateUrl: '/views/messagethread.html',
						controller: 'MessageThreadController',
						controllerAs: 'message_thread'
				})
        .otherwise ( {
            redirectTo: '/home'
        });
}]);
