angular
.module("appCliente")
.controller("admAutorController",['$scope','$http', function ($scope,$http){
	var vm = this;
	vm.Model = {};	
		
	vm.autor = [];
	vm.autores = {};
	vm.pagina = 0;
	vm.totalPalginas = [];
	vm.totalItems = [];
	vm.size = [];
	
	vm.mensagem = "";
	
	vm.carregarAutores = function() {
		$http.get('/user/autor?page='+vm.pagina).then(function (response) {
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
		if(vm.formAutor.$valid) {
		$http({
			method: 'POST', url: '/admin/autor',data:vm.autores})
			.then(function(response) {
				vm.autores = {}
				vm.formAutor.$setPristine(true);
				vm.mensagem = "Salvo com Sucesso";
				vm.carregarAutores();
			}, function (response) {
				vm.mensagem = response.data.message;
				console.log(response.data);
				console.log(response.status);
			})
		}
		else {
			vm.mensagem = "Erro No Formulario";
		}
	};	
	
	vm.excluirAutor = function(autor) {
		$http({
			method: 'DELETE', url : '/admin/autor/'+autor.id})
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