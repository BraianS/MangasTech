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
			method: 'GET', url: '/user/genero'})
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
		if(vm.formGenero.$valid) {
		$http({
			method: 'POST', url: '/admin/genero',data:vm.Genero})
			.then(function (response) {
				vm.Genero = {};
				console.log(response.data);
				console.log(response.status);
				vm.carregarGeneros();
				vm.formGenero.$setPristine(true);
				vm.mensagem = "Salvo com Sucesso";
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
		}
		else {
			vm.mensagem = "Erro No formulario";
		}
	};
	
	vm.deletarGeneros = function (genero) {
		$http({
			method: 'DELETE', url: "/admin/genero/"+genero.id
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