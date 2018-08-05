
(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('generosController', generosController);

	//Injeta as dependências
	generosController.$inject = ['$http'];

	function generosController($http) {

		var vm = this;

		vm.genero = [];
		vm.carregarGeneros = carregarGeneros;

		carregarGeneros();

		function carregarGeneros() {
			$http({
				method: 'GET',
				url: '/user/genero'
			}).then(function (response) {
				vm.genero = response.data.content;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
		}
	}
})();