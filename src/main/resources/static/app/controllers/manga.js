(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('mangaController', mangaController);

	//Injeta as dependências
	mangaController.$inject = ['mangaService'];

	function mangaController(mangaService) {

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
			}
			return mangaService.buscarMangaPorLetra(vm.letraAtivada, vm.paginaPorLetra)
				.then(function (data) {
					vm.mangas = data.content;
					vm.totalElementos = data.totalElements;
				})
		}

		function carregarMangas() {
			return mangaService.carregarMangas(vm.paginaPorMangas)
				.then(function (data) {
					vm.mangas = data.content;
				})
		}
	}
})();