myApp.factory('EventsFactory', ['APIRoutes', function(APIRoutes) {
  var eventFactory = {};

  // get all data from database
  eventFactory.getEventAll = function() {
    var promise = APIRoutes.apiCall(6, "", {}).then(function(response) {
        return response.data;
      },
      function(response) {
        return response.data;
      });
    return promise;
  };

  eventFactory.getEvent = function(event_title) {
		var promise = APIRoutes.apiCall(7, event_title, {}).then(function(response) {
          return response.data;
        },
        function(response) {
          return response.data;
        });
    return promise;
  };

  eventFactory.addEvent = function(event) {
		var promise = APIRoutes.apiCall(0, "", event).then(function(response) {
        return response.data;
      },
      function(response) {
        return response.data;
      });
    return promise;
  };

  eventFactory.updateEvent = function(event_title, event) {
		var promise = APIRoutes.apiCall(12, event_title, event).then(function(response) {
          return "Updated";
        },
        function(response) {
          return response.statusText + ' ' + response.status + ' ' + response.data;
        });

    return promise;
  };

  eventFactory.deleteEvent = function(event_title) {
    var promise = APIRoutes.apiCall(15, event_title, {}).then(function(response) {
				return response.statusText + ' ' + response.status + ' ' + response.data;;
      },
      function(response) {
        return response.statusText + ' ' + response.status + ' ' + response.data;;
      });
    return promise;
  };

  return eventFactory;
}]);
