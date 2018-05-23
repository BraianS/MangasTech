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
	vm.pagina = 0;
	vm.number = [];
	vm.totalDePaginas = [];
	vm.items = [];
	vm.size = [];
	
	vm.pageChange = function(){
		alert("pagina atual e:"+vm.pagina)
	}
	
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
			method: 'GET', url: '/user/grupo?page='+vm.pagina})
			.then(function (response){
				vm.Grupos = response.data.content;
				vm.number = response.data.number;
				vm.totalDePaginas = response.data.totalPages;
				vm.items = response.data.totalElements;
				vm.size = response.data.size;
				
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
				})
		};
		vm.carregarGrupos();
		
		vm.salvarGrupos = function () {
			if(vm.formGrupo.$valid) {
			$http({
				method: 'POST', url: '/admin/grupo',data: vm.Grupo})
				.then(function (response) {
					vm.Grupo = {};
					vm.carregarGrupos();
					vm.formGrupo.$setPristine(true);
					vm.mensagem = "Salvo com Sucesso"
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
					vm.mensagem = response.data.message;
				})
			}
			else {
				vm.mensagem= "Error no Formulario";
			}
			
		};
		
		vm.deletarGrupos = function (grupos) {
			$http({
				method : 'DELETE', url : '/admin/grupo/'+grupos.id})
				.then( function (response) {
					pos = vm.Grupos.indexOf(vm.Grupo);
					vm.Grupos.splice(pos,1);
					vm.carregarGrupos();
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