angular
.module("appCliente")
.controller("navController",['$scope','$location','$route','$routeParams','AuthService','$http','$rootScope','pesquisaService','$transitions','$state','$rootScope',
	function($scope, $location,$route, $routeParams,AuthService,$http,$rootScope,pesquisaService,$transitions,$state,$rootScope){
	$scope.$location = $location;
	$scope.$route=$route;
	$scope.$routeParams = $routeParams;
	
	$scope.user = [];
	
	 $scope.$on('LoginSuccessful', function() {
		$scope.user = AuthService.user;
	});
	
	
	$scope.$on('LogoutSuccessful', function() {
		$scope.user = null;
	});
	
	
	
	var vm = this;
	
	vm.d = [];
	vm.nome = [];
	
	vm.logout = function() {		
		AuthService.user = null;
	}
	
	vm.service = function(nome) {
		pesquisaService.setValue(nome);
		vm.nome = [];
	}	
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
	
}]);