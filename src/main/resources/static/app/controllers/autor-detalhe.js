angular
.module('appCliente')
.controller('autorDetalhe', function($http,$stateParams) {
	var vm = this;
	vm.ola = "bem vindo";
	
	vm.id = $stateParams.autorId;
	vm.autor = [];
	
	vm.carregarAutor = function() {
		$http({
			method: 'GET',
			url: 'autor/'+vm.id
		}).then(function(res) {
			console.log(res);
			console.log(res.data);
			vm.autor = res.data;
		}, function(res) {
			console.log(res);
			console.log(res.data);
		})
	}
	
	vm.carregarAutor();
});