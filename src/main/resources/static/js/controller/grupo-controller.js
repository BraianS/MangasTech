angular
.module("appCliente")
.controller("grupoController", function($http,$scope,AuthService) {
	var vm = this;
	
	vm.Model = {};
	vm.Grupos = [];
	vm.Grupo = {};
	
	vm.totalPaginas = [];
	vm.size = [];
	vm.pagina = 0;
	vm.totalElementos = [];
	vm.number = [];
	
	
	$scope.user = AuthService.user;
	
	
	vm.carregarGrupos = function () {
		$http({
			method: 'GET', url: 'grupo?page='+vm.pagina})
			.then(function (response){
				vm.Grupos = response.data.content;
				vm.number = response.data.number;
				vm.totalPaginas = response.data.totalPages;
				vm.size = response.data.size;
				vm.totalElementos = response.data.totalElements;
				
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
				})
		};
		vm.carregarGrupos();
		
		vm.pageChange = function () {
			alert("pagina atual e : "+vm.pagina);
		}
	
});