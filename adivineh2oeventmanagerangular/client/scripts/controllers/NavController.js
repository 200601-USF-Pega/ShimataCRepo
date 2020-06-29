myApp.controller('NavController',['DataFactory', '$window', '$scope', function(DataFactory, $window, $scope) {

  console.log('nav controller running');
  var self = this;

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
