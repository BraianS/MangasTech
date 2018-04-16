angular
.module("appCliente")
.controller("autorController",['$scope','$http', function ($scope,$http){
	var vm = this;
	vm.Model = {};
	
	vm.autor = [];
	vm.autores = {};
	vm.pagina = 1;
	
	vm.pageChange = function() {
		alert("valor da pagina: "+vm.pagina)
	}
	
	vm.carregarAutores = function() {
		$http.get('/user/autor?page='+vm.pagina).then(function (response) {
			vm.autor = response.data.content;
			vm.size = response.data.size;
			vm.totalItems = response.data.totalElements;
			console.log(response.data);
			console.log(response.status);
		}, function (response){
			console.log(response);
		})
	};
	
	vm.carregarAutores();
	
}]);