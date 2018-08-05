(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('registrarController', registrarController);

	//Injeta as dependências
	registrarController.$inject = ['$http', '$state'];

	function registrarController($http, $state) {

		var vm = this;

		vm.registrar = {};
		vm.cadastrar = cadastrar;

		function cadastrar() {
			if (vm.formRegistrar.$valid) {
				$http({
					method: 'POST',
					url: '/registrar', data: vm.registrar
				}).then(function (response) {
					vm.confirmarSenha = null;
					vm.registrar = {};
					vm.formRegistrar.$setPristine(true);
					$state.go('nav.login');
				}, function (response) {
					console.log(response.data.message);
					console.log(response.data);
					vm.mensagem = response.data.message;
				})
			}
			else {
				alert("formulario invalido");
			}
		}
	}
})();