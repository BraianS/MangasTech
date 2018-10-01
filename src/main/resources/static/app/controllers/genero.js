(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('generosController', generosController);

	//Injeta as dependências
	generosController.$inject = ['generoService'];

	function generosController(generoService) {

		var vm = this;

		vm.genero = [];
		vm.carregarGeneros = carregarGeneros;

		carregarGeneros();

		function carregarGeneros() {
			return generoService.carregarGeneros()
				.then(function (data) {
					vm.genero = data.content;
				})
		}
	}
})();