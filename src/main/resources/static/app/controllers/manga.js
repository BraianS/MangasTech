angular
	.module('appCliente')
	.controller('mangaController', ['$http', function ($http) {

		var vm = this;

		vm.Mangas = [];
		vm.paginaPorMangas = 1;
		vm.paginaPorLetra = 1;
		vm.status = false;
		vm.letraAtivada = "";

		var letra = "abcdefghijklmnopqrstuvwxyz";

		vm.alfabeto = letra.toUpperCase().split("");

		vm.resetar = function () {
			vm.paginaPorMangas = 1;
			vm.status = false;
			vm.letraAtivada = "";
		};

		vm.false = function () {
			vm.paginaPorMangas = 1;
			vm.letraAtivada = "";
			vm.status = false;
		};

		vm.true = function () {
			vm.status = true;
			vm.paginaPorLetra = 1;
		};

		vm.carregarAlfabeto = function (letras) {
			if (letras != null) {
				vm.letraAtivada = letras;
			}
			$http({
				method: 'GET', url: '/user/manga/az/' + vm.letraAtivada + '?page=' + vm.paginaPorLetra
			}).then(function (response) {
					vm.Mangas = response.data.content;
					vm.totalElementos = response.data.totalElements;
					console.log(response);
					console.log(response.data);
				}, function (response) {
					console.log(response);
					console.log(response.data);
				})
		};

		vm.carregarMangas = function () {
			$http({
				method: 'GET', url: '/user/manga?page=' + vm.paginaPorMangas
			})
				.then(function (response) {
					vm.Mangas = response.data.content;
					vm.totalElementos1 = response.data.totalElements;
					console.log(response);
					console.log(response.data);
				}, function (response) {
					console.log(response);
					console.log(response.data);
				});
		};

		vm.carregarMangas();
	}]);