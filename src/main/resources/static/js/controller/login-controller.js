angular
.module("appCliente")
.controller('loginController', function (usuarioService,$http) {
	var vm = this;
	
	vm.logar = function(user) {
		usuarioService.validaLogin(user);
	};
	
	vm.usuario = {};
	
		
	vm.logout = function() {
		usuarioService.logout();
	}
	
	vm.autenticar = function() {
		$http.post("/autenticar", vm.usuario).then(function(response) {
			console.log("Sucess " + response.data);
		}, function(response) {
			console.log("Falha " + response);
		})
	}
	
})/*
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