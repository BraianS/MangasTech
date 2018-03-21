angular
.module('appCliente')
.controller('registrarController', function($http) {
	var vm = this;
	
	
	vm.reg = {};
	
	vm.registrar = function() {
		$http({
			method: 'POST', url: 'http://localhost:8080/registrar',data:vm.reg})
			.then(function (response){
				console.log('salvo com sucesso');
				console.log(response.data);
				console.log(response.status);
				vm.reg = {};
			}, function (response){
				console.log(response.data);
				console.log(response.status);
			})
	}
});