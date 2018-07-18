angular
	.module("appCliente")
	.controller("autorController", ['$http', function ($http) {

		var vm = this;

		vm.autor = [];
		vm.paginaAutor = 1;
		vm.paginaPorLetra = 1;
		vm.status = false;
		vm.ativado = "";

		var alfabeto = "abcdefghijklmnopqrstuvwxyz";

		vm.letra = alfabeto.toUpperCase().split("");


		vm.false = function () {
			vm.paginaAutor = 1;
			vm.status = false;
			vm.ativado = ""
		};

		vm.true = function () {
			vm.paginaPorLetra = 1;
			vm.status = true;
		};
		/* 
			Atualiza os Autores
		*/
		vm.carregarAutores = function () {
			$http({
				method: 'GET', url: '/user/autor?page=' + vm.paginaAutor
			})
				.then(function (response) {
					vm.autor = response.data.content;
					vm.totalElementos = response.data.totalElements;
					console.log(response.data);
					console.log(response.status);
				}, function (response) {
					console.log(response);
				})
		};
		/*
			Busca por letras passado como parametro
		*/
		vm.buscarPorLetra = function (letra) {
			if (letra != null) {
				vm.ativado = letra;
			}
			$http({
				method: 'GET',
				url: '/user/autor/letra/' + vm.ativado + "?page=" + vm.paginaPorLetra
			}).then(function (response) {
				vm.autor = response.data.content;
				vm.totalElementos1 = response.data.totalElements;
				console.log(response);
				console.log(response.data);
			}, function (response) {
				console.log(response);
				console.log(response.data);
			})
		};

		vm.carregarAutores();
	}]);