angular
.module("appCliente")
.controller("admAutorController",['$scope','$http', function ($scope,$http){
	var vm = this;
	vm.Model = {};
	
	vm.ola =  "Sou o autor, mano brown";
	
	vm.autor = [];
	vm.autores = {};
	vm.pagina = 0;
	vm.totalPalginas = [];
	vm.totalItems = [];
	vm.size = [];
	
	vm.carregarAutores = function() {
		$http.get('http://localhost:8080/autor?page='+vm.pagina).then(function (response) {
			vm.autor = response.data.content;
			vm.totalPaginas = response.data.totalPages;
			vm.totalItems = response.data.totalElements;
			vm.size = response.data.size;
			console.log(response.data.content);
			console.log(response.status);
		}, function (response){
			console.log(response.content);
		})
	};
	
	vm.pageChange = function () {
		alert("pagina atual e: "+ vm.pagina)
	}
	
	vm.salvarAutores = function() {
		$http({
			method: 'POST', url: 'http://localhost:8080/autor',data:vm.autores})
			.then(function(response) {
				vm.autores = {}
				console.log("salvo com sucesso");
				console.log(response.data);
				console.log(response.status);
				vm.carregarAutores();
			}, function (response) {
				
				console.log(response.data);
				console.log(response.status);
			})
	};
	
	vm.excluirAutor = function(autor) {
		$http({
			method: 'DELETE', url : 'http://localhost:8080/autor/'+autor.id})
			.then(function (response) {
				pos = vm.autor.indexOf(vm.autores);
				vm.autor.splice(pos,1);
				console.log("deletado");
				vm.carregarAutores();
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			})
	}
	
	vm.cancelar = function () {
		vm.autores = {};
	};
	
	vm.alterarAutores = function(autor) {
		vm.autores = autor;
	}
	
	vm.carregarAutores();
	
}]);