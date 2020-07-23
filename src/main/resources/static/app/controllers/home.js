(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('homeController', homeController);

	//Injeta as dependências
	homeController.$inject = ['autenticacaoService'];

	function homeController(autenticacaoService) {

		var vm = this;

		var data = new Date();
		var DiaHoje = data.getDate();
		var DiaOntem = data.getDate() - 1;
		var DiaAnteOntem = data.getDate() - 2;

		var mes = data.getMonth() + 1;
		var ano = data.getFullYear();

		vm.hoje = DiaHoje + '-' + mes + '-' + ano;
		vm.ontem = DiaOntem + '-' + mes + '-' + ano;
		vm.anteontem = DiaAnteOntem + '-' + mes + '-' + ano;

		vm.novosCapitulos = [];
		vm.novosMangas = [];
		vm.mangaCapitulo = [];
		vm.mangaUmDiaAtras = mangaUmDiaAtras;
		vm.mangasDoisDiasAtras = mangasDoisDiasAtras;
		vm.ontemStatus = false;
		vm.anteOntemStatus = false;
		vm.listaTop10MangasAcessados = [];

		carregarNovosCapitulos();
		carregarNovosMangas();
		carregarTop10MangasAcessados();

		function carregarNovosCapitulos() {
			return autenticacaoService.carregarNovosCapitulos(vm.hoje)
				.then(function (data) {
					vm.mangaCapitulo = data;
				})
		}

		function mangaUmDiaAtras() {
			return autenticacaoService.carregarMangasPorUmDia(vm.ontem)
				.then(function (data) {
					vm.MangasOntem = data;
					vm.ontemStatus = true;
				})
		}

		function mangasDoisDiasAtras() {
			return autenticacaoService.carregarMangasDoisDiasAtras(vm.anteontem)
				.then(function (data) {
					vm.MangasAnteOntem = data;
					vm.anteOntemStatus = true;
				})
		}

		function carregarNovosMangas() {
			return autenticacaoService.carregarNovosMangas()
				.then(function (data) {
					vm.novosMangas = data;
				})
		}

		function carregarTop10MangasAcessados() {
			return autenticacaoService.carregarTop10Mangas()
				.then(function (data) {
					vm.listaTop10MangasAcessados = data;
				})
		}

		vm.default = {
			animateIn: 'fadeIn',
			lazyLoad: true,
			autoplay: true,
			autoPlayTimeout: 2000,
			loop: false,
			dots: false,
			margin: 0,
			nav: true,
			rewind: true,
			autoplayHoverPause: true,
			navText: ["<i class='glyphicon glyphicon-chevron-left'></i>",
				"<i class='glyphicon glyphicon-chevron-right'></i>"],
			responsive: {
				0: {
					items: 2
				},
				500: {
					items: 2
				},
				600: {
					items: 3
				},
				768: {
					items: 4
				},
				1000: {
					items: 6
				}
			}
		};
	}
})();