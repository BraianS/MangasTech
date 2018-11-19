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
		vm.pagina = 1;

		carregarGeneros();

		function carregarGeneros() {
			return generoService.carregarGeneros(vm.pagina)
				.then(function (data) {
					vm.genero = data.content;
					vm.totalElementos = data.totalElements;
				})
		}
	}
})();