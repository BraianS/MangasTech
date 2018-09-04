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

		function closeMsg(){
			vm.mensagem = "";
		}

		function editarCapitulo(capitulo){
			vm.capitulo = capitulo;			
		}

		function listaCapitulosPorManga() {
			$http({
				method: 'GET',
				url: '/user/capitulo/lista/' + vm.mangaId
			}).then(function (response) {
				vm.listaCapitulos = response.data;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}

		function excluirCapitulo(capitulo) {
			$http({
				method: 'DELETE',
				url: '/user/capitulo/lista/' + capitulo.id
			}).then(function (response) {
				vm.mensagem = "Capitulo: "+capitulo.capitulo+ " Deletado";
				listaCapitulosPorManga();
			}, function (response) {
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
				url: '/user/capitulo/' + vm.pagina.manga
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
					cancelarCapitulo();
					cancelarPagina();
					vm.mensagem = "Salvo com sucesso";
				}, function (response) {
					vm.mensagem = response.data.message;
					console.log(response.data);
					console.log(response.status);
				});
			} else {
				vm.mensagem = "Cadastro Nao realizado";
			}
		}

		function uploadPaginas(paginas, paginaErro) {
			vm.paginas = paginas;
			vm.paginaErro = paginaErro;
			if (paginas.length && vm.formPagina.$valid) {
				angular.forEach(paginas, function (pagina, count) {
					Upload.upload({
						url: '/admin/pagina',
						method: 'POST',
						arrayKey: '',
						data: { paginas: pagina, nome: vm.pagina.descricao, capitulo: vm.pagina.capitulo, numCapitulo: count }
					}).then(function (response) {
						var ultimoIndex = vm.paginas[vm.paginas.length - 1];
						if (ultimoIndex == pagina) {
							cancelarPagina();
							vm.mensagemPagina = "Salvo com sucesso";
						};
					}, function (response) {
						vm.mensagem = response.data.message;
						console.log(response);
						console.log(response.status);
					}, function (evt) {
						vm.progresso = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
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
			vm.pagina = {};
			vm.mangaId = "";
			vm.listaCapitulos = "";			
			vm.formPagina.$setUntouched();
			vm.formPagina.$setPristine();
		}

		function limparPaginas(){
			vm.paginas = {};
			vm.paginaErro = [];
		}
	}
})();