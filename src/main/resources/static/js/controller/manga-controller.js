angular
.module("appCliente")
.controller("mangasController", function($scope, $http, $q, $timeout,$window,$filter) {
	
	var vm = this;
	 vm.Model = {};
	
	
	
	vm.status = [
		"COMPLETO",
		"PAUSADO"
	];
	
	
	vm.Model.Mangas = [];
	vm.Model.Manga = {};
	vm.Model.generos = {};
	vm.Model.autor = [];
	
	
	$scope.dates = {};
	$scope.years = [{value: 'Year', disabled : true}];
	for(var i = 1990; i <= 2020; i++) {
		$scope.years.push({value: i});
	}
	   var d = new Date();
	    var n = d.getFullYear();

	    $scope.dates.startYear = n;
	    console.log(n);
	
	    $scope.mangas = [];
		$scope.manga = {};
		
		vm.Model.Generos=[
		 	{
		        "id": 1,
		        "nome": "TERROR"
		    },
		    {
		        "id": 2,
		        "nome": "ACAO"
		    },
		    {
		        "id": 3,
		        "nome": "AVENTURA"
		    },
		    {
		        "id": 4,
		        "nome": "ROMANCE"
		    }
		];
	    
	    
	    vm.salvarMangas= function() {
			$http({
				  method: 'POST', url: 'http://localhost:8080/manga',data:vm.Model.Manga})
				  .then(function (response) {
					  
					  
				
					  vm.Model.Manga = {};
					  vm.Load();
					  vm.carregarMangas();
								  
				  }, function (response) {
				    console.log(response.data);
				    console.log(response.status);
				  });
			};
	    
	      
	 
	
	/*vm.Model.carregarGeneros= function() {
			$http({
				  method: 'GET', url: 'http://localhost:8080/genero'})
				  .then(function (response) {
					$scope.generos=response.data;
					vm.Model.generos = response.data;
				   console.log(response.data);
				   console.log(response.status);
				  }, function (response) {
				    console.log(response.data);
				    console.log(response.status);
				  });
			};*/
				
	
	
	vm.carregarMangas = function() {
	$http({
		  method: 'GET', url: 'http://localhost:8080/manga'})
		  .then(function (response) {
			  vm.Model.Mangas =response.data;
		   console.log(response.data);
		   console.log(response.status);
		  }, function (response) {
		    console.log(response.data);
		    console.log(response.status);
		  });
	};
	
	
	vm.Model.carregarautor = function() {
		$http({
			  method: 'GET', url: 'http://localhost:8080/autor1'})
			  .then(function (response) {
				  vm.Model.autor =response.data;
			   
			  }, function (response) {
			    console.log(response.data);
			    console.log(response.status);
			  });
	};
	
	vm.excluirMangas=function(Manga) {
		$http({
			  method: 'DELETE', url: 'http://localhost:8080/manga/'+Manga.id})
			  .then(function (response) {
				pos = vm.Model.Mangas.indexOf(vm.Model.Manga);
				vm.Model.Mangas.splice(pos,1);
				vm.carregarMangas();
			  
			  }, function (response) {
			    console.log(response.data);
			    console.log(response.status)
			  });
	};
	
	//Aqui vem a chamada da API dos generos
	
	
	//no then do manga ele executa e atualiza a lista de generos
	vm.Load= function (){
		if (vm.Model.Manga.genero && vm.Model.Generos) {
			angular.forEach(vm.Model.Generos, function(_genero, i){

				_genero.Selecionado = $filter('filter')(vm.Model.Manga.genero,{id:_genero.id}).length > 0 ;
				//console.log(i, vm.Model.Manga.genero, $filter('filter')(vm.Model.Manga.genero,{id:_genero.id})[0]);
			});
		};
	};

	//função após mudar os checkboxes
	vm.Selecionar = function  (argument) {
		vm.Model.Manga.genero = $filter('filter')(vm.Model.Generos,{Selecionado: true});
		console.log($filter('filter')(vm.Model.Generos, {Selecionado: true}));
	}
	
	vm.carregarMangas();
	/*vm.Model.carregarGeneros();		*/
	vm.Model.carregarautor();
	vm.Load();
		
		$scope.alterarMangas = function(manga) {
			$scope.manga = angular.copy(manga);
		}
		
		$scope.cancelar = function() {
						$scope.manga = {};
		};
		
	vm.alterarMangas = function(manga) {
		vm.Model.Manga = manga;
	}
	
	vm.Cancelar = function() {
		vm.Model.Manga = {};
	}
		
}) ;