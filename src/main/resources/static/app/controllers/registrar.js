angular
.module('appCliente')
.controller('registrarController', function($http,$state) {
	var vm = this;		
	
	vm.registrar = function() {
		if(vm.formRegistrar.$valid) {
		$http({
			method: 'POST', url: '/registrar',data:vm.reg})
			.then(function (response){
				console.log('salvo com sucesso');
				console.log(response.data);
				console.log(response.status);
				vm.confirmarSenha = null;
				vm.reg = {};
				vm.formRegistrar.$setPristine(true);
				$state.go('login');
			}, function (response){				
				console.log(response.data.message);
				console.log(response.data);
				vm.mensagem = response.data.message;
			})
		}
		else {
			alert("formulario invalido");
		}
	}
});