angular
.module('appCliente')
.controller('grupoDetalheController', function($http,$stateParams) {
	var vm = this;
	
	vm.id = $stateParams.gruposId;
	vm.grupod = [];
	vm.c = [];
	vm.ola = 'ola eu sou pagina do grupos detalhe';
	
	vm.carregarGrupos = function() {
		$http({
			method : 'GET',
			url: 'grupo/'+vm.id
		}).then(function(res){
			console.log(res);
			console.log(res.data);
			vm.grupod = res.data;
			vc.c = res.data.capitulo;
		}, function(res) {
			console.log(res);
			console.log(res.data);
		})
	}
	
	vm.carregarGrupos();
})