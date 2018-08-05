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
		vm.palavraPesquisada = pesquisaService.getValue();

		pesquisarNome();

		function pesquisarNome() {
			$http({
				method: 'GET',
				url: '/user/manga/nome/' + vm.palavraPesquisada + "?page=" + vm.pagina
			}).then(function (res) {
				vm.manga = res.data.content;
				vm.totalEmentos = res.data.totalElements;
			}, function (res) {
				console.log(res);
				console.log(res.data);
			})
		}
	}
})();