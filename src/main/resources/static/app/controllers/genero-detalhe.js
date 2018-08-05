(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('generoDetalheController', generoDetalheController);

	//Injeta as dependências
	generoDetalheController.$inject = ['$http', '$stateParams'];

	function generoDetalheController($http, $stateParams) {

		var vm = this;

		vm.generosDetalhe = [];
		vm.pagina = 1;
		vm.generoId = $stateParams.generoId;

		carregarGeneros();

		function carregarGeneros(Genero) {
			$http({
				method: 'GET',
				url: '/user/genero/' + vm.generoId + "?page=" + vm.pagina
			}).then(function (response) {
				vm.generosDetalhe = response.data.content;
				vm.items = response.data.totalElements;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}
	}
})();