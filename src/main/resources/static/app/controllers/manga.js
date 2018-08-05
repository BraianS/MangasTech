(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('mangaController', mangaController);

	//Injeta as dependências
	mangaController.$inject = ['$http'];

	function mangaController($http) {

		var vm = this;

		var alfabeto = "abcdefghijklmnopqrstuvwxyz";
		vm.letra = alfabeto.toUpperCase().split("");

		vm.mangas = [];
		vm.paginaPorMangas = 1;
		vm.paginaPorLetra = 1;
		vm.status = false;
		vm.letraAtivada = "";
		vm.false = falso;
		vm.true = verdadeiro;
		vm.carregarMangas = carregarMangas;
		vm.carregarAlfabeto = carregarAlfabeto;

		carregarMangas();

		function falso() {
			vm.paginaPorMangas = 1;
			vm.letraAtivada = "";
			vm.status = false;
		}

		function verdadeiro() {
			vm.status = true;
			vm.paginaPorLetra = 1;
		}

		function carregarAlfabeto(letras) {
			if (letras != null) {
				vm.letraAtivada = letras;
			} $http({
				method: 'GET',
				url: '/user/manga/az/' + vm.letraAtivada + '?page=' + vm.paginaPorLetra
			}).then(function (response) {
				vm.mangas = response.data.content;
				vm.totalElementos = response.data.totalElements;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}

		function carregarMangas() {
			$http({
				method: 'GET',
				url: '/user/manga?page=' + vm.paginaPorMangas
			}).then(function (response) {
				vm.mangas = response.data.content;
				vm.totalElementos1 = response.data.totalElements;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			});
		}
	}
})();