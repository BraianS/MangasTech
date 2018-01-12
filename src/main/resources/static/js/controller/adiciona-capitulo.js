angular
.module("appCliente")
.controller("adicionaCapituloController", function($http) {
	
	var vm = this;
	vm.Model = {};
	
		
	
	vm.Model.Capitulo = {};
	vm.Model.Mangas = [];
	vm.Model.Grupos = [];
	
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
				vm.Model.Grupos = response.data;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
	};
	
	
	
	vm.carregarMangas();
	vm.carregarGrupos();
})