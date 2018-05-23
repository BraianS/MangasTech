angular
.module('appCliente')
.controller('homeController', function($http, $scope, $location,$route,$routeParams,$rootScope) {
		
	var vm = this;
	
	$scope.$location = $location;
	$scope.$route=$route;
	$scope.$routeParams = $routeParams;
			
	vm.capnews = [];
	vm.nome = [];
	vm.d = [];
	vm.mangas = [];	
		
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
	};
	
	vm.carregarNovidades();

	vm.carregarMangas = function() {
		$http({
			method: 'GET',
			url: '/user/manga/top10'
		}).then(function(response) {
			vm.mangas = response.data;
		}, function(response) {
			console.log(response);
			console.log(response.data);
		})
	};
	
	vm.carregarMangas();

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