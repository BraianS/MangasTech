angular
.module('appCliente')
.controller('listaController', ['$http','$stateParams','$state','$scope','$filter','$log', function ($http,$stateParams,$state,$scope,$filter,$log) {
	var vm = this;
	
	vm.Model = {};	
		
	vm.Mangas = [];
	vm.number = [];
	vm.totalDePaginas = [];
	vm.size = [];
	vm.items = [];
	vm.pagina = 1;		
	vm.Model.generos = {};
	vm.Model.autor = [];
	
	vm.pageChange = function() {
	   alert("pagina mudou para 3"+vm.pagina)
	  };
	  	  	 	
	vm.carregarMangas = function() {
		$http({
		
			method: 'GET', url: 'http://localhost:8080/user/manga?page='+vm.pagina})
			.then(function (response) {
				vm.Mangas = response.data.content;
				vm.number = response.data.number;
				vm.totalDePaginas = response.data.totalPages;
				vm.items = response.data.totalElements;
				vm.size = response.data.size;
				
				console.log(response);
				console.log(response.data);
			}, function (response) {
				console.log(response);
				console.log(response.data);
			});
	};
						
	vm.Model.carregarGeneros= function() {
			$http({
				  method: 'GET', url: 'http://localhost:8080/genero'})
				  .then(function (response) {
					  vm.Model.generos = response.data;
					console.log("genero buscado com sucesso");
				   console.log(response.data);
				   console.log(response.status);
				  }, function (response) {
				    console.log(response.data);
				    console.log(response.status);
				  });
			};
				
	vm.Model.carregarautor = function() {
		$http({
			  method: 'GET', url: 'http://localhost:8080/user/autor'})
			  .then(function (response) {
				  vm.Model.autor =response.data;
			   
			  }, function (response) {
			    console.log(response.data);
			    console.log(response.status);
			  });
	};
	
	//Aqui vem a chamada da API dos generos
	
	//no then do manga ele executa e atualiza a lista de generos
	vm.Load= function (){
		if (vm.Model.generos && vm.Model.Generos) {
			angular.forEach(vm.Model.generos, function(_genero, i){

				_genero.Selecionado = $filter('filter')(vm.Model.generos,{id:_genero.id}).length > 0 ;
				//console.log(i, vm.Model.Manga.genero, $filter('filter')(vm.Model.Manga.genero,{id:_genero.id})[0]);
			});
		};
	};

	
	vm.Model.carregarGeneros();
	vm.carregarMangas();	
	vm.Model.carregarautor();
	vm.Load();
		
		
}]);


