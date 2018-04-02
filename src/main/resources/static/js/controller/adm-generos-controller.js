angular
.module("appCliente")
.controller("admGenerosController", function($http) {
	var vm = this;
	vm.hola = "como estas amigo?";
	
	var vm = this;
	vm.Model = {};
	
	vm.Genero = {};
	
	vm.title = "funcionando";
	vm.Generos = [];
	
	
	vm.carregarGeneros = function() {
		$http({
			method: 'GET', url: 'http://localhost:8080/genero'})
			.then(function (response) {
			vm.Generos = response.data.content;
			console.log(response.data);
			console.log(response.status);
			
			},function (response){
				console.log(response.data);
				console.log(response.status);
			});
	};
	
	vm.salvarGeneros = function() {
		$http({
			method: 'POST', url: 'http://localhost:8080/genero',data:vm.Genero})
			.then(function (response) {
				vm.Genero = {};
				console.log(response.data);
				console.log(response.status);
				vm.carregarGeneros();
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
	};
	
	vm.deletarGeneros = function (genero) {
		$http({
			method: 'DELETE', url: "http://localhost:8080/genero/"+genero.id
		}).then(function (response){
			pos = vm.Generos.indexOf(vm.Genero);
			vm.Generos.splice(pos,1);
			console.log("deletado com sucesso");
		},function (response){
			console.log(response);
			console.log(response.data);
		});
	}
	
	vm.carregarGeneros();
	
	vm.cancelar = function () {
		vm.Genero = {};
	}
	
	vm.alterarGeneros = function (genero) {
		vm.Genero = genero;
	}
});