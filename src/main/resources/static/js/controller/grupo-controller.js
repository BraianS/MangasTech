angular
.module("appCliente")
.controller("grupoController", function($http,$scope,AuthService) {
	var vm = this;
	
	vm.Model = {};
	vm.Grupos = [];
	vm.Grupo = {};
	
	$scope.user = AuthService.user;
	
	vm.title = "sou grupos, prazer";
	
	vm.carregarGrupos = function () {
		$http({
			method: 'GET', url: 'http://localhost:8080/grupo'})
			.then(function (response){
				vm.Grupos = response.data;
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
				})
		};
		vm.carregarGrupos();
	
});