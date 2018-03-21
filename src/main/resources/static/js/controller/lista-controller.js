angular
.module('appCliente')
.controller('listaController', ['$http','$stateParams','$state','$scope','$filter','multipartForm','Upload', function ($http,$stateParams,$state,$scope,$filter,multipartForm,Upload) {
	var vm = this;
	vm.Model = {};
	vm.title = 'hello world mudafuck';
	
	
	
	vm.page = parseInt($stateParams.pages, 10);
		
	vm.Mangas = [];
	vm.number = [];
	vm.numero = 0;
	vm.totalDePaginas = [];
	
	vm.nome = 'BRAIAN';
	
	
	vm.pageChanged = function() {
		console.log("page changed to: " + $scope.number);
	}
	vm.index =[];
	vm.carregarMangas2 = function() {
		$http({
			method: 'GET', url: 'http://localhost:8080/manga?page='+vm.page})
			.then(function (response) {
				vm.Mangas = response.data.content;
				vm.number = response.data.number;
				vm.totalDePaginas = response.data.totalPages;
				vm.items = response.data.totalElements;
				
				
				console.log(response);
				console.log(response.data);
			}, function (response) {
				console.log(response);
				console.log(response.data);
			});
	};
	
	vm.carregarMangas2();
	
	
	
	vm.nextPage = function() {
		if(vm.page < vm.totalDePaginas ) {
			$state.go('.', {pages: vm.page +1});
		}
		else {
			console.log("chegou no limite de paginas");
		}
		
	};
	
	vm.prevPage = function() {
		if (vm.page > 0) {
			$state.go('.', {pages: vm.page -1});
		}
	};
	//MANGA DETALHE
	
	vm.status = [
		"COMPLETO",
		"PAUSADO"
	];
		
	vm.Model.Mangas = [];
	vm.Model.Manga = {};
	vm.Model.generos = {};
	vm.Model.autor = [];
	
	    $scope.mangas = [];
		$scope.manga = {};
	
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
				
	vm.carregarMangas = function() {
	$http({
		  method: 'GET', url: 'http://localhost:8080/manga'})
		  .then(function (response) {
			  vm.Model.Mangas =response.data.content;
		   console.log(response.data);
		   console.log(response.status);
		  }, function (response) {
		    console.log(response.data);
		    console.log(response.status);
		  });
	};
	
	
	vm.Model.carregarautor = function() {
		$http({
			  method: 'GET', url: 'http://localhost:8080/autor'})
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
	/*vm.Model.carregarGeneros();		*/
	vm.Model.carregarautor();
	vm.Load();
		
		
	 var str = "abcdefghijklmnopqrstuvwxyz";
	  vm.alphabet = str.toUpperCase().split("");

	  vm.activeLetter = '';

	  vm.activateLetter = function(letter) {
	    
	    alert("foi");
	      $http({method: 'GET', url: 'http://localhost:8080/manga/az/'+letter})
	      .then(function (response) {
	    	  vm.activeLetter = letter;
	    	  vm.abarreta = response.data.content;
	    	  console.log(response.data.content);
	    	  $state.go('.', {ordenado: vm.activeLetter});
	    	  
	    	  
	    }, function (response){
	    	console.log(response);
	    	console.log(response.data);
	    	console.log(response.data.content);
	    })
	  }
}]);


