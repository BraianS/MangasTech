(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admMangaController', admMangaController);

	//Injeta as dependências
	admMangaController.$inject = ['$http', '$filter'];

	function admMangaController($http, $filter) {

		var vm = this;

		vm.manga = {};
		vm.autor = [];
		vm.Mangas = [];
		vm.pagina = 1;
		vm.totalElementos = [];
		vm.generos = [];
		vm.recebeImagem = {};
		vm.selecionaImagem = selecionaImagem;
		vm.status = ["COMPLETO", "LANCANDO", "PAUSADO"];
		vm.Selecionar = Selecionar;
		vm.salvarMangas = salvarMangas;
		vm.carregarGeneros = carregarGeneros;
		vm.alterarMangas = alterarMangas;
		vm.excluirMangas = excluirMangas;
		vm.cancelarMangas = cancelarMangas;
		vm.editarUsuario = false;
		vm.submit = submit;
		vm.carregarMangas = carregarMangas;

		vm.years = [{ value: '2021', disabled: true }];
		for (var i = 2020; i >= 1990; i--) {
			vm.years.push({ value: i });
		}

		carregarMangas();
		carregarAutor();
		carregarGeneros();


		function selecionaImagem(imagem) {
			vm.recebeImagem = imagem;
		}

		function salvarMangas() {
			if (vm.formNovoManga.$valid) {
				$http({
					method: 'POST', url: '/admin/manga',
					headers: { 'Content-Type': undefined },
					transformRequest: function (data) {
						var formData = new FormData();
						formData.append('mangas', new Blob([angular.toJson(data.mangas)], {
							type: "application/json"
						}));
						formData.append("file", data.file);
						return formData;
					},
					data: { mangas: vm.manga, file: vm.recebeImagem },
				}).then(function (response) {
					vm.manga = {};
					vm.recebeImagem = "";
					carregarMangas();
					carregarGeneros();
					vm.mensagem = "Salvo com Sucesso";
					vm.formNovoManga.$setPristine(true);
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
					vm.mensagem = response.data.message;
				});
			}
			else {
				alert('Não foi salvo');
			}
		}

		function Selecionar(argument) {
			vm.manga.genero = $filter('filter')(vm.generos, { Selecionado: true });
			console.log($filter('filter')(vm.generos, { Selecionado: true }));
		}

		function carregarGeneros() {
			$http({
				method: 'GET', url: '/user/genero/lista'
			}).then(function (response) {
				vm.generos = response.data;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
		}

		function carregarAutor() {
			$http({
				method: 'GET', url: '/user/autor/lista'
			}).then(function (response) {
				vm.autor = response.data;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
		}

		function excluirMangas(Manga) {
			$http({
				method: 'DELETE', url: '/admin/manga/' + Manga.id
			}).then(function (response) {
				carregarMangas();
			}, function (response) {
				console.log(response.data);
				console.log(response.status)
			});
		}

		function carregarMangas() {
			$http({
				method: 'GET',
				url: '/user/manga?page=' + vm.pagina
			}).then(function (response) {
				vm.Mangas = response.data.content;
				vm.totalElementos = response.data.totalElements;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			});
		}

		function updateManga(){
			if(vm.formNovoManga.$valid){
				$http({
					method: 'PUT',
					url: '/admin/manga',
					headers: { 'Content-Type': undefined },
					transformRequest: function (data) {
						var formData = new FormData();
						formData.append('mangas', new Blob([angular.toJson(data.mangas)], {
							type: "application/json"
						}));
						formData.append("file",data.file);
						return formData;
					},
					data: { mangas: vm.manga,file: vm.recebeImagem}
				}).then(function(response) {
					vm.manga = {};
					vm.recebeImagem = "";
					carregarMangas();
					carregarGeneros();
					vm.formNovoManga.$setPristine(true);
					vm.mensagem = "Alterado com sucesso";
				}, function(response) {
					console.log(response);
					console.log(response.data);
				})
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
			vm.recebeImagem = "";
			vm.formNovoManga.$setPristine(true);
		}

		function submit(){
			if(vm.editarUsuario){
				updateManga();
				vm.editarUsuario = false;
			}
			else {
				salvarMangas();
			}
		}
	}
})();