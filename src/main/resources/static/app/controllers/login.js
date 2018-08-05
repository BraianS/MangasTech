(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('loginController', loginController);

	//Injeta as dependências
	loginController.$inject = ['$http', '$state', 'AuthService', '$rootScope'];

	function loginController($http, $state, AuthService, $rootScope) {

		var vm = this;

		vm.token = [];
		vm.username = [];
		vm.password = [];
		vm.autenticar = autenticar;

		/* Autenticar um Usuario */
		function autenticar() {
			if (vm.formLogin.$valid) {
				$http({
					method: 'POST',
					url: '/autenticar',
					params: {
						username: vm.username,
						password: vm.password
					}
				}).then(function (res) {
					vm.password = null;					
					if (res.data.token) {
						vm.mensagem = '';						
						$http.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.token;
						AuthService.user = res.data.user;
						$rootScope.$broadcast('LoginSuccessful');
						$state.go('nav');
					} else {
						vm.mensagem = 'Autenticação Falhau';
					}
				}, function (res) {
					vm.mensagem = 'Autenticação Falhou';
				});
			}
			else {
				alert("Formulario invalido")
			}
		};
	}
})();