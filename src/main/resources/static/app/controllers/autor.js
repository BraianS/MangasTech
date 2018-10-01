(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('autorController', autorController);

	//Injeta as dependências
	autorController.$inject = ['autorService'];

	function autorController(autorService) {

		var vm = this;

		var alfabeto = "abcdefghijklmnopqrstuvwxyz";
		vm.letra = alfabeto.toUpperCase().split("");

		vm.autor = [];
		vm.paginaAutor = 1;
		vm.paginaPorLetra = 1;
		vm.status = false;
		vm.ativado = "";
		vm.buscarPorLetra = buscarPorLetra;
		vm.carregarAutores = carregarAutores;
		vm.true = verdadeiro;
		vm.false = falso;

		carregarAutores();

		function verdadeiro() {
			vm.paginaAutor = 1;
			vm.status = false;
			vm.ativado = ""
		}

		function falso() {
			vm.ativado = "";
			vm.paginaPorLetra = 1;
			vm.status = true;
		}

		function carregarAutores() {
			return autorService.carregarAutores(vm.paginaAutor)
				.then(function (data) {
					vm.autor = data.content;
					vm.totalElementos = data.totalElements;
				})
		}

		function buscarPorLetra(letra) {
			if (letra != null) {
				vm.ativado = letra;
			}
			return autorService.buscarAutorPorLetra(vm.ativado, vm.paginaPorLetra)
				.then(function (data) {
					vm.autor = data.content;
					vm.totalElementos1 = data.totalElements;
				})
		}
	}
})();