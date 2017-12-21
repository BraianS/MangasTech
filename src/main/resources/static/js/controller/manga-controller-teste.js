angular
.module("appCliente")
.controller('indexController',['$scope','$http','$q','$timeout','$window','$filter', function($scope, $http, $q, $timeout,$window,$filter){
	var vm = this;
	vm.Model={   
   };
	
	vm.title = "nome";
	
   //Aqui vem a chamada da API dos generos
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
	//no sucesso da requisição ele chama o manga
	
	vm.mangas = [];
	
	
	
		
	//Aqui vem API de obter o Manga
   	vm.Model.Manga = [
   		/*"nome": "BORUTO",
	    "status": "COMPLETO",
	    "dataLancado": "2015",
	    "genero" : [{"id":1, "nome":"TERROR"},{"id":"2", "nome":"ACAO"}]*/
   				
   	];
   	
   	vm.olamundo = function () {
   		var vm = this;
   		this.name = "Braian";
   		console.log(this.name);
   	};
   	
   	vm.carregarMangas= function() {
		$http({
			  method: 'GET', url: 'http://localhost:8080/manga'})
			  .then(function (response) {
				vm.mangas=response.data;
			   console.log(response.data);
			   console.log(response.status);
			  }, function (response) {
			    console.log(response.data);
			    console.log(response.status);
			  });
		};
	
	
	vm.Model.salvarMangas = function() {
		$http({
			method: 'POST', url: 'http://localhost:8080/manga',data:$scope.vm.Model.Manga})
			 .then(function (response) {
				 $scope.vm.Model.Manga(response.data);
			 }, function (response) {
			console.log(response.data);
			console.log(response.status);
		});
	};
	
   	
   	vm.carregarMangas();

   	//no then do manga ele executa e atualiza a lista de generos
	vm.Load= function (){
		if (vm.Model.Manga.genero && vm.Model.Generos) {
			angular.forEach(vm.Model.Generos, function(_genero, i){

				_genero.Selecionado = $filter('filter')(vm.Model.Manga.genero,{id:_genero.id}).length > 0 ;
				//console.log(i, vm.Model.Manga.genero, $filter('filter')(vm.Model.Manga.genero,{id:_genero.id})[0]);
			});
		};
	}

	//função após mudar os checkboxes
	vm.Selecionar = function  (argument) {
		vm.Model.Manga.genero = $filter('filter')(vm.Model.Generos,{Selecionado: true});
		console.log($filter('filter')(vm.Model.Generos, {Selecionado: true}));
	}

	vm.Load();
}]);