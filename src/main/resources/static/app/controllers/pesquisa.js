(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('pesquisaController', pesquisaController);

	//Injeta as dependências
	pesquisaController.$inject = ['$http', 'pesquisaService'];

	function pesquisaController($http, pesquisaService) {

		var vm = this;

		vm.totalEmentos = [];
		vm.manga = [];
		vm.pagina = 1;
		vm.pesquisarNome = pesquisarNome;
		vm.palavraPesquisada = pesquisaService.getNome();
		vm.mensagem = "";

		pesquisarNome();

		function pesquisarNome() {
			$http({
				method: 'GET',
				url: '/user/manga/nome/' + vm.palavraPesquisada + "?page=" + vm.pagina
			}).then(function (res) {
				vm.manga = res.data.content;
				vm.totalEmentos = res.data.totalElements;
				if (!vm.totalEmentos) {
					vm.mensagem = "Nada Encontrado";
				}
			}, function (res) {
				console.log(res);
				console.log(res.data);
			})
		}
	}
})();