angular
.module('appCliente')
.controller('generodetalhe', function($http,GeneroService,$stateParams) {
	var vm = this;
	
	vm.ola = 'hello my bruda';
	vm.generosDetalhe = [];
	
	vm.generoId = $stateParams.generoId;
	
	vm.carregarGeneros = function(Genero) {
		$http({
			method: 'GET',
			url: '/user/genero/'+vm.generoId
		}).then(function(response){
			console.log(response);
			console.log(response.data);
			vm.generosDetalhe = response.data;
		}, function(response) {
			console.log(response);
			console.log(response.data);
		})
	}
	
	vm.carregarGeneros();
});