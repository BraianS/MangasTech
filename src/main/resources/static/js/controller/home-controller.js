angular
.module('appCliente')
.controller('homeController', function($http, $scope, AuthService,$location,$route,$routeParams) {
	$scope.user = AuthService.user;
	
	var vm = this;
	
	$scope.$location = $location;
	$scope.$route=$route;
	$scope.$routeParams = $routeParams;
	
	$scope.ola = "ola mundo";
	
	vm.capnews = [];
	
	vm.carregarNovidades = function() {
		$http({
			method : 'GET',
			url: 'http://localhost:8080/home2'
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
})