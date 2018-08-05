(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('grupoController', grupoController);

	//Injeta as dependências
	grupoController.$inject = ['$http'];

	function grupoController($http) {

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
			$http({
				method: 'GET',
				url: '/user/grupo?page=' + vm.paginaPorGrupos
			}).then(function (response) {
				vm.grupos = response.data.content;
				vm.totalElementos = response.data.totalElements;
			}, function (response) {
				console.log(response.data.content);
				console.log(response.status);
			})
		}

		function buscarPorLetra(letra) {
			if (letra != null) {
				vm.ativado = letra;
			}
			$http({
				method: 'GET',
				url: '/user/grupo/letra/' + vm.ativado + "?page=" + vm.paginaPorLetra
			}).then(function (response) {
				vm.grupos = response.data.content;
				vm.totalElementos2 = response.data.totalElements;
			}, function (response) {
				console.log(response.data);
				console.log(response.data.content);
			})
		}
	}
})();