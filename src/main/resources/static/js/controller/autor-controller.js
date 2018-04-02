angular
.module("appCliente")
.controller("autorController",['$scope','$http', function ($scope,$http){
	var vm = this;
	vm.Model = {};
	
	vm.title =  "Sou o autor, mano brown";
	
	vm.autor = [];
	vm.autores = {};
	
	vm.carregarAutores = function() {
		$http.get('http://localhost:8080/autor').then(function (response) {
			vm.autor = response.data.content;
			console.log(response.data);
			console.log(response.status);
		}, function (response){
			console.log(response);
		})
	};
	
	vm.carregarAutores();
	
}]);