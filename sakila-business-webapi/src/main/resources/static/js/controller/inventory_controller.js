'use strict';

App.controller('InventoryController', [
		'$scope',
		'InventoryService',
		'FilmService',
		function($scope, InventoryService, FilmService) {
			
			var self = this;
			self.films = [];
			
			self.fetchAllInventories = function() {
				InventoryService.fetchAllInventories().then(function(d) {
					self.inventories = d;
				}, function (errResponse) {
					console.error('Error while fetching inventories');
				})
			};
			
			self.createInventory = function(inventory) {
				InventoryService.createInventory(inventory).then(self.fetchAllInventories,
						function(errResponse) {
					console.error('Error while creating Actor.');
				});
			}
			
			self.updateInventory = function(inventory) {
				InventoryService.updateInventory(inventory).then(self.fetchAllInventories,
						function(errResponse) {
							console.error('Error while updating Inventory.');
						});
			};

			self.deleteInventory = function(inventoryId) {
				InventoryService.deleteInventory(inventoryId).then(self.fetchAllInventories,
						function(errResponse) {
							console.error('Error while deleting Inventory.');
						});
			};
			
			self.fetchAllFilms = function() {
				FilmService.fetchAllFilms().then(function(d) {
					self.films = d;
					self.checkInventories();
				}, function (errResponse) {
					console.error('Error while fetching films');
				})
			};
			
			self.checkInventories = function() {
				angular.forEach(self.films, function(film, k) {
					InventoryService.getInventoriesByFilm(film.filmId).then(
							function(d) {
								film.quantity = d.length;
								film.inventoriesId = [];
								angular.forEach(d, function(inventory, key) {
										film.inventoriesId.push(inventory.inventoryId);
									});
							},
							function(errResponse) {
						console.error('Error while getting Inventories by film');
					});
				});
			}
			
			self.fetchAllFilms();
			
			self.addInventory = function(film) {
				console.log('Inventory to be add for film : ', film);
				film.quantity++;
				// TODO
				var currentFilm = {film: film.filmId, store: 1};
				InventoryService.createInventory(currentFilm).then(function(){
							self.checkInventories();
						},
						function(err){
							film.quantity--;
							console.log("error adding inventory");
						}
				)
			};

			self.remove = function(film) {
				console.log('Remove inventory with id : ', film.inventoriesId[0]);
				film.quantity--;
				InventoryService.deleteInventory(film.inventoriesId[0]).then(function(){
							self.checkInventories();
						},
						function(err){
							film.quantity++;
							console.log("error adding inventory");
						}
				)
			};
			
		} ]);