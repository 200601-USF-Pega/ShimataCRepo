myApp.factory('EventFactory',['APIRoutes', '$http',function($http, APIRoutes) {

	var event = function(title){

		this.initialize = function () {

			var url = '34.236.181.231/router/event' + title + '?callback=JSON_CALLBACK';
			var data = someObject
			var eventData = $http.get(url);
			var self = this;

			$addEvent = function() {
				return $http.post(serviceBase + 'updateCustomer', {id:id, customer:customer}).then(function (status) {
					return status.data;
				});
			}

			eventData.then(function(response){
				angular.extend(self, response.data);
			});
		}

		this.likeScore = function(){
			return this.likes_received_count - this.likes_count;
		}

		this.commentScore = function(){
			return this.comments_received_count - this.comments_count;
		}

		// Call the initialize function for every new instance
		this.initialize();
	}

	// Return a reference to the function
	return (event);
}]);
