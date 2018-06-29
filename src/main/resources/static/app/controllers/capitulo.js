angular
	.module("appCliente")
	.controller("capituloController", [ '$http', '$stateParams', function ( $http, $stateParams) {
		var vm = this;

		vm.capituloId = {};
	
		vm.fotos = [];
		vm.pagina = 1;

		 /* carrega as fotos */
		vm.carregarcapitulo = function () {
			$http({
				method: 'GET',
				url: '/user/pagina/' + vm.capituloId + "?page=" + vm.pagina
			})
				.then(function (response) {					
					vm.fotos = response.data.content;// guarda os valores na vm.fotos
					vm.number = response.data.number; /// numero da pagina atual
					vm.totalDePaginas = response.data.totalPages; //total de paginas da Pagination
					vm.items = response.data.totalElements; //total de items, elementos.
					vm.size = response.data.size; // item por por Pagination
				}, function (response) {
					console.log(response);
					console.log(response.data.content);
				})
		};
	
	
	function init() {
		console.log("Init work");
		vm.capituloId = $stateParams.capituloId;
		vm.carregarcapitulo();
	}
	/* inicia carregando as fotos */
	init();	


		/* bind para pegar o evento das setas do teclado */
		$('#mydiv').bind('keydown', function(e) {
			//Se a seta pressionada for da Esquerda
			if (e.keyCode === 37) {
				//Se pagina for maior que 1 ou igual
				//atualiza os Capitulos
				if (vm.pagina >= 1) {
					//subtrai vm.pagina
					vm.pagina--;					
					vm.carregarcapitulo();
					
				}
				//vm.pagina igual a 0
				if (vm.pagina === 0) {
					vm.pagina = 0;
				}
				console.log("Esquerda");
				
				return false;
			}

			//Se a seta pressionada for da Direita
			if (e.keyCode === 39) {
				//Se a vm.pagina menor que total de Paginas	
				if (vm.pagina < vm.totalDePaginas) {
					//soma vm.pagina
					vm.pagina++;					
				}
				vm.carregarcapitulo();
				console.log("Direita");
				return false;
			}
			
		});

		//Focus
		$(function() {
			$('#mydiv').focus();
		 });

	}]);
