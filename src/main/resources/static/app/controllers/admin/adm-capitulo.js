/* angular
.module("appCliente")
.controller("admCapituloController",['$http','Upload', function($http,Upload){ */

(function() {
	angular
		.module('appCliente');

	angular
		.module('appCliente')
		.controller('admCapituloController',admCapituloController);

		admCapituloController.$inject = ['$scope','$http','$stateParams','Upload'];

		function admCapituloController($scope,$http,$stateParams,Upload) {
			var vm = this;
	
			vm.Model = {};
			
			vm.Model.Capitulo = {};
			vm.Model.Mangas = [];
			vm.Model.Grupos = [];		
				
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
			
			vm.carregarCapitulos = function() {
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
			
			vm.capManga = function() {
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
			
			
			vm.salvarCapitulos = function() {
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
				$http({	method: 'GET' , url: '/user/manga/lista'})
					.then(function (response) {
						vm.Model.Mangas = response.data;
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
						url : '/admin/pagina',
							method: 'POST',	
							params: {nome:$scope.nome, capitulo: vm.valor},
							  arrayKey: '',
							  data: { fotos: fotos},
							  
							  headers: { 'Content-Type':undefined}
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
		};
})();


	 
	
