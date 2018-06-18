angular
.module('appCliente')
.controller('generodetalhe', function($http,GeneroService,$stateParams) {
	var vm = this;

	vm.generosDetalhe = [];

	vm.pagina = 1;
	
	vm.generoId = $stateParams.generoId;
	
	vm.carregarGeneros = function(Genero) {
		$http({
			method: 'GET',
			url: '/user/genero/'+vm.generoId+"?page="+vm.pagina
		}).then(function(response){
			console.log(response);
			console.log(response.data);
			vm.generosDetalhe = response.data.content;
			vm.number = res.data.number;
			vm.totalDePaginas = res.data.totalPages;
			vm.items = res.data.totalElements;
			vm.size = res.data.size;
		}, function(response) {
			console.log(response);
			console.log(response.data);
		})
	}
	
	vm.carregarGeneros();
});