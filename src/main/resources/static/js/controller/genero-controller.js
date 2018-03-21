angular
.module("appCliente")
.controller("generosController",['$http', function($http){
	
	var vm = this;
	vm.Model = {};
	
	vm.Genero = {};
	
	vm.Generos = [];
	vm.Manga = []
	
	
	vm.carregarGeneros = function() {
		$http({
			method: 'GET', url: 'http://localhost:8080/genero'})
			.then(function (response) {
			vm.Generos = response.data;
			vm.Manga = response.data.manga;
			console.log(response.data);
			console.log(response.status);
			
			},function (response){
				console.log(response.data);
				console.log(response.status);
			});
	};
	
	vm.carregarGeneros();
	
}]);