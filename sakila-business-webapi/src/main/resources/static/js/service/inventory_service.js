'use strict';

App.factory('InventoryService', [
		'$http',
		'$q',
		function($http, $q) {

			return {
				fetchAllInventories : function() {
					return $http.get('http://localhost:8080/inventory/store/1').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching inventories');
								return $q.reject(errResponse);
							});
				},
				
				getInventoriesByFilm : function(filmId) {
					return $http.get('http://localhost:8080/inventory/film/'+filmId).then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching inventories by film');
								return $q.reject(errResponse);
							});
				},

				createInventory : function(inventory) {
					return $http.post('http://localhost:8080/inventory/', inventory)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating inventory');
								return $q.reject(errResponse);
							});
				},

				updateInventory : function(inventory, inventoryId) {
					console.log("XXX", inventory);
					return $http.post('http://localhost:8080/inventoryUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating inventory');
						return $q.reject(errResponse);
					});
				},

				deleteInventory : function(inventoryId) {
					return $http.get(
							'http://localhost:8080/inventoryDelete/' + inventoryId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting inventory');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);