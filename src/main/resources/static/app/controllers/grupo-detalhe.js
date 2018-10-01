(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('grupoDetalheController', grupoDetalheController);

	//Injeta as dependências
	grupoDetalheController.$inject = ['$stateParams', 'grupoService'];

	function grupoDetalheController($stateParams, grupoService) {

		var vm = this;

		vm.grupo = [];
		vm.pagina = 1;
		vm.grupoId = $stateParams.gruposId;
		vm.totalElementos = [];
		vm.carregarGrupos = carregarGrupos;

		carregarGrupos();

		function carregarGrupos() {
			return grupoService.buscarAutorPorId(vm.grupoId, vm.pagina)
				.then(function (data) {
					vm.grupo = data.content;
					vm.totalElementos = data.totalElements;
				})
		}
	}
})();