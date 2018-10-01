(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('registrarController', registrarController);

	//Injeta as dependências
	registrarController.$inject = ['registrarService', '$state'];

	function registrarController(registrarService, $state) {

		var vm = this;

		vm.registrar = {};
		vm.cadastrar = cadastrar;

		function cadastrar() {
			if (vm.formRegistrar.$valid) {
				return registrarService.registrarUsuario(vm.registrar)
					.then(function (data) {
						vm.confirmarSenha = null;
						vm.registrar = {};
						vm.formRegistrar.$setPristine(true);
						$state.go('nav.login');
						vm.mensagem = data.message;
					})
			}
			else {
				alert("Formulario invalido");
			}
		}
	}
})();