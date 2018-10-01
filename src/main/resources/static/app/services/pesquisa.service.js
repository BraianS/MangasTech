(function () {
	'use strict';
	/* Adiciona o factory ao modulo */
	angular
		.module('appCliente')
		.factory('pesquisaService', pesquisaService);

	//Injeta as dependências
	pesquisaService.$inject = ['$http'];

	function pesquisaService($http) {

		var nome;

		return {
			getNome: getNome,
			setNome: setNome,
			pesquisaNome: pesquisaNome
		}

		function pesquisaNome(nome, numPagina) {
			return $http.get('/user/manga/nome/' + nome + '?page=' + numPagina)
				.then(getNomesucesso)
				.catch(getErro);

			function getNomesucesso(response) {
				return response.data;
			}

			function getErro(error) {
				return "erro ao buscar nome";
			}
		}

		function getNome() {
			return nome;
		}

		function setNome(nomePesquisado) {
			nome = nomePesquisado;
		}
	}
})();
