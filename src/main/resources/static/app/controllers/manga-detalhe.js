(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('mangaDetalheController', mangaDetalheController);

	//Injeta as dependências
	mangaDetalheController.$inject = ['$stateParams', 'mangaService'];

	function mangaDetalheController($stateParams, mangaService) {

		var vm = this;

		vm.ListCapitulos = [];
		vm.manga = [];
		vm.mangasId = $stateParams.mangasId;

		carregarMangaECapitulos();
		function carregarMangaECapitulos() {
			return mangaService.buscarMangaPorId(vm.mangasId)
				.then(function (data) {
					vm.manga = data;
				})
		}
	}
})();