angular
	.module("appCliente")
	.controller("grupoController", function ($http) {
		var vm = this;

		vm.Grupos = [];
		vm.paginaPorGrupos = 1;
		vm.paginaPorLetra = 1;
		vm.status = false;
		vm.ativado = "";

		var alfabeto = "abcdefghijklmnopqrstuvwxyz";

		vm.letra = alfabeto.toUpperCase().split("");

		vm.false = function () {
			vm.status = false;
			vm.paginaPorGrupos = 1;
			vm.ativado = "";
		};

		vm.true = function () {
			vm.status = true;
			vm.paginaPorLetra = 1;
		};

		vm.carregarGrupos = function () {
			$http({
				method: 'GET',
				url: '/user/grupo?page=' + vm.paginaPorGrupos
			}).then(function (response) {
				vm.Grupos = response.data.content;
				vm.totalElementos = response.data.totalElements;
				console.log(response.data.content);
			}, function (response) {
				console.log(response.data.content);
				console.log(response.status);
			})
		};

		vm.buscarPorLetra = function (letra) {
			if (letra != null) {
				vm.ativado = letra;
			}
			$http({
				method: 'GET',
				url: '/user/grupo/letra/' + vm.ativado + "?page=" + vm.paginaPorLetra
			}).then(function (response) {
				vm.Grupos = response.data.content;
				vm.totalElementos2 = response.data.totalElements;
				console.log(response.data.content);
			}, function (response) {
				console.log(response.data);
				console.log(response.data.content);
			})
		};

		vm.carregarGrupos();
	});