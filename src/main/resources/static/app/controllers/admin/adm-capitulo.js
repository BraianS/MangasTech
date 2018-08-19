(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admCapituloController', admCapituloController);

	//Injeta as dependências
	admCapituloController.$inject = ['$http', 'Upload'];

	function admCapituloController($http, Upload) {

		var vm = this;

		vm.capitulo = {};
		vm.Mangas = [];
		vm.Grupos = [];
		vm.capituloManga = [];
		vm.capituloIdSelecionado = [];
		vm.mangaIdSelecionado = [];
		vm.mangaIdCapitulo = [];
		vm.mensagem = "";
		vm.capituloPorManga = capituloPorManga;
		vm.salvarCapitulos = salvarCapitulos;
		vm.uploadPaginas = uploadPaginas;
		vm.cancelarCapitulo = cancelarCapitulo;
		vm.cancelarPagina = cancelarPagina;
		vm.muda = mudar;
		vm.mangaId = [];
		vm.excluir = excluir;
		vm.atual = [];
		vm.editarCapitulo = editarCapitulo;	
		carregarMangas();
		carregarGrupos();

		function editarCapitulo(capitulo){
			vm.capitulo = capitulo
		}

		function mudar(){
			$http({
				method: 'GET',
				url:'/user/capitulo/lista/'+vm.mangaId
			}).then(function(response) {				
				vm.listaCapitulos = response.data;
			}, function(response) {
				console.log(response);
				console.log(response.data);
			})
		}

		function excluir(id){
			$http({
				method: 'DELETE',
				url:'/user/capitulo/lista/'+id
			}).then(function(response){
				mudar();			
			}, function(response) {
				console.log(response);
				console.log(response.data);
			});
		}

		function carregarMangas() {
			$http({
				method: 'GET',
				url: '/user/manga/lista'
			}).then(function (response) {
				vm.Mangas = response.data;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
		}

		function carregarGrupos() {
			$http({
				method: 'GET',
				url: '/user/grupo/lista'
			}).then(function (response) {
				vm.Grupos = response.data;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
		}

		function capituloPorManga() {
			$http({
				method: 'GET',
				url: '/user/capitulo/' + vm.mangaIdSelecionado
			}).then(function (response) {
				vm.capituloManga = response.data;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}

		function salvarCapitulos() {
			if (vm.formCapitulo.$valid) {
				$http({
					method: 'POST',
					url: '/admin/capitulo', data: vm.capitulo
				}).then(function (response) {
					vm.capitulo = {};
					vm.formCapitulo.$setPristine(true);
					vm.mensagem = "Salvo com sucesso";
				}, function (response) {
					vm.mensagem = response.data.message;
					console.log(response.data);
					console.log(response.status);
				});
			}
			else {
				vm.mensagem = "Cadastro Nao realizado";
			}
		}
		
		function uploadPaginas(fotos) {
			if (vm.formPagina) {
				if (fotos && fotos.length) {
					Upload.upload({
						url: '/admin/pagina',
						method: 'POST',
						params: { nome: vm.nomeCapitulo, capitulo: vm.capituloIdSelecionado },
						arrayKey: '',
						data: { fotos: fotos },
						headers: { 'Content-Type': undefined }
					}).then(function (response) {						
						console.log("sucesso");
						vm.mensagemPagina = "Salvo com sucesso";
						vm.formPagina.$setPristine(true);
					}), function (response) {
						vm.mensagem = response.data.message;
						console.log(response);
						console.log(response.data);
					}
				}
			}
			else {
				vm.mensagemPagina = "Não foi salvo";
			}
		}

		function cancelarCapitulo() {
			vm.capitulo = {};
			vm.formCapitulo.$setPristine(true);
		}

		function cancelarPagina() {
			vm.mangaIdCapitulo = "";
			vm.mangaIdSelecionado = {};
			vm.capituloIdSelecionado = {};
			vm.formPagina.$setPristine(true);
		}
	}
})();