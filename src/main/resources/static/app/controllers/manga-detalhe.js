(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('mangaDetalheController', mangaDetalheController);

	//Injeta as dependências
	mangaDetalheController.$inject = ['$http', '$stateParams', '$scope'];

	function mangaDetalheController($http, $stateParams, $scope) {

		var vm = this;

		vm.ListCapitulos = [];
		vm.manga = [];
		vm.mangasId = $stateParams.mangasId;

		carregarMangaECapitulos();
		function carregarMangaECapitulos() {
			$http({
				method: 'GET',
				url: '/user/capitulo/lista/' + vm.mangasId
			}).then(function (response) {
				vm.manga = response.data;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}
	}
})();