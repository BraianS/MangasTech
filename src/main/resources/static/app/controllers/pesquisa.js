angular
.module('appCliente')
.controller('pesquisaController', function($http, $rootScope, pesquisaService,$scope) {
	
	var vm = this;
	vm.d = [];
	vm.ola = "Texto pesquisado";
	
	pesquisaService.getValue();	
	
	vm.a = pesquisaService.getValue();	
	vm.pagina = 1;
	vm.pesquisarNome = function(){
		$http({
			method: 'GET',
			url: '/user/manga/nome/'+vm.a+"?page="+vm.pagina
		}).then(function(res) {
			console.log(res);
			console.log(res.data);
			vm.d = res.data.content;
			vm.number = res.data.number;
			vm.totalDePaginas = res.data.totalPages;
			vm.items = res.data.totalElements;
			vm.size = res.data.size;
			
		}, function(res) {
			console.log(res);
			console.log(res.data);
		})
	}
	vm.pesquisarNome();
	
});
