(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('grupoDetalheController', grupoDetalheController);

	//Injeta as dependências
	grupoDetalheController.$inject = ['$http', '$stateParams'];

	function grupoDetalheController($http, $stateParams) {

		var vm = this;

		vm.grupo = [];
		vm.pagina = 1;
		vm.grupoId = $stateParams.gruposId;
		vm.totalElementos = [];
		vm.carregarGrupos = carregarGrupos;

		carregarGrupos();

		function carregarGrupos() {
			$http({
				method: 'GET',
				url: '/user/grupo/' + vm.grupoId + "?page=" + vm.pagina
			}).then(function (response) {
				vm.grupo = response.data.content;
				vm.totalElementos = response.data.totalElements;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}
	}
})();