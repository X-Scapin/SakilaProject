'use strict';

App.factory('AdressService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllAdress : function() {
					return $http.get('http://localhost:8080/adress/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching adresss');
								return $q.reject(errResponse);
							});
				},
				
				readAdress : function(adress) {
					return $http.post('http://localhost:8080/adress/', customer)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while reading adress');
								return $q.reject(errResponse);
							});
				},

				createAdress : function(adress) {
					return $http.post('http://localhost:8080/adressCreate/', adress)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating adress');
								return $q.reject(errResponse);
							});
				},

				updateAdress : function(adress, adressId) {
					console.log("XXX", adress);
					return $http.post('http://localhost:8080/adressUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating adress');
						return $q.reject(errResponse);
					});
				},

				deleteAdress : function(adressId) {
					return $http.get(
							'http://localhost:8080/adressDelete/' + adressId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting adress');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);