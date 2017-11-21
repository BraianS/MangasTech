

appCliente.controller("mangasController", function($scope,$http) {
	
	
	$scope.generos = {};
	$scope.status = ["COMPLETO","LANCANDO","PAUSADO"];
	
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
	    $scope.generolist = [
	    		{id:1, nome: 'TERROR'}
	    ];
	    
	    $scope.selected = {
	    		generos : []
	    };
	    
	    $scope.salvarMangas= function() {
			$http({
				  method: 'POST', url: 'http://localhost:8080/manga',data:$scope.manga})
				  .then(function (response) {
					$scope.mangas.push(response.data);
					 /*carregarClientes();
					 
					  $scope.manga={};*/
								  
				  }, function (response) {
				    console.log(response.data);
				    console.log(response.status);
				  });
			};
	    
	    $scope.roles = [
	        {id: 1, text: 'guest'},
	        {id: 2, text: 'user'},
	        {id: 3, text: 'customer'},
	        {id: 4, text: 'admin'}
	      ];
	      $scope.user = {
	        roles: [$scope.roles[1]]
	      };
	      $scope.checkAll = function() {
	        $scope.user.roles = angular.copy($scope.roles);
	      };
	      $scope.uncheckAll = function() {
	        $scope.user.roles = [];
	      };
	      $scope.checkFirst = function() {
	        $scope.user.roles = [];
	        $scope.user.roles.push($scope.roles[0]);
	      };
	      $scope.setToNull = function() {
	        $scope.user.roles = null;
	      };
	      
	 
	   
	$scope.carregarGeneros= function() {
			$http({
				  method: 'GET', url: 'http://localhost:8080/genero'})
				  .then(function (response) {
					$scope.generos=response.data;
				   console.log(response.data);
				   console.log(response.status);
				  }, function (response) {
				    console.log(response.data);
				    console.log(response.status);
				  });
			};
			
			
				
	
	
	 carregarClientes= function() {
	$http({
		  method: 'GET', url: 'http://localhost:8080/manga'})
		  .then(function (response) {
			$scope.mangas=response.data;
		   console.log(response.data);
		   console.log(response.status);
		  }, function (response) {
		    console.log(response.data);
		    console.log(response.status);
		  });
	};
	
	
	carregarAutor = function() {
		$http({
			  method: 'GET', url: 'http://localhost:8080/autor'})
			  .then(function (response) {
				$scope.autors=response.data;
			   
			  }, function (response) {
			    console.log(response.data);
			    console.log(response.status);
			  });
	};
	
	$scope.excluirMangas=function(manga) {
		$http({
			  method: 'DELETE', url: 'http://localhost:8080/manga/'+manga.id})
			  .then(function (response) {
				pos = $scope.mangas.indexOf(manga);
				$scope.mangas.splice(pos,1);
			  
			  }, function (response) {
			    console.log(response.data);
			    console.log(response.status)
			  });
	};
	
	
	
		
		
		
		$scope.alterarMangas = function(manga) {
			$scope.manga = angular.copy(manga);
		}
		
		$scope.cancelar = function() {
						$scope.manga = {};
		};
		
		carregarAutor(); 
		carregarClientes();
		$scope.carregarGeneros();
}) ;