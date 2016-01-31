'use strict';

App.factory('CategoryService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllCategory : function() {
					return $http.get('http://localhost:8080/category/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching categorys');
								return $q.reject(errResponse);
							});
				},

				createCategory : function(category) {
					return $http.post('http://localhost:8080/category/', category)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating category');
								return $q.reject(errResponse);
							});
				},

				updateCategory : function(category, categoryId) {
					console.log("XXX", category);
					return $http.post('http://localhost:8080/categoryUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating category');
						return $q.reject(errResponse);
					});
				},

				deleteCategory : function(categoryId) {
					return $http.get(
							'http://localhost:8080/categoryDelete/' + categoryId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting category');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);