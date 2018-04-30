angular
.module('appCliente')
.controller('homeController', function($http, $scope, AuthService,$location,$route,$routeParams,$rootScope) {
	$scope.user = AuthService.user;
	
	var vm = this;
	
	$scope.$location = $location;
	$scope.$route=$route;
	$scope.$routeParams = $routeParams;
	
	$scope.ola = "ola mundo";
	
	vm.capnews = [];
	vm.nome = [];
	vm.d = [];
	
	
	$scope.xuxa = $rootScope.xuxa;
	$rootScope.xuxa;
	
	vm.carregarNovidades = function() {
		$http({
			method : 'GET',
			url: '/user/capitulo/novidades'
		}).then(function (response){
			console.log(response);
			console.log(response.data);
			vm.capnews = response.data;
		}, function (response) {
			console.log(response);
			console.log(response.data);
		})
	}
	
	vm.carregarNovidades();
	
	vm.pesquisarNome = function(nome){
		$http({
			method: 'GET',
			url: '/user/nome/'+nome
		}).then(function(res) {
			console.log(res);
			console.log(res.data);
			vm.d = res.data;
		}, function(res) {
			console.log(res);
			console.log(res.data);
		})
	}
})