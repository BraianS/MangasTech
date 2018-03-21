angular
.module("appCliente")
.controller("mangaDetalheController",['$scope', '$routeParams','$http','multipartForm','Upload','$stateParams',function ($scope, $routeParams, $http, multipartForm,Upload,$stateParams){

	$scope.mangasId = $stateParams.mangasId;
	
	$scope.ola = "ola mundo eu sou o manga detalhe";
		
	$scope.customer = {};
	
	//usa a diretiva MULTIPARTFORM pra salvar foto por foto.
	$scope.Submit = function(){
		var uploadUrl = 'http://localhost:8080/pagina';
		multipartForm.post(uploadUrl,$scope.customer),
		 alert("file uploaded successfully.");
	}
	
	$scope.mmm = [];
	
	
		
	var vm = this;
	vm.Model = {};
	vm.Model.addPagina = [];
	vm.Model.ListCapitulos = [];
	vm.Model.manga = [];
	
	$scope.items = [
		{id:1, name:'foo'},
		{id:2, name:'bar'},
		{id:3,name: 'bla'}
	]
	
		
	vm.carregarMangas = function() {
		$http({
			method: 'GET', url: 'http://localhost:8080/manga/'+$stateParams.mangasId
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
	vm.carregarMangas();
	vm.carregarCapitulos = function () {
		$http({
			method :'GET', url : "capitulo/"+$stateParams.mangasId
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
	                url: 'http://localhost:8080/pagina',
	                method: 'POST',
	                
	                data: {
	                	files: files,
	                	 arrayKey: '[]',
	                	 
	                }
	            });

	            });
	        }
	/*$scope.files = {};
	$scope.files.fotos = "";*/
	
	//percorre cada foto adicionando uma de cada vez
	vm.uploadFiles = function (files, errFiles) {  		
		  angular.forEach(files, function (fotos) {                
		    fotos.upload = Upload.upload({
		      url: 'http://localhost:8080/pagina',//url:upload,
		      method: 'POST',
		      headers: { 'Content-Type':undefined},
		      arrayKey: '',
		      data: { 'nome' : vm.c
		      },
		      fields: {'nome': vm.c},
		      fotos: fotos,
		    });
		    fotos.upload.then(function (response) {
		      console.log("sucesso, contador é: ");
		      files = "";
		      
		      
		    }, function (response) {
		      console.log("error");
		    });
		  });
		};
		
		vm.c = [];
		
		$scope.nome = [];
		$scope.capitulo = [];
		
		//adiciona multiplas fotos, um array de fotos
		vm.uploadFiles2 = function (fotos) {			
			if (fotos && fotos.length) {
				Upload.upload({
					url : 'http://localhost:8080/pagina',
						method: 'POST',	
						params: {nome:$scope.nome, capitulo: $scope.capitulo},
					      arrayKey: '',
					      data: { /* nome:$scope.nome,*/ fotos: fotos},
					      /*fields: {
					    	  nome:$scope.nome
					      },*/
					      headers: { 'Content-Type':undefined}/*,
					      transformRequest: function (data, headersGetter) {
		                        var formData = new FormData();
		                        angular.forEach(data, function (value, key) {
		                            formData.append(key, value);
		                        });
		                        return formData;
					    	  
					      }*/
				}).then (function (response) {
					console.log("sucesso");
					console.log();
					alert("salvo com sucesso");
				}), function(response) {
					console.log("error");
					alert("deu erro");
				}
			}
		}
		
}]);



angular
.module("appCliente")
.directive('ngFileModel', [ '$parse', function($parse) {
    return {
        restrict : 'A',
        link : function(scope, element, attrs) {
            var model = $parse(attrs.ngFileModel);
            var isMultiple = attrs.multiple;
            var modelSetter = model.assign;

            element.bind('change', function() {
            	var values = [];
            	
            	angular.forEach(element[0].files, function (item) {
            		var value = {
            				name: item.name,
            				url: URL.createObjectURL(item),     				
            				
            				_fotos: item
            		};
            		values.push(value);
            	});
                scope.$apply(function() {
                  /*  modelSetter(scope, element[0].files[0]);*/
                	if(isMultiple) {
                		 modelSetter(scope, element[0].files[0]);
                	} else {
                		modelSetter(scope, values[0]);
                	}
                });
            });
        }
    };
} ]);


angular
.module("appCliente")
.service('multipartForm', ['$http', function ($http){
	this.post = function (upLoadUrl, data){
		var fd = new FormData();
		for(var key in data)
			fd.append(key, data[key]);
		$http.post(upLoadUrl, fd, {
			transformRequest: angular.indentity,
			arrayKey: '',
			headers: { 'Content-Type':undefined}
		})
	}
}])
