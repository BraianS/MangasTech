(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('generoDetalheController', generoDetalheController);

	//Injeta as dependências
	generoDetalheController.$inject = ['generoService', '$stateParams'];

	function generoDetalheController(generoService, $stateParams) {

		var vm = this;

		vm.generosDetalhe = [];
		vm.pagina = 1;
		vm.generoId = $stateParams.generoId;

		carregarGeneros();

		function carregarGeneros(Genero) {
			return generoService.buscarGeneroPorId(vm.generoId, vm.pagina)
				.then(function (data) {
					vm.generosDetalhe = data.content;
					vm.itels = data.totalElements;
				})
		}
	}
})();