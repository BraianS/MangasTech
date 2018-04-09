angular
.module("appCliente")
.controller('loginController', function ($http,$scope,$state,AuthService,$rootScope) {
	var vm = this;
	
	vm.logar = function(user) {
		usuarioService.validaLogin(user);
	};
	
	vm.usuario = {};
	vm.token = [];
		
	vm.logout = function() {
		usuarioService.logout();
	}
	
	vm.autenticar = function() {
		if(vm.formLogin.$valid) {
		$http({
			method : 'POST',
			url: 'autenticar',
			params: {
				username : $scope.username,
				password : $scope.password
			}
		}).then(function(res){
			$scope.password = null;
			if(res.data.token) {
				$scope.message = '';
				
				$http.defaults.headers.common['Authorization'] = 'Bearer ' + res.data.token;
				
				AuthService.user = res.data.user;
				$rootScope.$broadcast('LoginSuccessful');
								
				$state.go('home');
			} else {
				$scope.message = 'Authentication Failed';
				
			}
				
			
		}, function (res) {
			$scope.message = 'Authentication Failed !';
		});
		
		}
		else {
			alert("Formulario e invalido")
		}
	};
	
		
	
});/*
.service ('usuarioService',function ($rootScope, $location) {
	var vm = this;
	
	vm.title = 'OLA MUNDOOO';
	vm.ngmodel = "cabra";
	vm.password = "123";
	
	this.validaLogin = function(user){
		
		var usuarios = [{username:'Robson', password:'123', admin:true},
	          {username:'Juliano', password:'123', admin:false},
	          {username:'Bruno', password:'123', admin:false}]
		
		angular.forEach(usuarios, function(value, index) {
			if(value.username == user.username &&
					value.password == user.password){
				delete value.password;
				$rootScope.usuarioLogado = value;
				$location.path('/')
			}
		})
		alert("deu certo");
	}
	
	this.logout = function () {
		$rootScope.usuarioLogado = null;
		$location.path('/')
	}
	
})*/