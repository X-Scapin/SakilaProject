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
				postal_code : '',
				city_id : '1',
			}
			
			self.customer = {
				customerId : null,
				store_id : '1',
				firstName : '',
				lastName : '',
				email : '',
				phone : '',
				address_id : null,
			};
			
			
			self.customers = [];
			self.citys = [];
			self.allAddress = [];

			self.fetchAllCustomers = function() {
				CustomerService.fetchAllCustomers().then(function(d) {
					self.customers = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};
			
			self.fetchAllAddress = function() {
				AddressService.fetchAllAddress().then(function(d) {
					self.allAddress = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};
			
//			self.readAddress = function(address_Id) {
//				AddressService.readAddress(address_Id).then(function(d) {
//					self.customerAddress = d;
//				}, function(errResponse) {
//					console.error('Error while fetching Currencies');
//				});
//			};
			
			self.fetchAllCitys = function() {
				CityService.fetchAllCitys().then(function(d) {
					self.citys = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};
			
			self.createAddressAndCustomer = function(address, customer) {
				AddressService.createAddress(address).then(function(d) {
							customer.address_id = d.addressId;
							self.createCustomer(customer);
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
			self.fetchAllAddress();
			self.fetchAllCitys();
			

			self.submit = function() {
				if (self.customer.customerId == null) {
					self.createAddressAndCustomer(self.address,self.customer);			
				} else {
					console.log('Customer updating with id ',
							self.customer.customerId);
					console.log('Customer: ', self.customer);
					self.updateAddress(self.address);
					self.updateCustomer(self.customer);
				}
				self.reset();
				self.fetchAllCustomers();
			};

			self.edit = function(customerId, addressId) {
				console.log('customer id to be edited', customerId);
				console.log('address id to be edited', addressId);
				for (var i = 0; i < self.customers.length; i++) {
					if (self.customers[i].customerId == customerId) {
						self.customer = angular.copy(self.customers[i]);
						if (self.customers[i].address_id > 0) {
							for (var j = 0; j < self.allAddress.length; j++) {
								if (addressId == self.allAddress[j].addressId) {
									console.log(self.allAddress[j]);
									self.address = angular.copy(self.allAddress[j]);
								}
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
					address_id : null,
				};
				self.address = {
					address : '',
					address2 : '',
					district : '',
					city_id : '1',
					postal_code : '',	
				}
				$scope.myForm.$setPristine(); //reset Form
			};

		} ]);