(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('homeController', homeController);

	//Injeta as dependências
	homeController.$inject = ['$http'];

	function homeController($http) {

		var vm = this;

		vm.novosCapitulos = [];
		vm.novosMangas = [];

		carregarNovosCapitulos();
		carregarNovosMangas();

		function carregarNovosCapitulos() {
			$http({
				method: 'GET',
				url: '/user/capitulo/novidades'
			}).then(function (response) {
				vm.novosCapitulos = response.data;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}

		function carregarNovosMangas() {
			$http({
				method: 'GET',
				url: '/user/manga/top10'
			}).then(function (response) {
				vm.novosMangas = response.data;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}
	}
})();