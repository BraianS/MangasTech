(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('navController', navController);

	//Injeta as dependênciass
	navController.$inject = ['$scope', 'AuthService', 'pesquisaService','$location','$state'];

	function navController($scope, AuthService, pesquisaService,$location,$state) {

		var vm = this;

		vm.nome = [];
		vm.user = [];
		vm.logout = logout;
		vm.service = service;
		vm.isActive = isActive;

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
			vm.formPesquisa.$setPristine();			
			pesquisaService.setNome(nome);
			$state.go('nav.pesquisa', {txtPesquisado:vm.nome});
			vm.nome = [];				
		}

		function isActive(active){
			return active === $location.path();
		}	
	}
})();