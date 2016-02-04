'use strict';

App.controller('FilmController', [
		'$scope',
		'FilmService',
		'ActorService',
		'LanguageService',
		'CategoryService',
		function($scope, FilmService, ActorService, LanguageService, CategoryService) {
			var self = this;

			self.film = {
				filmId : null,
				title : '',
				description : '',
				filmActors : [],
				filmCategories : [],
				language_id : '',
				rentalDuration : '',
				replaCost : ''
			};
			self.films = [];
			self.languages = [];
			self.actors = [];
			self.categories = [];
			
			self.fetchAllFilms = function() {
				FilmService.fetchAllFilms().then(function(d) {
					self.films = d;
					self.fetchAllLanguages();
				}, function (errResponse) {
					console.error('Error while fetching films');
				});
			};
			
			self.fetchAllActors = function() {
				ActorService.fetchAllActors().then(function(d) {
					self.actors = d;
				}, function (errResponse) {
					console.error('Error while fetching actors');
				});
			};
			
			self.fetchAllCategories = function() {
				CategoryService.fetchAllCategory().then(function(d) {
					self.categories = d;
				}, function (errResponse) {
					console.error('Error while fetching categories');
				});
			};
			
			self.fetchAllLanguages = function() {
				LanguageService.fetchAllLanguage().then(function(d) {
					self.languages = d;
					angular.forEach(self.films, function(film, key) {
						for (var i = 0; i < self.languages; i++) {
							if (self.languages[i].languageId == film.language_id) {
								film.language = self.languages[i];
							}
						}
					});
				}, function (errResponse) {
					console.error('Error while fetching languages');
				});
			};

			self.getFilmLanguage = function(film) {
				LanguageService.getLanguage(film.language_id).then(function(d){
					
				}, function (errResponse) {
					console.error('Error while getting language');
				});
			};
			
			self.getFilmActors = function(film) {
				ActorService.getFilmActors(film.filmId).then(function(d){
					film.actors = d;
				}, function (errResponse) {
					console.error('Error while getting film actors');
				});
			};
			
			self.getFilmCategories= function(film) {
				CategoryService.getFilmCategories(film.filmId).then(function(d){
					film.categories = d;
				}, function (errResponse) {
					console.error('Error while getting film actors');
				});
			};
			
			self.fetchAllFilms();
			self.fetchAllActors();
			self.fetchAllCategories();
			
			//TODO
			/*
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
			};
			*/
			
			self.edit = function(filmId) {
				console.log('Film id to be edited', filmId);
				for (var i = 0; i < self.films.length; i++) {
					if (self.films[i].filmId == filmId) {
						self.film = angular.copy(self.films[i]);
						break;
					}
				}
			};
			
			self.remove = function(filmId) {
				console.log("Remove film : "+filmId);
				FilmService.deleteFilm(filmId).then(function(){
					if (self.film.filmId == filmId) {
						console.log("Reset form");
						self.reset();
					}
					self.fetchAllFilms();
				}, function(err){
					console.log("error removing film");
				});
			};

			self.reset = function() {
				self.film = {
						filmId : null,
						title : '',
						description : '',
						filmActors : [],
						filmCategories : [],
						language_id : '',
						rentalDuration : '',
						replaCost : ''
				};

				self.language = {
					languageId : null,
					name : ''
				};
				$scope.myForm.$setPristine(); //reset Form
			};
		} ]);