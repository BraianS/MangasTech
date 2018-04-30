angular
.module("appCliente")
.controller("mangaDetalheController",['$scope', '$routeParams','$http','$stateParams',function ($scope, $routeParams, $http, $stateParams){

	$scope.mangasId = $stateParams.mangasId;
	
	$scope.ola = "ola mundo eu sou o manga detalhe";
				
	var vm = this;
	vm.Model = {};
	vm.Model.addPagina = [];
	vm.Model.ListCapitulos = [];
	vm.Model.manga = [];
			
	vm.carregarMangas = function() {
		$http({
			method: 'GET', url: '/user/manga/'+$stateParams.mangasId
		}).then(function(response) {
			console.log(response);
			console.log(response.data);
			$scope.mmm = response.data;
			vm.Model.manga = response.data;
		}, function (res) {
			console.log(res);
			console.log(res.data);
		})
	};
	
	vm.carregarCapitulos = function () {
		$http({
			method :'GET', url : "/user/capitulo/"+$stateParams.mangasId
		}).then(function (response){
			vm.Model.ListCapitulos = response.data;
			console.log(response);
			console.log(response.data);
		}, function (response){
			console.log(response);
			console.log(response.data);
		})
	}
	/*$http.get("manga/"+$stateParams.mangasId).then(function (response) {
		vm.Model.ListCapitulos = response.data;
	console.log(response.data);
	console.log(response.status);
	}, function (response) {
		console.log(response);
	});*/
	vm.carregarCapitulos();
	
	
	$scope.uploadFiles2 = function(files, errFiles) {
		files.forEach(function(e){$scope.files.push(e)})
	        $scope.errFiles = errFiles;
	        angular.forEach(files, function(files) {
	        	files.upload = Upload.upload({
	                url: '/pagina',
	                method: 'POST',
	                
	                data: {
	                	files: files,
	                	 arrayKey: '[]',
	                	 
	                }
	            });

	            });
	        }
			
		vm.carregarMangas();
		
}]);
