'use strict';

App.factory('AddressService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllAddress : function() {
					return $http.get('http://localhost:8080/address/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching addresss');
								return $q.reject(errResponse);
							});
				},
				
				readAddress : function(address) {
					return $http.post('http://localhost:8080/address/', customer)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while reading address');
								return $q.reject(errResponse);
							});
				},

				createAddress : function(address) {
					return $http.post('http://localhost:8080/addressCreate/', address)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating address');
								return $q.reject(errResponse);
							});
				},

				updateAddress : function(address, addressId) {
					console.log("XXX", address);
					return $http.post('http://localhost:8080/addressUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating address');
						return $q.reject(errResponse);
					});
				},

				deleteAddress : function(addressId) {
					return $http.get(
							'http://localhost:8080/addressDelete/' + addressId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting address');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);