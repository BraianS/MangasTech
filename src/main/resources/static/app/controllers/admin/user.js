(function () {
	'use strict';
	//Carrega o modulo
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admUsuarioController', admUsuarioController);

	//Injeta as dependências
	admUsuarioController.$inject = ['AuthService'];

	function admUsuarioController(AuthService) {

		var vm = this;
		vm.user = AuthService.user;
	}
})();