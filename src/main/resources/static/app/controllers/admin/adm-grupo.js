(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('adminGruposController', adminGruposController);

	//Injeta as dependências
	adminGruposController.$inject = ['grupoService'];

	function adminGruposController(grupoService) {

		var vm = this;

		vm.grupos = [];
		vm.grupo = {};
		vm.totalElementos = [];
		vm.pagina = 1;
		vm.carregarGrupos = carregarGrupos;
		vm.salvarGrupos = salvarGrupos;
		vm.deletarGrupos = deletarGrupos;
		vm.alterarGrupos = alterarGrupos;
		vm.cancelarGrupo = cancelarGrupo;

		carregarGrupos();

		vm.closeMsg = closeMsg;

		function closeMsg() {
			vm.mensagem = "";
		}

		function carregarGrupos() {
			return grupoService.carregarGrupos(vm.pagina)
				.then(function (data) {
					vm.grupos = data.content;
					vm.totalElementos = data.totalElementos;
				})
		}

		function salvarGrupos() {
			if (vm.formGrupo.$valid) {
				return grupoService.salvarGrupos(vm.grupo)
					.then(function (data) {
						vm.grupo = {};
						carregarGrupos();
						vm.formGrupo.$setPristine(true);
						vm.mensagem = data;
					})
			}
			else {
				vm.mensagem = "Error no Formulario";
			}
		}

		function deletarGrupos(grupo) {
			return grupoService.excluirGrupos(grupo)
				.then(function (data) {
					vm.mensagem = data;
					cancelarGrupo();
					carregarGrupos();
				})
		}

		function cancelarGrupo() {
			vm.grupo = {};
			vm.formGrupo.$setPristine(true);
		}

		function alterarGrupos(grupo) {
			vm.grupo = grupo;
		}
	}
})();