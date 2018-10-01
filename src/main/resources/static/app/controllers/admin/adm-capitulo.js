(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admCapituloController', admCapituloController);

	//Injeta as dependências
	admCapituloController.$inject = ['mangaService', 'grupoService', 'capituloService', 'uploadService'];

	function admCapituloController(mangaService, grupoService, capituloService, uploadService) {

		var vm = this;

		vm.capitulo = {};
		vm.pagina = {};
		vm.paginas = [];
		vm.paginaErro = [];
		vm.Mangas = [];
		vm.Grupos = [];
		vm.capituloManga = [];
		vm.listaCapitulos = [];
		vm.mangaId = [];
		vm.mensagem = "";
		vm.capituloPorManga = capituloPorManga;
		vm.salvarCapitulos = salvarCapitulos;
		vm.uploadPaginas = uploadPaginas;
		vm.cancelarCapitulo = cancelarCapitulo;
		vm.cancelarPagina = cancelarPagina;
		vm.listaCapitulosPorManga = listaCapitulosPorManga;
		vm.excluirCapitulo = excluirCapitulo;
		vm.editarCapitulo = editarCapitulo;
		vm.limparPaginas = limparPaginas;

		carregarMangas();
		carregarGrupos();

		vm.closeMsg = closeMsg;

		function closeMsg() {
			vm.mensagem = "";
		}

		function editarCapitulo(capitulo) {
			vm.capitulo = capitulo;
		}

		function listaCapitulosPorManga() {
			return capituloService.listaCapitulosPorManga(vm.mangaId)
				.then(function (data) {
					vm.listaCapitulos = data;
				})
		}

		function excluirCapitulo(capitulo) {
			return capituloService.excluirCapitulo(capitulo)
				.then(function (data) {
					vm.mensagem = data;
					listaCapitulosPorManga();
				})
		}

		function carregarMangas() {
			return mangaService.listaMangas()
				.then(function (data) {
					vm.Mangas = data;
				})
		}

		function carregarGrupos() {
			return grupoService.listaGrupos()
				.then(function (data) {
					vm.Grupos = data;
				})
		}

		function capituloPorManga() {
			return capituloService.carregarCapituloPorManga(vm.pagina.manga)
				.then(function (data) {
					vm.capituloManga = data;
				})
		}

		function salvarCapitulos() {
			if (vm.formCapitulo.$valid) {
				return capituloService.salvarCapitulos(vm.capitulo)
					.then(function (data) {
						vm.mensagem = data;
						cancelarCapitulo();
						cancelarPagina();
					})
			} else {
				vm.mensagem = "Cadastro Nao realizado";
			}
		}

		function uploadPaginas(paginas, paginaErro) {
			vm.paginas = paginas;
			vm.paginaErro = paginaErro;
			if (paginas.length && vm.formPagina.$valid) {
				angular.forEach(paginas, function (pagina, count) {
					return uploadService.salvarPaginas(paginas, vm.pagina.descricao, vm.pagina.capitulo, count)
						.then(function (data) {
							var ultimoIndex = vm.paginas[vm.paginas.length - 1];
							vm.progresso = 100;
							if (ultimoIndex == pagina) {
								cancelarPagina();
								vm.mensagemPagina = data;
							}
						})
				})
			} else {
				vm.mensagemPagina = "Não foi salvo";
			}
		}

		function cancelarCapitulo() {
			vm.capitulo = {};
			vm.formCapitulo.$setPristine();
			vm.formCapitulo.$setUntouched();
		}

		function cancelarPagina() {
			vm.pagina = {};
			vm.mangaId = "";
			vm.listaCapitulos = "";
			vm.formPagina.$setUntouched();
			vm.formPagina.$setPristine();
		}

		function limparPaginas() {
			vm.paginas = {};
			vm.paginaErro = [];
		}
	}
})();