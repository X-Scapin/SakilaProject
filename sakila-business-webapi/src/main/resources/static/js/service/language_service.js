'use strict';

App.factory('LanguageService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllLanguage : function() {
					return $http.get('http://localhost:8080/language/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching languages');
								return $q.reject(errResponse);
							});
				},

				getLanguage : function(languageId) {
					return $http.get('http://localhost:8080/language/'+languageId)
						.then(function(response) {
							return response.data;
						}, function(errResponse) {
							console.error('Error while getting language');
							return $q.reject(errResponse);
						});
				},
				
				createLanguage : function(language) {
					return $http.post('http://localhost:8080/language/', language)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating language');
								return $q.reject(errResponse);
							});
				},

				updateLanguage : function(language, languageId) {
					console.log("XXX", language);
					return $http.post('http://localhost:8080/languageUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating language');
						return $q.reject(errResponse);
					});
				},

				deleteLanguage : function(languageId) {
					return $http.get(
							'http://localhost:8080/languageDelete/' + languageId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting language');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);