'use strict';

App.controller('InventoryController', [
		'$scope',
		'InventoryService',
		function($scope, InventoryService) {
			var self = this;
			self.inventory = {
				inventoryId : null,
				film : '',
				store : ''
			};
			self.inventories = [];
			
			self.fetchAllInventories = function() {
				InventoryService.fetchAllInventories().then(function(d) {
					self.inventories = d;
				}, function (errResponse) {
					console.error('Error while fetching inventories')
				})
			};
			
			self.fetchAllInventories();
			
			
		} ]);