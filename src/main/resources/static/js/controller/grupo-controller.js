angular
.module("appCliente")
.controller("grupoController", function($http) {
	var vm = this;
	
	vm.Model = {};
	vm.Grupos = [];
	vm.Grupo = {};
	
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
		
		vm.salvarGrupos = function () {
			$http({
				method: 'POST', url: 'http://localhost:8080/grupo',data: vm.Grupo})
				.then(function (response) {
					vm.Grupo = {};
					vm.carregarGrupos();
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
				})
			}
		
		vm.deletarGrupos = function (grupos) {
			$http({
				method : 'DELETE', url : 'http://localhost:8080/grupo/'+grupos.id})
				.then( function (response) {
					pos = vm.Grupos.indexOf(vm.Grupo);
					vm.Grupos.splice(pos,1);
					console.log("deletado com sucesso");
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
				})
		}
		
		vm.carregarGrupos();
		
		vm.cancelar = function (){
			vm.Grupo = {};
		}
		
		vm.alterarGrupos = function (grupo){
			vm.Grupo = grupo;
		}
});