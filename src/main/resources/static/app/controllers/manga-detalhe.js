(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('mangaDetalheController', mangaDetalheController);

	//Injeta as dependências
	mangaDetalheController.$inject = ['$http', '$stateParams'];

	function mangaDetalheController($http, $stateParams) {

		var vm = this;

		vm.ListCapitulos = [];
		vm.manga = [];
		vm.mangasId = $stateParams.mangasId;

		carregarMangas();
		carregarCapitulos();

		/* Atualiza os Mangas */
		function carregarMangas() {
			$http({
				method: 'GET',
				url: '/user/manga/' + vm.mangasId
			}).then(function (response) {
				vm.manga = response.data;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		};

		/* Atualiza os Capitulos */
		function carregarCapitulos() {
			$http({
				method: 'GET',
				url: "/user/capitulo/" + vm.mangasId
			}).then(function (response) {
				vm.ListCapitulos = response.data;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}
	}
})();