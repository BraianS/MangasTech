(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('mangaDetalheController', mangaDetalheController);

	//Injeta as dependências
	mangaDetalheController.$inject = ['$http', '$stateParams', 'capituloService'];

	function mangaDetalheController($http, $stateParams, capituloService) {

		var vm = this;

		vm.ListCapitulos = [];
		vm.manga = [];
		vm.mangasId = $stateParams.mangasId;

		carregarMangaECapitulos();
		function carregarMangaECapitulos() {
			return capituloService.listaCapitulosPorManga(vm.mangasId)
				.then(function (data) {
					vm.manga = data;
				})
		}
	}
})();