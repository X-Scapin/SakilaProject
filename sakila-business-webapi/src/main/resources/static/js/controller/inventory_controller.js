'use strict';

App.controller('InventoryController', [
		'$scope',
		'InventoryService',
		'FilmService',
		'StoreService',
		function($scope, $cookieStore, InventoryService, FilmService, StoreService) {
			var self = this;

			self.inventory = {
				inventoryId : null,
				film : ''
			}
			
		} ]);