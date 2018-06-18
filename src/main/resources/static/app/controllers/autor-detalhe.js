angular
.module('appCliente')
.controller('autorDetalhe', function($http,$stateParams) {
	var vm = this;
		
	vm.id = $stateParams.autorId;
	vm.autor = [];
	vm.pagina = 1;
	
	vm.carregarAutor = function() {
		$http({
			method: 'GET',
			url: '/user/autor/'+vm.id+'?page='+vm.pagina
		}).then(function(res) {
			console.log(res);
			console.log(res.data.content);
			vm.autor = res.data.content;
			vm.number = res.data.number;
			vm.totalDePaginas = res.data.totalPages;
			vm.items = res.data.totalElements;
			vm.size = res.data.size;
			
		}, function(res) {
			console.log(res);
			console.log(res.data);
		})
	}
	
	vm.carregarAutor();
});