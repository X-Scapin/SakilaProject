'use strict';

App.controller('ActorController', [
		'$scope',
		'ActorService',
		function($scope, ActorService) {
			var self = this;
			self.actor = {
				actorId : null,
				lastName : '',
				firstName : ''
			};
			self.actors = [];

			self.fetchAllActors = function() {
				ActorService.fetchAllActors().then(function(d) {
					self.actors = d;
				}, function(errResponse) {
					console.error('Error while fetching Currencies');
				});
			};

			self.createActor = function(actor) {
				ActorService.createActor(actor).then(self.fetchAllActors,
						function(errResponse) {
							console.error('Error while creating Actor.');
						});
			};

			self.updateActor = function(actor) {
				ActorService.updateActor(actor).then(self.fetchAllActors,
						function(errResponse) {
							console.error('Error while updating Actor.');
						});
			};

			self.deleteActor = function(actorId) {
				ActorService.deleteActor(actorId).then(self.fetchAllActors,
						function(errResponse) {
							console.error('Error while deleting Actor.');
						});
			};

			self.fetchAllActors();

			self.submit = function() {
				if (self.actor.actorId == null) {
					console.log('Saving New Actor', self.actor);
					self.createActor(self.actor);
				} else {
					console.log('Actor updating with id ', self.actor.actorId);
					console.log('Actor: ', self.actor);
					self.updateActor(self.actor);
				}
				self.reset();
			};

			self.edit = function(actorId) {
				console.log('id to be edited', actorId);
				for (var i = 0; i < self.actors.length; i++) {
					if (self.actors[i].actorId == actorId) {
						self.actor = angular.copy(self.actors[i]);
						break;
					}
				}
			};

			self.remove = function(actorId) {
				console.log('id to be deleted', actorId);
				for (var i = 0; i < self.actors.length; i++) {
					if (self.actors[i].actorId == actorId) {
						self.reset();
						break;
					}
				}
				self.deleteActor(actorId);
			};

			self.reset = function() {
				self.actor = {
					actorId : null,
					lastName : '',
					firstName : ''
				};
				$scope.myForm.$setPristine(); // reset Form
			};

		} ]);