(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('loginController', loginController);

	//Injeta as dependências
	loginController.$inject = ['$http', '$state', 'AuthService', '$rootScope', 'loginService'];

	function loginController($http, $state, AuthService, $rootScope, loginService) {

		var vm = this;

		vm.token = [];
		vm.username = [];
		vm.password = [];
		vm.autenticar = autenticar;

		/* Autenticar um Usuario */
		function autenticar() {
			if (vm.formLogin.$valid) {
				return loginService.login(vm.username, vm.password)
					.then(function (data) {
						vm.password = null;
						if (data.token) {
							vm.mensagem = '';
							$http.defaults.headers.common['Authorization'] = 'Bearer ' + data.token;
							AuthService.user = data.user;
							$rootScope.$broadcast('LoginSuccessful');
							$state.go('nav');
						}
					})
			}
			else {
				alert("Formulario invalido")
			}
		};
	}
})();