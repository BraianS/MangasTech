angular
.module('appCliente')
.controller('loginController', function ($http,$scope,$state,AuthService,$rootScope,$transitions) {
	var vm = this;
		
	vm.usuario = {};
	vm.token = [];

		
	vm.autenticar = function() {
		if(vm.formLogin.$valid) {
		$http({
			method : 'POST',
			url: '/autenticar',
			params: {
				username : $scope.username,
				password : $scope.password
			}
		}).then(function(res){
			$scope.password = null;
			if(res.data.token) {
				vm.mensagem = '';
				
				$http.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.token;
				
				AuthService.user = res.data.user;
				$rootScope.$broadcast('LoginSuccessful');
				$state.go('home');
			} else {
				vm.mensagem = 'Autenticacao Falhau';				
			}
				
			
		}, function (res) {
			vm.mensagem = 'Autenticacao Falhou';
		});
		
		}
		else {
			alert("Formulario e invalido")
		}
	};
});
		