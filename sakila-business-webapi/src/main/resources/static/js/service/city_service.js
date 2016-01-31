'use strict';

App.factory('CityService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllCitys : function() {
					return $http.get('http://localhost:8080/city/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching citys');
								return $q.reject(errResponse);
							});
				},

				createCity : function(city) {
					return $http.post('http://localhost:8080/city/', city)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating city');
								return $q.reject(errResponse);
							});
				},

				updateCity : function(city, cityId) {
					console.log("XXX", city);
					return $http.post('http://localhost:8080/cityUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating city');
						return $q.reject(errResponse);
					});
				},

				deleteCity : function(cityId) {
					return $http.get(
							'http://localhost:8080/cityDelete/' + cityId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting city');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);