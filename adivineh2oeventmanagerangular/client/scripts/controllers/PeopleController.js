myApp.controller('PeopleController',['DataFactory', '$window', '$scope', function(DataFactory, $window, $scope) {

  console.log('People controller running');
  var self = this;
  this.testMessage = 'This is the view-2 test message';

	$scope.logout=function() {
		$window.localStorage.removeItem('sessionUser');
		console.log("hit the logout");
		console.log($window.localStorage.getItem('sessionUser'))
		$window.location.href = '/#!/home';
	};
	if ($window.localStorage.getItem('sessionUser') == null) {
		$window.location.href = '/#!/home';
		console.log('user is null')
	}else{
		console.log($window.localStorage.getItem('sessionUser'))
	}

}]);
