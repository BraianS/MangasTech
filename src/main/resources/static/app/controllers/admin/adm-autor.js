(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admAutorController', admAutorController);

	//Injeta as dependências
	admAutorController.$inject = ['autorService'];

	function admAutorController(autorService) {

		var vm = this;

		vm.autor = [];
		vm.autores = {};
		vm.pagina = 1;
		vm.mensagem = "";
		vm.cancelar = cancelar;
		vm.excluirAutor = excluirAutor;
		vm.salvarAutores = salvarAutores;
		vm.alterarAutores = alterarAutores;
		vm.carregarAutores = carregarAutores;
		vm.autorEditado = false;
		vm.submit = submit;

		carregarAutores();

		vm.closeMsg = closeMsg;

		function closeMsg() {
			vm.mensagem = "";
		}

		function carregarAutores() {
			return autorService.carregarAutores(vm.pagina)
				.then(function (data) {
					vm.autor = data.content;
					vm.totalItems = data.totalElements;
				})
		}

		function salvarAutores() {
			if (vm.formAutor.$valid) {
				return autorService.salvarAutor(vm.autores)
					.then(function (data) {
						vm.mensagem = data;
						cancelar();
						carregarAutores();
					})
			}
			else {
				vm.mensagem = "Erro No Formulario";
			}
		}

		function excluirAutor(autor) {
			return autorService.excluirAutor(autor)
				.then(function (data) {
					vm.mensagem = data;
					cancelar();
					carregarAutores();
				});
		}

		function updateAutor() {
			return autorService.atualizarAutor(vm.autores)
				.then(function (data) {
					vm.mensagem = data;
					cancelar();
					carregarAutores();
				})
		}

		function cancelar() {
			vm.autores = {};
			vm.formAutor.$setPristine(true);
		}

		function alterarAutores(autor) {
			vm.autores = autor;
			vm.autorEditado = true;
		}

		function submit() {
			if (vm.autorEditado) {
				updateAutor();
				vm.autorEditado = false;
			}
			else {
				salvarAutores();
			}
		}
	}
})();