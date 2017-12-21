angular
.module("appCliente")
.controller("grupoController", function($http) {
	var vm = this;
	
	vm.Model = {};
	vm.Model.Grupos = [];
	
	vm.title = "sou grupos, prazer";
	
	vm.carregarGrupos = function () {
		$http({
			method: 'GET', url: 'http://localhost:8080/grupo'})
			.then(function (response){
				vm.Model.Grupos = response.data;
				}, function (response) {
					console
				})
		};
		vm.carregarGrupos();
		
});