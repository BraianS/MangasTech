(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admMangaController', admMangaController);

	//Injeta as dependências
	admMangaController.$inject = ['$filter', 'mangaService', 'autorService', 'generoService'];

	function admMangaController($filter, mangaService, autorService, generoService) {

		var vm = this;

		vm.manga = {};
		vm.autor = [];
		vm.Mangas = [];
		vm.pagina = 1;
		vm.totalElementos = [];
		vm.generos = [];
		vm.recebeImagem = [];
		vm.selecionaImagem = selecionaImagem;
		vm.status = ["COMPLETO", "LANCANDO", "PAUSADO"];
		vm.Selecionar = Selecionar;
		vm.salvarMangas = salvarMangas;
		vm.carregarGeneros = carregarGeneros;
		vm.alterarMangas = alterarMangas;
		vm.excluirMangas = excluirMangas;
		vm.cancelarMangas = cancelarMangas;
		vm.editarUsuario = false;
		vm.show = false;
		vm.submit = submit;
		vm.carregarMangas = carregarMangas;
		vm.closeMsg = closeMsg;
		vm.closeCapa = closeCapa;

		vm.years = [{ value: '2021', disabled: true }];
		for (var i = 2020; i >= 1990; i--) {
			vm.years.push({ value: i });
		}

		carregarMangas();
		carregarAutor();
		carregarGeneros();

		function closeMsg() {
			vm.show = false;
			vm.mensagem = "";
		}

		function closeCapa() {
			vm.errorImage = [];
		}

		function selecionaImagem(imagem, errorImage) {
			vm.recebeImagem = imagem[0];
			vm.errorImage = errorImage[0];
		}

		function salvarMangas() {
			if (vm.formNovoManga.$valid) {
				return mangaService.salvarMangas(vm.manga, vm.recebeImagem)
					.then(function (data) {
						vm.recebeImagem = "";
						carregarMangas();
						carregarGeneros();
						vm.mensagem = data;
						cancelarMangas();
					});
			} else {
				alert("Erro no Formulario");
			}
		}

		function Selecionar(argument) {
			vm.manga.genero = $filter('filter')(vm.generos, { Selecionado: true });
		}

		function carregarGeneros() {
			return generoService.listaGeneros()
				.then(function (data) {
					vm.generos = data;
				})
		}

		function carregarAutor() {
			return autorService.listaAutores()
				.then(function (data) {
					vm.autor = data;
				})
		}

		function excluirMangas(Manga) {
			return mangaService.excluirMangas(Manga)
				.then(function (data) {
					vm.mensagem = data;
					cancelarMangas();
					carregarMangas();
				})
		}

		function carregarMangas() {
			return mangaService.carregarMangas(vm.pagina)
				.then(function (data) {
					vm.Mangas = data.content;
					vm.totalElementos = data.totalElements;
				});
		}

		function updateManga() {
			if (vm.formNovoManga.$valid) {
				return mangaService.atualizarManga(vm.manga.id,vm.manga, vm.recebeImagem)
					.then(function (data) {
						vm.manga = {};
						vm.recebeImagem = [];
						vm.mensagem = data;
						carregarMangas();
						carregarGeneros();
						vm.formNovoManga.$setPristine(true);
					});
			}
			else {
				alert("Não foi salvo");
			}
		}

		function alterarMangas(manga) {
			vm.manga = manga;
			vm.editarUsuario = true;
		}

		function cancelarMangas() {
			vm.manga = {};
			vm.recebeImagem = [];
			vm.formNovoManga.$setPristine(true);
			vm.formNovoManga.$setUntouched(true);
			angular.element("#capas").val(null);
		}

		function submit() {
			if (vm.editarUsuario) {
				updateManga();
				vm.editarUsuario = false;
			}
			else {
				salvarMangas();
			}
		}
	}
})();