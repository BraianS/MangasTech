angular
.module("appCliente")
.controller("generosController",['$http', function($http){
	
	var vm = this;
	vm.Model = {};
	
	vm.Genero = {};
	
	vm.title = "funcionando";
	vm.Model.Generos = [];
	
	
	vm.carregarGeneros = function() {
		$http({
			method: 'GET', url: 'http://localhost:8080/genero'})
			.then(function (response) {
			vm.Model.Generos = response.data;
			console.log(response.data);
			console.log(response.status);
			
			},function (response){
				console.log(response.data);
				console.log(response.status);
			});
	};
	vm.carregarGeneros();
}]);