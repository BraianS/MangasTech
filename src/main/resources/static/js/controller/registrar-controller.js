angular
.module('appCliente')
.controller('registrarController', function($http,$state) {
	var vm = this;
	
	
	vm.reg = {};
	
	vm.registrar = function() {
		if(vm.formRegistrar.$valid) {
		$http({
			method: 'POST', url: 'http://localhost:8080/registrar',data:vm.reg})
			.then(function (response){
				console.log('salvo com sucesso');
				console.log(response.data);
				console.log(response.status);
				vm.reg = {};
				vm.formRegistrar.$setPristine(true);
				$state.go('login');
			}, function (response){
				console.log(response.data);
				console.log(response.status);
			})
		}
		else {
			alert("formulario invalido");
		}
	}
});