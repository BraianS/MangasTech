(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admAutorController', admAutorController);

	//Injeta as dependências
	admAutorController.$inject = ['$http'];

	function admAutorController($http) {

		var vm = this;

		vm.autor = [];
		vm.autores = {};
		vm.pagina = 1;
		vm.mensagem = "";
		vm.cancelar = cancelar;
		vm.excluirAutor = excluirAutor;
		vm.salvarAutores = salvarAutores;
		vm.alterarAutores = alterarAutores;
		vm.autorEditado = false;
		vm.submit = submit;

		carregarAutores();

		function carregarAutores() {
			$http.get('/user/autor?page=' + vm.pagina).then(function (response) {
				vm.autor = response.data.content;
				vm.totalItems = response.data.totalElements;
			}, function (response) {
				console.log(response.content);
			})
		}

		function salvarAutores() {
			if (vm.formAutor.$valid) {
				$http({
					method: 'POST',
					url: '/admin/autor', data: vm.autores
				}).then(function (response) {
					vm.autores = {}
					vm.formAutor.$setPristine(true);
					vm.mensagem = "Salvo com Sucesso";
					carregarAutores();
				}, function (response) {
					vm.mensagem = response.data.message;
					console.log(response.data);
					console.log(response.status);
				})
			}
			else {
				vm.mensagem = "Erro No Formulario";
			}
		}

		function excluirAutor(autor) {
			$http({
				method: 'DELETE',
				url: '/admin/autor/' + autor.id
			}).then(function (response) {
				carregarAutores();
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			})
		}

		function updateAutor() {
			$http({
				method: 'PUT',
				url: '/admin/autor', data: vm.autores
			}).then(function (response) {
				vm.autores = {};
				vm.formAutor.$setPristine(true);
				vm.mensagem = "Atualizado";
			}, function (response) {
				console.log(response);
				console.log(response.data);
				vm.mensagem = response.data.message;
			});
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