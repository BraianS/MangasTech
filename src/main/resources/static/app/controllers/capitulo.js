angular
.module("appCliente")
.controller("capituloController",['$scope', '$routeParams','$rootScope','$http','$location','$stateParams',function ($scope, $routeParams,$rootScope, $http, $location,$stateParams) {
	var vm = this;
	vm.Model = {};
	
	$scope.capituloId = $stateParams.capituloId;
	
	vm.foto = {};
	
		
	$http.get("/user/capitulo/"+$stateParams.capituloId).then(function (response) {
		vm.foto = response.data;
	console.log(response.data);
	console.log(response.status);
	}, function (response) {
		console.log(response);
	});
	
	vm.carregarcapitulo = function() {
		$http({
			method : 'GET',
			url: '/user/pagina/'+$stateParams.capituloId
		})
		.then(function (response){
			
			vm.fotos = response.data;
			console.log(response);
			console.log(response.data);
		}, function(response) {
			console.log(response);
			console.log(response.data);
		})
	}
	
	vm.carregarcapitulo();
	
}]);
