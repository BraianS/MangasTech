(function () {

	//Carrega o modulo
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('navController', navController);

	navController.$inject = ['$scope', '$location', 'AuthService', '$http', 'pesquisaService', '$route', '$routeParams'];

	function navController($scope, $location, AuthService, $http, pesquisaService, $route, $routeParams) {

		var vm = this;

		vm.d = [];
		vm.nome = [];

		$scope.$location = $location;
		$scope.$route = $route;
		$scope.$routeParams = $routeParams;

		vm.user = [];

		$scope.$on('LoginSuccessful', function () {
			vm.user = AuthService.user;
		});

		$scope.$on('LogoutSuccessful', function () {
			vm.user.user = null;
		});

		vm.logout = function () {
			AuthService.user = null;
		}

		vm.service = function (nome) {
			pesquisaService.setValue(nome);
			vm.nome = [];
		}
		vm.pesquisarNome = function (nome) {
			$http({
				method: 'GET',
				url: '/user/manga/nome/' + nome
			}).then(function (res) {
				console.log(res);
				console.log(res.data);
				vm.d = res.data;
			}, function (res) {
				console.log(res);
				console.log(res.data);
			})
		}
	};
	
})();

