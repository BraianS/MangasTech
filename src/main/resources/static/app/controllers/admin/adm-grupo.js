(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('adminGruposController', adminGruposController);

	//Injeta as dependências
	adminGruposController.$inject = ['$http'];

	function adminGruposController($http) {

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

		function carregarGrupos() {
			$http({
				method: 'GET',
				url: '/user/grupo?page=' + vm.pagina
			}).then(function (response) {
				vm.grupos = response.data.content;
				vm.totalElementos = response.data.totalElements;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			})
		}

		function salvarGrupos() {
			if (vm.formGrupo.$valid) {
				$http({
					method: 'POST',
					url: '/admin/grupo', data: vm.grupo
				}).then(function (response) {
					vm.grupo = {};
					vm.carregarGrupos();
					vm.formGrupo.$setPristine(true);
					vm.mensagem = "Salvo com Sucesso"
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
					vm.mensagem = response.data.message;
				})
			}
			else {
				vm.mensagem = "Error no Formulario";
			}
		}

		function deletarGrupos(grupos) {
			$http({
				method: 'DELETE',
				url: '/admin/grupo/' + grupos.id
			}).then(function (response) {
				vm.carregarGrupos();
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
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