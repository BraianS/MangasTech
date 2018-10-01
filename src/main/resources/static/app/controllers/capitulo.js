(function () {
	'use strict';
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('capituloController', capituloController);

	//Injeta as dependências
	capituloController.$inject = ['capituloService', '$stateParams'];

	function capituloController(capituloService, $stateParams) {

		var vm = this;

		vm.fotos = [];
		vm.pagina = 1;
		vm.totalElementos = [];
		vm.carregarCapitulo = carregarCapitulo;
		vm.capituloId = $stateParams.capituloId;

		carregarCapitulo();

		function carregarCapitulo() {
			return capituloService.carregarPaginas(vm.capituloId, vm.pagina)
				.then(function (data) {

					vm.totalElementos = data.totalElements;
					vm.fotos = data.content;
				})
		}

		/* bind para pegar o evento das setas do teclado */
		$('#capituloDiv').bind('keydown', function (e) {
			//Se a seta pressionada for da Esquerda
			if (e.keyCode === 37) {
				if (vm.pagina > 1) {
					vm.pagina--;
					carregarCapitulo();
				}
				else {
					vm.pagina = vm.pagina;
					console.log('Limite de paginas');
				}
				console.log("Esquerda");
				return false;
			}

			//Se a seta pressionada for da Direita
			if (e.keyCode === 39) {
				if (vm.pagina < vm.totalElementos) {
					vm.pagina++;
					carregarCapitulo();
				} else {
					vm.pagina = vm.pagina;
					console.log('Limite de paginas');
				}
				console.log("Direita");
				return false;
			}
		});

		//Focus
		$(function () {
			$('#capituloDiv').focus();
		});
	}
})();