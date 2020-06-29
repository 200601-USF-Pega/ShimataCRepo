myApp.factory('PeopleFactory', ['APIRoutes', function(APIRoutes) {
  var eventFactory = {};

  // get all data from database
  eventFactory.GetEventAll = function() {
    var promise = APIRoutes.apiCall(6, "", {}).then(function(response) {
        return response.data;
      },
      function(response) {
        return response.data;
      });
    return promise;
  };


  // get single record from database
  eventFactory.GetEvent = function(id) {

    var promise = $http({
        method: 'GET',
        url: '/api/PersonalDetails/' + id
      })
      .then(function(response) {
          return response.data;
        },
        function(response) {
          return response.data;
        });
    return promise;
  };


  // post the data from database
  eventFactory.Insert = function(firstName, lastName, age, active) {
    var personalDetail = {
      FirstName: firstName,
      LastName: lastName,
      Age: age,
      Active: active,
    };

    var promise = $http({
        method: 'POST',
        url: '/api/PersonalDetails',
        data: personalDetail
      })
      .then(function(response) {
          return response.statusText;
        },
        function(response) {
          return response.statusText;
        });

    return promise;
  };

  // put the data from database
  eventFactory.Update = function(autoId, firstName, lastName, age, active) {
    var personalDetail = {
      AutoId: autoId,
      FirstName: firstName,
      LastName: lastName,
      Age: age,
      Active: active,
    };

    var promise = $http({
        method: 'PUT',
        url: '/api/PersonalDetails/' + autoId,
        data: personalDetail
      })
      .then(function(response) {
          return "Updated";
          // return response.statusText + ' ' + response.status + ' ' + response.data;
        },
        function(response) {
          return response.statusText + ' ' + response.status + ' ' + response.data;
        });

    return promise;
  };

  // get all data from database
  eventFactory.deleteEvent = function(event_title) {
    var promise = APIRoutes.apiCall(15, event_title, {}).then(function(response) {
				return response.statusText + ' ' + response.status + ' ' + response.data;;
      },
      function(response) {
				console.log("hello" + event_title);

        return response.statusText + ' ' + response.status + ' ' + response.data;;
      });
    return promise;
  };

  return eventFactory;
}]);
