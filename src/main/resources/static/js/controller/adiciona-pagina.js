angular
.module("appCliente")
.controller("paginasController", function($http){
	
	var vm = this;
	vm.Model = {};
	
	vm.Model.Pagina = {};
	vm.Model.CarregarCapitulo = {};
	
	vm.salvarPaginas = function() {
		$http({
			method: 'POST', url: 'http://localhost:8080/pagina',data:vm.Model.Pagina})
			.then(function () {
				vm.Model.Pagina = {};
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			})
	};
	
	vm.carregarCapitulos = function() {
		$http({
			method : 'GET', url : 'http://localhost:8080/capitulo'})
			.then(function (response) {
				vm.Model.CarregarCapitulo = response.data;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			})
	};
	
	vm.carregarCapitulos();
});