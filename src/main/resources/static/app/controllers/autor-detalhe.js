(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('autorDetalheController', autorDetalheController);

	//Injeta as dependências
	autorDetalheController.$inject = ['autorService', '$stateParams'];

	function autorDetalheController(autorService, $stateParams) {

		var vm = this;

		vm.autor = [];
		vm.pagina = 1;
		vm.autorId = $stateParams.autorId;
		vm.totalElementos = [];
		vm.carregarAutor = carregarAutor;

		carregarAutor();

		function carregarAutor() {
			return autorService.buscarAutorPorId(vm.autorId, vm.pagina)
				.then(function (data) {
					vm.autor = data.content;
					vm.totalElementos = data.totalElements;
				})
		}
	}
})();