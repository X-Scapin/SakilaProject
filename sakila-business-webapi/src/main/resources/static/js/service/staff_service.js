'use strict';

App.factory('StaffService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllStaff : function() {
					return $http.get('http://localhost:8080/staff/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching staffs');
								return $q.reject(errResponse);
							});
				},

				createStaff : function(staff) {
					return $http.post('http://localhost:8080/staff/', staff)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating staff');
								return $q.reject(errResponse);
							});
				},

				updateStaff : function(staff, staffId) {
					console.log("XXX", staff);
					return $http.post('http://localhost:8080/staffUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating staff');
						return $q.reject(errResponse);
					});
				},

				deleteStaff : function(staffId) {
					return $http.get(
							'http://localhost:8080/staffDelete/' + staffId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting staff');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);