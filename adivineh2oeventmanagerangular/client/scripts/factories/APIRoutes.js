myApp.factory('APIRoutes',['$http',function($http) {
		var baseURL = "http://adivineh2oeventmanagerapi.us-east-1.elasticbeanstalk.com/router/";
		var responseHandle = '?callback=JSON_CALLBACK';
		// var responseHandle = '';


		var title = "";
		var original_title = "";
		var supply_auto_id = "";
		var phone = "";
		var person_auto_id = "";
		var schedule_auto_id = "";
		var eventObject = {};
		var person_auto_id = {};
		var supplyObject = {};
		var scheduleObject = {};
		var messageThreadObject = {};
		var messageObject = {};
		var hashMapOfEventObjects = {};
		var arrayOfMessageObjects = {};
		var hashMapOfSupplyObjects = {};
		var hashMapOfPersonObjects = {};
		var person_and_passObject = {};
		var personObject = {};
		var input_pass = {};
		var arrayOfScheduleObjects = {};
		var hashMapOfArraysOfMessageObjects = {};
		var old_pass_new_passObject = {};
		var schedule_auto_id = {};


	 var possibleRequests = [
		 {type: "post", route: "event", subroute: "/event", param: "", body: eventObject},
		 {type: "post", route: "event", subroute: "/person/", param: title, body: person_auto_id},
		 {type: "post", route: "event", subroute: "/supply", param: "", body: supplyObject},
		 {type: "post", route: "event", subroute: "/schedule", param: "", body: scheduleObject},
		 {type: "post", route: "event", subroute: "/message_thread", param: "", body: messageThreadObject},
		 {type: "post", route: "event", subroute: "/message_thread/message", param: "", body: messageObject},
		 {type: "get", route: "event", subroute: "/all", param: "", body: hashMapOfEventObjects},
		 {type: "get", route: "event", subroute: "/", param: title, body: eventObject},
		 {type: "get", route: "event", subroute: "/schedule/", param: title, body: scheduleObject},
		 {type: "get", route: "event", subroute: "/message_thread/message_all/", param: title, body: arrayOfMessageObjects},
		 {type: "get", route: "event", subroute: "/supply_all/", param: title, body: hashMapOfSupplyObjects},
		 {type: "get", route: "event", subroute: "/person_all/", param: title, body: hashMapOfPersonObjects},
		 {type: "put", route: "event", subroute: "/", param: original_title, body: eventObject},
		 {type: "put", route: "event", subroute: "/supply", param: "", body: supplyObject},
		 {type: "put", route: "event", subroute: "/schedule", param: "", body: scheduleObject},
		 {type: "delete", route: "event", subroute: "/", param: title, body: ""},
		 {type: "delete", route: "event", subroute: "/supply", param: supply_auto_id, body: ""},
		 {type: "delete", route: "event", subroute: "/person/", param: title + "/" + person_auto_id, body: ""},
		 {type: "post", route: "person", subroute: "", param: "", body: personObject},
		 {type: "post", route: "person", subroute: "/register", param: "", body: person_and_passObject},
		 {type: "post", route: "person", subroute: "/schedule", param: "", body: scheduleObject},
		 {type: "get", route: "person", subroute: "/all", param: "", body: hashMapOfPersonObjects},
		 {type: "get", route: "person", subroute: "/", param: phone, body: personObject},
		 {type: "post", route: "person", subroute: "/login/", param: phone, body: input_pass},
		 {type: "get", route: "person", subroute: "/schedule_all/", param: person_auto_id, body: arrayOfScheduleObjects},
		 {type: "get", route: "person", subroute: "/supply_all/", param: person_auto_id, body: hashMapOfSupplyObjects},
		 {type: "get", route: "person", subroute: "/message_all/message_thread_match/", param: person_auto_id, body: hashMapOfArraysOfMessageObjects},
		 {type: "put", route: "person", subroute: "/person", param: "", body: personObject},
		 {type: "put", route: "person", subroute: "/pass/", param: phone, body: old_pass_new_passObject},
		 {type: "delete", route: "person", subroute: "/", param: person_auto_id, body: ""},
		 {type: "delete", route: "person", subroute: "/schedule/", param: schedule_auto_id, body: ""}
	 ];


			function apiCall(possibleRequestOption, param, body) {
				var requestOption = possibleRequests[possibleRequestOption];
				var type = requestOption.type;
				var route = requestOption.route;
				var subroute = requestOption.subroute;
				// var p = param == "" ? requestOption.param : param;

				var url = baseURL + route + subroute + param + responseHandle;
				switch (type) {
					case 'post':
							return $http.post(url, body);
					case 'get':
							return $http.get(url);
					case 'put':
					return $http.put(url, body);
					case 'delete':
							console.log("hello " + url);

							return $http.delete(url);
				}
			};
			return {
				apiCall: apiCall
			};

}]);
