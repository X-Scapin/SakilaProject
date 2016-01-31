'use strict';

App.factory('FilmService', [
		'$http',
		'$q',
		function($http, $q) {

			return {

				fetchAllFilm : function() {
					return $http.get('http://localhost:8080/film/').then(
							function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching films');
								return $q.reject(errResponse);
							});
				},

				createFilm : function(film) {
					return $http.post('http://localhost:8080/film/', film)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while creating film');
								return $q.reject(errResponse);
							});
				},

				updateFilm : function(film, filmId) {
					console.log("XXX", film);
					return $http.post('http://localhost:8080/filmUpdate/',
							actor).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating film');
						return $q.reject(errResponse);
					});
				},

				deleteFilm : function(filmId) {
					return $http.get(
							'http://localhost:8080/filmDelete/' + filmId)
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while deleting film');
								return $q.reject(errResponse);
							});
				}

			};

		} ]);