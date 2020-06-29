myApp.controller('LoginController', ['$scope', '$window',
	function($scope, $window){

		$scope.login=function() {
			$window.localStorage.setItem('sessionUser', 'casey');
			$window.location.href = '/#!/events';
		};
	}
]);
