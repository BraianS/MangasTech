angular
.module("appCliente")
.controller("admMangaController", function($http,$scope,$filter,$stateParams,$state){
	var vm = this;
		
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
	
	vm.pagina = 0;
	vm.size = [];
	vm.totalPaginas = [];
	vm.totalElementos = [];
	
	vm.status = [
		"COMPLETO",
		"LANCANDO",
		"PAUSADO"
	];
	
	vm.page = parseInt($stateParams.pages, 10);	
		
	 vm.salvarMangas = function() {
		 if(vm.formNovoManga.$valid){
			$http({
				  method: 'POST', url: '/admin/manga',data:vm.Model.Manga})
				  .then(function (response) {					  				
					  vm.Model.Manga = {};
					 
					  vm.carregarMangas();
					  vm.mensagem = "Salvo com Sucesso";
					  vm.formNovoManga.$setPristine(true);
					  vm.Model.carregarGeneros();
					 
					vm.Load();			  
				  }, function (response) {
				    console.log(response.data);
				    console.log(response.status);
				    vm.mensagem = "Nao foi salvo";
				  });
		 		}
		 	else
		 	{
		 		alert('não foi salvo');
		 	}
			};
			
			vm.Load = function (){
				if (vm.Model.generos && vm.Model.Generos) {
					angular.forEach(vm.Model.generos, function(_genero, i){
						_genero.Selecionado = $filter('filter')(vm.Model.generos,{id:_genero.id}).length > 0 ;
						console.log(i, vm.Model.Manga.genero, $filter('filter')(vm.Model.Manga.genero,{id:_genero.id})[0]);
					});
				};
			};
			
			//função após mudar os checkboxes
			vm.Selecionar = function  (argument) {
				vm.Model.Manga.genero = $filter('filter')(vm.Model.generos,{Selecionado: false});
				console.log($filter('filter')(vm.Model.generos, {Selecionado: false}));
			}
			
			vm.Model.carregarGeneros= function() {
				$http({
					  method: 'GET', url: '/user/genero'})
					  .then(function (response) {
						  vm.Model.generos = response.data.content;
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
						  method: 'GET', url: '/user/autor'})
						  .then(function (response) {
							  vm.Model.autor =response.data.content;
							  console.log(response);
							  console.log(response.data);
						  }, function (response) {
						    console.log(response.data);
						    console.log(response.status);
						  });
				};
				
				vm.excluirMangas=function(Manga) {
					$http({
						  method: 'DELETE', url: '/admin/manga/'+Manga.id})
						  .then(function (response) {
							pos = vm.Model.Mangas.indexOf(vm.Model.Manga);
							vm.Model.Mangas.splice(pos,1);
							vm.carregarMangas();
													  
						  }, function (response) {
						    console.log(response.data);
						    console.log(response.status)
						  });
				};
				
									
					vm.pageChange = function() {
						alert("pagina atual e : "+vm.pagina)
					}
				
				vm.carregarMangas = function() {
					$http({
						method: 'GET', url: '/user/manga?page='+vm.pagina})
						.then(function (response) {
							vm.Mangas = response.data.content;
							vm.totalDePaginas = response.data.totalPages;
							vm.totalElementos = response.data.totalElements;
							vm.size = response.data.size;							
							
							console.log(response);
							console.log(response.data);
						}, function (response) {
							console.log(response);
							console.log(response.data);
						});
				};
				
				vm.excluirMangas=function(Manga) {
					$http({
						  method: 'DELETE', url: '/admin/manga/'+Manga.id})
						  .then(function (response) {
							pos = vm.Model.Mangas.indexOf(vm.Model.Manga);
							vm.Model.Mangas.splice(pos,1);							
							vm.carregarMangas();
							vm.carregarMangas
						  }, function (response) {
						    console.log(response.data);
						    console.log(response.status)
						  });
				};
				
				vm.carregarMangas();				
				vm.Model.carregarautor();
				vm.Model.carregarGeneros();
				
				vm.alterarMangas = function(manga) {
					vm.Model.Manga = manga;
				}
				
				vm.Cancelar = function() {
					vm.Model.Manga = {};
				}
});
