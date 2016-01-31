'use strict';

App.factory('PaymentService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllPayment : function() {
					return $http.get('http://localhost:8080/payment/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching payments');
								return $q.reject(errResponse);
							});
				},

				createPayment : function(payment) {
					return $http.post('http://localhost:8080/payment/', payment)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating payment');
								return $q.reject(errResponse);
							});
				},

				updatePayment : function(payment, paymentId) {
					console.log("XXX", payment);
					return $http.post('http://localhost:8080/paymentUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating payment');
						return $q.reject(errResponse);
					});
				},

				deletePayment : function(paymentId) {
					return $http.get(
							'http://localhost:8080/paymentDelete/' + paymentId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting payment');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);