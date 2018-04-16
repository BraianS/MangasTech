angular
.module('appCliente')
.controller('grupoDetalheController', function($http,$stateParams) {
	var vm = this;
	
	vm.id = $stateParams.gruposId;
	
	vm.grupod = [];
		
	vm.carregarGrupos = function() {
		$http({
			method : 'GET',
			url: 'grupo/'+vm.id
		}).then(function(res){
			console.log(res);
			console.log(res.data);
			vm.grupod = res.data;			
		}, function(res) {
			console.log(res);
			console.log(res.data);
		})
	}
	
	vm.carregarGrupos();
})