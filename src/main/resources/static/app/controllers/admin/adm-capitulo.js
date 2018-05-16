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
	vm.selecionarItem = null;
	function sel(){
		vm.Model.Mangas.forEach(function(item){
			if(item.id == vm.selectI){
				vm.selecionarItem = item
				console.log('capitulo: '+vm.selecionarItem.id);
			}
		});
	}
	
	$scope.$watch('assistir', function() {
		console.log('Numero do capitulo :'+vm.valor)
	})
	
	vm.xele = null;
	vm.sele = sell;
	
	function sell(){
		vm.Model.capituloManga.forEach(function(item){
			if(item.id == vm.xele) {
				vm.xelecionaItem = item;
				alert('capitulos :'+vm.xelectionaItem)
			}
		})
	}
	
	vm.Model.ListCapitulos = [];
	
	vm.carregarCapitulos = function () {
		$http({
			method :'GET', url : "/user/capitulo/detalhe/"+$stateParams.mangasId
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
		method :'GET', url : '/user/capitulo/'+vm.selecionarItem.id
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
		console.log('valor: '+vm.valor);
	}
	
	
	vm.salvarCapitulos = function () {
		if(vm.formCapitulo.$valid) {
		$http({
			method: 'POST' , url: '/admin/capitulo',data:vm.Model.Capitulo})
			.then(function () {
				vm.Model.Capitulo = {};
				vm.formCapitulo.$setPristine(true);
				vm.mensagem = "Salvo com sucesso";
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
		}
		else {
			vm.mensagem = "Cadastro Nao realizado";
		}
	};
	
	vm.carregarMangas = function () {
		$http({
			method: 'GET' , url: '/user/manga'})
			.then(function (response) {
				vm.Model.Mangas = response.data.content;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
	};
	
	vm.carregarGrupos = function () {
		$http({
			method: 'GET' , url: '/user/grupo'})
			.then(function (response) {
				vm.Model.Grupos = response.data.content;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
	};
	
	
	
	vm.carregarMangas();
	vm.carregarGrupos();
	
	
	
	$scope.nome = [];
	$scope.capitulo = [];
	
	vm.uploadFiles = function (fotos) {	
		if(vm.formPagina) {
		if (fotos && fotos.length) {
			Upload.upload({
				url : '/user/pagina',
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
				vm.mensagemPagina = "Salvo com sucesso";
				vm.formPagina.$setPristine(true);
			}), function(response) {
				console.log("error");
				alert("deu erro");
			}
		}
	  }
		else 
			{
				vm.mensagemPagina = "Nao foi salvo";
			}
	}
})