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

		vm.progresssBar = false;
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
		vm.selecionarPagina = selecionarPagina;

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
			return mangaService.buscarMangaPorId(vm.mangaId)
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
				return capituloService.salvarCapitulos(vm.capitulo, vm.paginas)
					.then(function (data) {
						vm.progresssBar = false;
						vm.mensagem = data;
						cancelarCapitulo();
						cancelarPagina();
					})
			} else {
				vm.mensagem = "Cadastro Nao realizado";
			}
		}

		function selecionarPagina(files, errFiles) {
			vm.paginas = files;
			vm.paginaErro = errFiles;
		}

		function uploadPaginas() {
			if (vm.paginas && vm.formPagina.$valid) {
				angular.forEach(vm.paginas, function (file, count) {
					return uploadService.salvarPaginas(file, vm.pagina.capitulo, vm.pagina.contador[count])
						.then(function (data) {
							console.log(data);
						});
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
			angular.element("#pagina").val(null);
			vm.paginas = {};
			vm.paginaErro = {};
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