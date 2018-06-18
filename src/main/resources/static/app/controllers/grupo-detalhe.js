angular
.module('appCliente')
.controller('grupoDetalheController', function($http,$stateParams) {
	var vm = this;
	
	vm.id = $stateParams.gruposId;
	
	vm.grupod = [];
	vm.pagina = 1;
	vm.carregarGrupos = function() {
		$http({
			method : 'GET',
			url: '/user/grupo/'+vm.id
		}).then(function(res){
			console.log(res);
			console.log(res.data);
			vm.grupod = res.data;
			vm.number = res.data.number;
			vm.totalDePaginas = res.data.totalPages;
			vm.items = res.data.totalElements;
			vm.size = res.data.size;			
		}, function(res) {
			console.log(res);
			console.log(res.data);
		})
	}
	
	vm.carregarGrupos();
})