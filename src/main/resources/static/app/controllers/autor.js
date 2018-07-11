angular
	.module("appCliente")
	.controller("autorController", [ '$http', function ($http) {
		var vm = this;		

		vm.autor = [];
	
		vm.paginaAutor = 1;
		vm.paginaPorLetra = 1;
		vm.mudar = false;

		var alfabeto = "abcdefghijklmnopqrstuvwxyz";
		
		vm.letra = alfabeto.toUpperCase().split("");

		vm.ativado = "";		

		vm.resetar = function() {
			vm.ativado = "";
			vm.paginaAutor = 1;
		}			
		/* 
			Atualiza os Autores
		*/
		vm.carregarAutores = function () {
			$http({
				method: 'GET', url: '/user/autor?page='+vm.paginaAutor})
				.then(function (response) {
					vm.autor = response.data.content;
					vm.size = response.data.size;
					vm.totalElementos = response.data.totalElements;
					console.log(response.data);
					console.log(response.status);
				}, function (response) {
					console.log(response);
				})
		};
		/*
			Busca por letras passado como parametro
		*/ 
		vm.buscarPorLetra = function(letra) {
			if(letra != null) {
				vm.ativado = letra;
			}
			$http({method: 'GET', url: '/user/autor/letra/'+vm.ativado+"?page="+vm.paginaPorLetra})
			.then(function(response){
				vm.autor = response.data.content;
					vm.s = response.data.size;
					vm.t = response.data.totalElements;
				console.log(response);
				console.log(response.data);
			}, function(response) {
				console.log(response);
				console.log(response.data);
			})
		};

		vm.carregarAutores();

	}]);