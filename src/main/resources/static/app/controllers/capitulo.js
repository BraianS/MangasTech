(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('capituloController', capituloController);

	//Injeta as dependências
	capituloController.$inject = ['capituloService', '$stateParams'];

	function capituloController(capituloService, $stateParams) {

		var vm = this;

		vm.pagina = 1;
		vm.totalElementos = [];
		vm.numeroDePaginas = [];
		vm.mangas = [];
		vm.capitulo = [];
		vm.capituloId = $stateParams.capituloId;
		vm.mangaId = $stateParams.mangaId;
		vm.carregarCapitulo = carregarCapitulo;
		vm.listaNumeroDePaginas = listaNumeroDePaginas;
		vm.mudarDeCapitulo = mudarDeCapitulo;

		listaNumeroDePaginas();
		carregarMangaECapitulos();
		carregarCapitulo();

		function listaNumeroDePaginas() {
			return capituloService.listaNumerosDePaginas(vm.capituloId)
				.then(function (data) {
					vm.numeroDePaginas = data;
				})
		}

		function carregarMangaECapitulos() {
			return capituloService.listaCapitulosPorManga(vm.mangaId)
				.then(function (data) {
					vm.mangas = data;
				})
		}

		function carregarCapitulo() {
			return capituloService.carregarPaginas(vm.capituloId, vm.pagina)
				.then(function (data) {
					vm.totalElementos = data.totalElements;
					vm.paginas = data.content;
					vm.capitulo = vm.paginas[0].capitulo.id;
				})
		}

		vm.proximaPagina = function () {
			if (vm.pagina < vm.totalElementos) {
				vm.pagina += 1;
				carregarCapitulo();
			} else {
				vm.pagina = vm.pagina;
				console.log("Limite de paginas");
			}
		}

		function mudarDeCapitulo() {
			return capituloService.mudarDeCapitulo(vm.mangaId, vm.capitulo, vm.pagina)
				.then(function (data) {
					vm.totalElementos = data.totalElements;
					vm.paginas = data.content;
				})
		}

		/* bind para pegar o evento das setas do teclado */
		$('#capituloDiv').bind('keydown', function (e) {
			//Se a seta pressionada for da Esquerda
			if (e.keyCode === 37) {
				if (vm.pagina > 1) {
					vm.pagina--;
					carregarCapitulo();
				}
				else {
					vm.pagina = vm.pagina;
					console.log('Limite de paginas');
				}
				console.log("Esquerda");
				return false;
			}

			//Se a seta pressionada for da Direita
			if (e.keyCode === 39) {
				if (vm.pagina < vm.totalElementos) {
					vm.pagina++;
					carregarCapitulo();
				} else {
					vm.pagina = vm.pagina;
					console.log('Limite de paginas');
				}
				console.log("Direita");
				return false;
			}
		});

		//Focus
		$(function () {
			$('#capituloDiv').focus();
		});
	}
})();