'use strict';

App.factory('RentalService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllRental : function() {
					return $http.get('http://localhost:8080/rental/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching rentals');
								return $q.reject(errResponse);
							});
				},

				createRental : function(rental) {
					return $http.post('http://localhost:8080/rental/', rental)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating rental');
								return $q.reject(errResponse);
							});
				},

				updateRental : function(rental, rentalId) {
					console.log("XXX", rental);
					return $http.post('http://localhost:8080/rentalUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating rental');
						return $q.reject(errResponse);
					});
				},

				deleteRental : function(rentalId) {
					return $http.get(
							'http://localhost:8080/rentalDelete/' + rentalId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting rental');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);