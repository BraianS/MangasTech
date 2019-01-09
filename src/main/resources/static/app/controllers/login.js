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

		vm.autenticar = autenticar;
		vm.login = {};
		/* Autenticar um Usuario */
		function autenticar() {
			if (vm.formLogin.$valid) {
				return loginService.login(vm.login)
					.then(function (data) {
						if (data.accessToken) {
							vm.mensagem = '';
							console.log(vm.mensagem);
							$http.defaults.headers.common['Authorization'] = 'Bearer ' + data.accessToken;
							AuthService.user = data;
							$rootScope.$broadcast('LoginSuccessful');
							$state.go('nav');
						}
						else {
							vm.mensagem = data.message;
							vm.login = {};
							vm.formLogin.$setPristine(true);
							console.log("vm.formlogin: " + vm.usuario)
						}
					})
			}
		};
	}
})();