(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('pesquisaController', pesquisaController);

	//Injeta as dependências
	pesquisaController.$inject = ['pesquisaService'];

	function pesquisaController(pesquisaService) {

		var vm = this;

		vm.totalEmentos = [];
		vm.manga = [];
		vm.pagina = 1;
		vm.palavraPesquisada = pesquisaService.getNome();
		vm.mensagem = "";
		vm.pesquisarNome = pesquisarNome;

		pesquisarNome();

		function pesquisarNome() {
			return pesquisaService.pesquisaNome(vm.palavraPesquisada, vm.pagina)
				.then(function (data) {
					vm.manga = data.content;
					vm.totalEmentos = data.totalElements;
					if (!vm.totalEmentos) {
						vm.mensagem = "nada encontrado";
					}
				})
		}
	}
})();