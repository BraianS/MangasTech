(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admGenerosController', admGenerosController);

	//Injeta as dependências
	admGenerosController.$inject = ['$http'];

	function admGenerosController($http) {

		var vm = this;

		vm.genero = {};
		vm.generos = [];
		vm.carregarGeneros = carregarGeneros;
		vm.salvarGeneros = salvarGeneros;
		vm.deletarGeneros = deletarGeneros;
		vm.cancelarGeneros = cancelarGeneros;
		vm.alterarGeneros = alterarGeneros;
		vm.usuarioEditado = false;

		carregarGeneros();

		function carregarGeneros() {
			$http({
				method: 'GET',
				url: '/user/genero'
			}).then(function (response) {
				vm.generos = response.data.content;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
		}

		function salvarGeneros() {
			if (vm.formGenero.$valid) {
				$http({
					method: 'POST',
					url: '/admin/genero', data: vm.genero
				}).then(function (response) {
					vm.genero = {};
					vm.carregarGeneros();
					vm.formGenero.$setPristine(true);
					vm.mensagem = "Salvo com Sucesso";
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
					vm.mensagem = response.data.message;
				});
			}
			else {
				vm.mensagem = "Erro No formulario";
			}
		}

		function deletarGeneros(genero) {
			$http({
				method: 'DELETE',
				url: "/admin/genero/" + genero.id
			}).then(function (response) {
				carregarGeneros();
			}, function (response) {
				console.log(response);
				console.log(response.data);
			});
		}
		

		function cancelarGeneros() {
			vm.usuarioEditado = true;
			vm.genero = {};
			vm.formGenero.$setPristine(true);
		}

		function alterarGeneros(genero) {
			vm.genero = genero;
		}

		function submit() {
			if(vm.usuarioEditado){
				salvarGeneros();
			}
			else {

			}
		}

	}
})();