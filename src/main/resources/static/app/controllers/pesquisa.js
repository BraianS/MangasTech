angular
.module('appCliente')
.controller('pesquisaController', function($http, $rootScope, pesquisaService,$scope) {
	
	var vm = this;
	vm.d = [];
		
	pesquisaService.getValue();
	
	
	vm.a = pesquisaService.getValue();
	
	
	
	vm.pesquisarNome = function(){
		$http({
			method: 'GET',
			url: '/user/nome/'+vm.a
		}).then(function(res) {
			console.log(res);
			console.log(res.data);
			vm.d = res.data;
			
		}, function(res) {
			console.log(res);
			console.log(res.data);
		})
	}
	vm.pesquisarNome();
	
});
