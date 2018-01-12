angular
.module("appCliente")
.controller("mangaDetalheController",['$scope', '$routeParams','$http','multipartForm','Upload','$stateParams',function ($scope, $routeParams, $http, multipartForm,Upload,$stateParams){

	$scope.mangasId = $stateParams.mangasId;
		
	$scope.customer = {};
	
	//usa a diretiva MULTIPARTFORM pra salvar foto por foto.
	$scope.Submit = function(){
		var uploadUrl = 'http://localhost:8080/pagina';
		multipartForm.post(uploadUrl,$scope.customer),
		 alert("file uploaded successfully.");
	}
		
	var vm = this;
	vm.Model = {};
	vm.Model.addPagina = [];
	vm.Model.ListCapitulos = {};
	
	vm.carregarMangas = function () {
		$http({
			method : "http://localhost:8080/manga/"+$stateParams.mangasId
		}).then(function (response){
			vm.Model.ListaCapitulos = response.data;
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
	vm.carregarMangas();
	
	$scope.uploadFiles2 = function(files, errFiles) {
		files.forEach(function(e){$scope.files.push(e)})
	        $scope.errFiles = errFiles;
	        angular.forEach(files, function(files) {
	        	files.upload = Upload.upload({
	                url: 'http://localhost:8080/pagina',
	                method: 'POST',
	                data: {
	                	files: files,
	                	 arrayKey: '[]'	                	
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
		      data: { fotos: fotos
		      },
		     
		    });
		    fotos.upload.then(function (response) {
		      console.log("sucesso, contador é: ");
		      files = "";
		      
		      
		    }, function (response) {
		      console.log("error");
		    });
		  });
		};
		
		
		//adiciona multiplas fotos, um array de fotos
		vm.uploadFiles2 = function (fotos) {
			vm.files = fotos; 
			if (fotos && fotos.length) {
				Upload.upload({
					url : 'http://localhost:8080/pagina',
						method: 'POST',
						 headers: { 'Content-Type':undefined},
					      arrayKey: '',
					      data: { fotos: fotos
					      },
				}).then (function (response) {
					console.log("sucesso");
				}), function(response) {
					console.log("error");
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
