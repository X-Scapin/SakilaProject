'use strict';

App.factory('StoreService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllStore : function() {
					return $http.get('http://localhost:8080/store/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching stores');
								return $q.reject(errResponse);
							});
				},

				createStore : function(store) {
					return $http.post('http://localhost:8080/store/', store)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating store');
								return $q.reject(errResponse);
							});
				},

				updateStore : function(store, storeId) {
					console.log("XXX", store);
					return $http.post('http://localhost:8080/storeUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating store');
						return $q.reject(errResponse);
					});
				},

				deleteStore : function(storeId) {
					return $http.get(
							'http://localhost:8080/storeDelete/' + storeId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting store');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);