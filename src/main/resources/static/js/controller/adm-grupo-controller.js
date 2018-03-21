angular
.module("appCliente")
.controller("adminGruposController", function($http,$scope,AuthService) {
	var vm = this;
	vm.hello = "ola porra";
	//AuthService.user = 'default';
	$scope.user = AuthService.user;

	vm.Model = {};
	vm.Grupos = [];
	vm.Grupo = {};
	
	vm.title = "sou grupos, prazer";
	
	vm.init = function () {
		$http.get('/user').then(function(res) {
			$scope.users = res;
			
			
			$scope.message = '';
			$scope.appUser = null;
			$scope.buttonText = 'Create';
		}, function(res) {
			console.log(res);
			console.log(res.data);
		})
	}
	
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
				method: 'POST', url: 'grupo',data: vm.Grupo})
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
		
		vm.init();
})