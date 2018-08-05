(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('autorController', autorController);

	//Injeta as dependências
	autorController.$inject = ['$http'];

	function autorController($http) {

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
			$http({
				method: 'GET',
				url: '/user/autor?page=' + vm.paginaAutor
			}).then(function (response) {
				vm.autor = response.data.content;
				vm.totalElementos = response.data.totalElements;
			}, function (response) {
				console.log(response);
			})
		}

		function buscarPorLetra(letra) {
			if (letra != null) {
				vm.ativado = letra;
			} $http({
				method: 'GET',
				url: '/user/autor/letra/' + vm.ativado + "?page=" + vm.paginaPorLetra
			}).then(function (response) {
				vm.autor = response.data.content;
				vm.totalElementos1 = response.data.totalElements;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}
	}
})();