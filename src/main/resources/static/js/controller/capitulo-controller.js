angular
.module("appCliente")
.controller("capituloController",['$scope', '$routeParams','$rootScope','$http','$location','$stateParams',function ($scope, $routeParams,$rootScope, $http, $location,$stateParams) {
	var vm = this;
	vm.Model = {};
	
	$scope.capituloId = $stateParams.capituloId;
	$scope.mangasId = $stateParams.mangasId;
	
		
	vm.Model.paginas = {};
	$scope.fotos = [];
	vm.fotinha = [];
	
	
	vm.pegar = function () {
		$http({
			method : 'GET', url: "http://localhost:8080/capitulo/"+$stateParams.capituloId,
			responseType: 'arraybuffer'
				})
			.then(function(response) {
				var str = _arrayBufferToBase64(response.data);
			    console.log(str);
			    vm.Model.paginas = response.data;
				console.log(response.data);
				   console.log(response.status);				   
				console.log("deu certo")
			}, function(response) {
				console.log(response.data);
				   console.log(response.status);
			})
	};
	vm.pegar();
	
	vm.carregarFoto = function() {
		$http({
			  method: 'GET', url: "http://localhost:8080/capitulo/"+$stateParams.capituloId,
			  responseType: 'arraybuffer'
			 })
			  .then(function (response) {
				  $scope.fotos=response.data;
				var str = _arrayBufferToBase64(response.data);
			    console.log(str);
			    console.log("Deu certo 2");
			   console.log(response.data);
			   console.log(response.status);
			  }, function (response) {
			    console.log(response.data);
			    console.log(response.status);
			    console.error('error in getting static img.');
			  });
		};
		
		vm.carregarFoto();
		
		
		vm.carregarMangas = function() {
			$http({
				  method: 'GET', url: 'capitulo/'+$stateParams.capituloId,
				  responseType: 'blob',
				  headers: {'Content-Type': 'image/jpg'}})				 
				  .then(function (response) {
				  vm.fotinha =response.data;
				  console.log("deu certo 3");
				   console.log(response.data);
				   console.log(response.status);
				   return 'data:image/jpeg;base64,' + response.data;
				  }, function () {
				    console.log(response.data);
				    console.log(response.status);
				  });
			};
			vm.carregarMangas();
			
			vm.img = function(data) {
				   return 'data:image/jpeg;base64,' + data;
				};
	
	function _arrayBufferToBase64(buffer) {
	    var binary = '';
	    var bytes = new Uint8Array(buffer);
	    var len = bytes.byteLength;
	    for (var i = 0; i < len; i++) {
	      binary += String.fromCharCode(bytes[i]);
	    }
	    return window.btoa(binary);
	  }
	
	var itemsUrl = "http://localhost:8080/capitulo/"+$stateParams.capituloId;
	var config = { responseType: 'blob' };

	$http.get(itemsUrl, config).then(function onSuccess(response) {
	    var blob = response.data;
	    var contentType = response.headers("content-type");
	    var fileURL = URL.createObjectURL(blob);
	    window.open(fileURL); 
	});
	
	
	
}]);

