(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('navController', navController);

	//Injeta as dependênciass
	navController.$inject = ['$scope', 'AuthService', 'pesquisaService'];

	function navController($scope, AuthService, pesquisaService) {

		var vm = this;

		vm.nome = [];
		vm.user = [];
		vm.logout = logout;
		vm.service = service;

		$scope.$on('LoginSuccessful', function () {
			vm.user = AuthService.user;
		});

		$scope.$on('LogoutSuccessful', function () {
			vm.user.user = null;
		});

		function logout() {
			AuthService.user = null;
		}

		function service(nome) {
			pesquisaService.setValue(nome);
			vm.nome = [];
		}
	}
})();