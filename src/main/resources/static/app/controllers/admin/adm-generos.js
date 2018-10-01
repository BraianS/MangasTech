(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admGenerosController', admGenerosController);

	//Injeta as dependências
	admGenerosController.$inject = ['generoService'];

	function admGenerosController(generoService) {

		var vm = this;

		vm.genero = {};
		vm.generos = [];
		vm.carregarGeneros = carregarGeneros;
		vm.salvarGeneros = salvarGeneros;
		vm.deletarGeneros = deletarGeneros;
		vm.cancelarGeneros = cancelarGeneros;
		vm.alterarGeneros = alterarGeneros;
		vm.atualizarGeneros = atualizarGeneros;
		vm.usuarioEditado = false;
		vm.submit = submit;

		carregarGeneros();

		vm.closeMsg = closeMsg;

		function closeMsg() {
			vm.mensagem = "";
		}

		function carregarGeneros() {
			generoService.listaGeneros()
				.then(function (data) {
					vm.generos = data;
				})
		}

		function salvarGeneros() {
			if (vm.formGenero.$valid) {
				generoService.salvarGenero(vm.genero)
					.then(function (data) {
						vm.genero = {};
						vm.mensagem = data;
						carregarGeneros();
						vm.formGenero.$setPristine(true);
					})
			}
			else {
				vm.mensagem = "Erro No formulario";
			}
		}

		function atualizarGeneros() {
			generoService.atualizarGenero(vm.genero)
				.then(function (data) {
					vm.genero = {};
					vm.formGenero.$setPristine(true);
					vm.mensagem = data;
				})
		}

		function deletarGeneros(genero) {
			generoService.excluirGenero(genero)
				.then(function (data) {
					vm.mensagem = data;
					cancelarGeneros();
					carregarGeneros();
				})
		}

		function cancelarGeneros() {
			vm.usuarioEditado = true;
			vm.genero = {};
			vm.formGenero.$setPristine(true);
		}

		function alterarGeneros(genero) {
			vm.genero = genero;
			vm.usuarioEditado = true;
		}

		function submit() {
			if (vm.usuarioEditado) {
				atualizarGeneros();
			}
			else {
				salvarGeneros();
			}
		}
	}
})();