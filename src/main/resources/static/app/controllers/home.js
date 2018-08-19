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

		var data = new Date();
		var DiaHoje = data.getDate();
		var DiaOntem = data.getDate() - 1;
		var DiaAnteOntem = data.getDate() - 2;

		var mes = data.getMonth() + 1;
		var ano = data.getFullYear();

		vm.hoje = DiaHoje+'-'+mes+'-'+ano;
		vm.ontem = DiaOntem+'-'+mes+'-'+ano;
		vm.anteontem = DiaAnteOntem+'-'+mes+'-'+ano;

		vm.novosCapitulos = [];
		vm.novosMangas = [];
		vm.mangaCapitulo = [];
		vm.mangaUmDiaAtras = mangaUmDiaAtrass;
		vm.mangasDoisDiasAtras = mangasDoisDiasAtras;
		vm.ontemStatus = false;
		vm.anteOntemStatus = false;		

		carregarNovosCapitulos();
		carregarNovosMangas();

		function carregarNovosCapitulos() {
			$http({
				method: 'GET',
				url: '/user/capitulo/lista/ordenado?date=' + vm.hoje
			}).then(function (response) {
				vm.mangaCapitulo = response.data;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}

		function mangaUmDiaAtrass() {
			vm.ontemStatus = true;
			$http({
				method: 'GET',
				url: '/user/capitulo/lista/ordenado?date=' + vm.ontem
			}).then(function (response) {
				vm.MangasOntem = response.data;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}

		function mangasDoisDiasAtras() {
			vm.anteOntemStatus = true;
			$http({
				method: 'GET',
				url: '/user/capitulo/lista/ordenado?date=' + vm.anteontem
			}).then(function (response) {
				vm.MangasAnteOntem = response.data;
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