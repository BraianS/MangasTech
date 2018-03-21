angular
.module("appCliente")
.controller("admMangaController", function($http,$scope,$filter){
	var vm = this;
	
	vm.hello = "MANGAS";
	
	$scope.dates = {};
	$scope.years = [{value: 'Year', disabled : true}];
	for(var i = 1990; i <= 2020; i++) {
		$scope.years.push({value: i});
	}
	   var d = new Date();
	    var n = d.getFullYear();

	    $scope.dates.startYear = n;
	    console.log(n);
	
	vm.Model = {};
	
	vm.Model.Manga = {};
	vm.Model.autor = [];
	vm.Mangas = [];
	vm.Model.Mangas = [];
	
	vm.status = [
		"COMPLETO",
		"PAUSADO"
	];
	
	
	 vm.salvarMangas= function() {
			$http({
				  method: 'POST', url: 'http://localhost:8080/manga',data:vm.Model.Manga})
				  .then(function (response) {					  				
					
					 console.log(response);
					 console.log(response.data);
					  vm.Model.Manga = {};
					  vm.Load();
								  
				  }, function (response) {
				    console.log(response.data);
				    console.log(response.status);
				  });
			};
			
			vm.Load= function (){
				if (vm.Model.generos && vm.Model.Generos) {
					angular.forEach(vm.Model.generos, function(_genero, i){

						_genero.Selecionado = $filter('filter')(vm.Model.generos,{id:_genero.id}).length > 0 ;
						//console.log(i, vm.Model.Manga.genero, $filter('filter')(vm.Model.Manga.genero,{id:_genero.id})[0]);
					});
				};
			};
			
			//função após mudar os checkboxes
			vm.Selecionar = function  (argument) {
				vm.Model.Manga.genero = $filter('filter')(vm.Model.generos,{Selecionado: true});
				console.log($filter('filter')(vm.Model.generos, {Selecionado: true}));
			}
			
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
						  method: 'GET', url: 'http://localhost:8080/autor'})
						  .then(function (response) {
							  vm.Model.autor =response.data;
							  console.log(response);
							  console.log(response.data);
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
							vm.carregarMangas2();
						  
						  }, function (response) {
						    console.log(response.data);
						    console.log(response.status)
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
				
				vm.carregarMangas2 = function() {
					$http({
						method: 'GET', url: 'http://localhost:8080/manga'})
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
				
				vm.excluirMangas=function(Manga) {
					$http({
						  method: 'DELETE', url: 'http://localhost:8080/manga/'+Manga.id})
						  .then(function (response) {
							pos = vm.Model.Mangas.indexOf(vm.Model.Manga);
							vm.Model.Mangas.splice(pos,1);
							
							vm.carregarMangas2();
							vm.carregarMangas
						  }, function (response) {
						    console.log(response.data);
						    console.log(response.status)
						  });
				};
				
				vm.carregarMangas2();
				
				vm.Model.carregarautor();
				vm.Model.carregarGeneros();
				
				vm.alterarMangas = function(manga) {
					vm.Model.Manga = manga;
				}
				
				vm.Cancelar = function() {
					vm.Model.Manga = {};
				}
});
