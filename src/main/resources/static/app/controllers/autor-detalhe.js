(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('autorDetalheController', autorDetalheController);

	//Injeta as dependências
	autorDetalheController.$inject = ['$http', '$stateParams'];

	function autorDetalheController($http, $stateParams) {

		var vm = this;

		vm.autor = [];
		vm.pagina = 1;
		vm.autorId = $stateParams.autorId;
		vm.totalElementos = [];
		vm.carregarAutor = carregarAutor;

		carregarAutor();

		function carregarAutor() {
			$http({
				method: 'GET',
				url: '/user/autor/' + vm.autorId + '?page=' + vm.pagina
			}).then(function (response) {
				vm.autor = response.data.content;
				vm.totalElementos = response.data.totalElements;
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		}
	}
})();