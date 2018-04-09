angular
.module("appCliente")
.controller("admCapituloController", function($http,$scope,Upload,$stateParams){
	var vm = this;
	
	vm.Model = {};
	
	vm.Model.Capitulo = {};
	vm.Model.Mangas = [];
	vm.Model.Grupos = [];
	
	$scope.mangasId = $stateParams.mangasId;
	
	
	
	vm.selectI = null;
	vm.selecionar = sel;
	vm.selecionarItem = [];
	function sel(){
		vm.Model.Mangas.forEach(function(item){
			if(item.id == vm.selectI){
				vm.selecionarItem = item
				alert(vm.selecionarItem.id);
			}
		});
	}
	
	vm.xele = null;
	vm.sele = sell;
	
	function sell(){
		vm.Model.capituloManga.forEach(function(item){
			if(item.id == vm.xele) {
				vm.xelecionaItem = item;
			}
		})
	}
	
	vm.Model.ListCapitulos = [];
	
	vm.carregarCapitulos = function () {
		$http({
			method :'GET', url : "capitulodetalhe/"+$stateParams.mangasId
		}).then(function (response){
			vm.Model.ListCapitulos = response.data;
			console.log(response);
			console.log(response.data);
		}, function (response){
			console.log(response);
			console.log(response.data);
		})
	};
	
	vm.numero = [];
	
	vm.Model.capituloManga = [];
	
	vm.capManga = function () {
	$http({
		method :'GET', url : 'http://localhost:8080/capitulo/'+vm.selecionarItem
	}).then(function (response){
		vm.Model.capituloManga = response.data;
		console.log(response);
		console.log(response.data);
	}, function (response){
		console.log(response);
		console.log(response.data);
	})
}
	
	vm.valor = 1;
	vm.meuMetodo = function(){
		alert(vm.valor);
	}
	
	vm.capManga();
	
	vm.Model.title = "ola eu sou o adicionaController.js";
	
	vm.salvarCapitulos = function () {
		$http({
			method: 'POST' , url: 'http://localhost:8080/capitulo',data:vm.Model.Capitulo})
			.then(function () {
				vm.Model.Capitulo = {};
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
	};
	
	vm.carregarMangas = function () {
		$http({
			method: 'GET' , url: 'http://localhost:8080/manga'})
			.then(function (response) {
				vm.Model.Mangas = response.data.content;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
	};
	
	vm.carregarGrupos = function () {
		$http({
			method: 'GET' , url: 'http://localhost:8080/grupo'})
			.then(function (response) {
				vm.Model.Grupos = response.data.content;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
	};
	
	
	
	vm.carregarMangas();
	vm.carregarGrupos();
	
	$scope.sizes = [ {code: 1, name: 'n1'}, {code: 2, name: 'n2'} ];
	$scope.update = function() {
		console.log($scope.item.code, $scope.item.name)
	}
	
	$scope.nome = [];
	$scope.capitulo = [];
	
	vm.uploadFiles2 = function (fotos) {			
		if (fotos && fotos.length) {
			Upload.upload({
				url : 'http://localhost:8080/pagina',
					method: 'POST',	
					params: {nome:$scope.nome, capitulo: vm.valor},
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
})