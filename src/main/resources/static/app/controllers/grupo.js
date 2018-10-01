(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('grupoController', grupoController);

	//Injeta as dependências
	grupoController.$inject = ['grupoService'];

	function grupoController(grupoService) {

		var vm = this;

		var alfabeto = "abcdefghijklmnopqrstuvwxyz";
		vm.letra = alfabeto.toUpperCase().split("");

		vm.grupos = [];
		vm.paginaPorGrupos = 1;
		vm.paginaPorLetra = 1;
		vm.status = false;
		vm.ativado = "";
		vm.false = falso;
		vm.true = verdadeiro;
		vm.carregarGrupos = carregarGrupos;
		vm.buscarPorLetra = buscarPorLetra;

		carregarGrupos();

		function falso() {
			vm.status = false;
			vm.paginaPorGrupos = 1;
			vm.ativado = "";
		}

		function verdadeiro() {
			vm.status = true;
			vm.paginaPorLetra = 1;
		}

		function carregarGrupos() {
			return grupoService.carregarGrupos(vm.paginaPorGrupos)
				.then(function (data) {
					vm.grupos = data.content;
					vm.totalElements = data.totalElements;
				})
		}

		function buscarPorLetra(letra) {
			if (letra != null) {
				vm.ativado = letra;
			}
			return grupoService.buscarPorLetra(vm.ativado, vm.paginaPorLetra)
				.then(function (data) {
					vm.grupos = data.content;
					vm.totalElementos2 = data.totalElements;
				})
		}
	}
})();