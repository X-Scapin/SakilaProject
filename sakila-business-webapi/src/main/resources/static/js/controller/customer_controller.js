'use strict';

App.controller('CustomerController', [
		'$scope',
		'CustomerService',
		'AddressService',
		'CityService',
		function($scope, CustomerService, AddressService, CityService) {
			var self = this;

			self.address = {
				addressId : null,
				address : '',
				address2 : '',
				district : '',
				city_id : '',
				postalCode : ''	
			}
			
			self.customer = {
				customerId : null,
				store_id : '1',
				firstName : '',
				lastName : '',
				email : '',
				phone : '',
				address_id : self.address.addressId,
			};
			
			
			self.customers = [];
			self.customerAddress = {
					addressId : null,
					address : '',
					address2 : '',
					district : '',
					city_id : '',
					postalCode : ''	
				};
			self.citys = [];

			self.fetchAllCustomers = function() {
				CustomerService.fetchAllCustomers().then(function(d) {
					self.customers = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};
			
			self.readAddress = function(address_Id) {
				AddressService.readAddress(address_Id).then(function(d) {
					self.customerAddress = d;
					console.log(d);
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};
			
			self.fetchAllCitys = function() {
				CityService.fetchAllCitys().then(function(d) {
					self.citys = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};
			
			self.createAddress = function(address) {
				AddressService.createAddress(address).then(function(d) {
							self.address = d;
							self.customer.address_Id = address.addressId;
						}, function(errResponse) {
							console.error('Error while creating Address.');
						});
			};
			
			self.updateAddress = function(address) {
				AddressService.updateAddress(address).then(
						self.fetchAllCustomers, function(errResponse) {
							console.error('Error while updating Address.');
						});
			};

			self.createCustomer = function(customer) {
				CustomerService.createCustomer(customer).then(
						self.fetchAllCustomers, function(errResponse) {
							console.error('Error while creating Customer.');
						});
			};

			self.updateCustomer = function(customer) {
				CustomerService.updateCustomer(customer).then(
						self.fetchAllCustomers, function(errResponse) {
							console.error('Error while updating Customer.');
						});
			};
			
			self.deleteCustomer = function(customerId) {
				CustomerService.deleteCustomer(customerId).then(self.fetchAllCustomers,
						function(errResponse) {
							console.error('Error while deleting Customer.');
						});
			};
			
			self.fetchAllCustomers();
			self.fetchAllCitys();
			

			self.submit = function() {
				if (self.customer.customerId == null) {
					console.log('Saving New Customer', self.customer);
					self.createAddress(self.address);
					self.createCustomer(self.customer);
				} else {
					console.log('Customer updating with id ',
							self.customer.customerId);
					console.log('Customer: ', self.customer);
					self.updateAddress(self.address);
					self.updateCustomer(self.customer);
				}
				self.reset();
			};

			self.edit = function(customerId, addressId) {
				console.log('customer id to be edited', customerId);
				console.log('address id to be edited', addressId);
				for (var i = 0; i < self.customers.length; i++) {
					if (self.customers[i].customerId == customerId) {
						self.customer = angular.copy(self.customers[i]);
						if (self.customers[i].address_id > 0) {
							self.readAddress(self.customers[i].address_id);
							console.log(self.customers[i].address_id);
							console.log(self.customerAddress);
							if (self.customers[i].address_id == self.customerAddress.addressId) {
								console.log('test');
								self.address = angular.copy(self.customerAddress);
								
								
							}
						}
						console.log(self.customer);
						break;
					}
				}
			};

			

			self.reset = function() {
				self.customer = {
					customerId : null,
					store_id : '1',
					firstName : '',
					lastName : '',
					email : '',
					phone : '',
					addressId : '',
				};
				self.address = {
					address : '',
					address2 : '',
					district : '',
					city_id : '',
					postalCode : '',	
				}
				$scope.myForm.$setPristine(); //reset Form
			};

		} ]);