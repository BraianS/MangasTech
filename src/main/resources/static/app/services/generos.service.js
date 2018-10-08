(function () {
	'use strict';
	/* Adiciona o factory ao modulo */
	angular
		.module('appCliente')
		.factory('generoService', generoService);

	//Injeta as dependências
	generoService.$inject = ['$http'];

	function generoService($http) {
		return {
			buscarGeneroPorId: buscarGeneroPorId,
			carregarGeneros: carregarGeneros,
			listaGeneros: listaGeneros,
			salvarGenero: salvarGenero,
			atualizarGenero: atualizarGenero,
			excluirGenero: excluirGenero
		}

		function buscarGeneroPorId(generoId, numPagina) {
			return $http.get('/api/genero/' + generoId + '?page=' + numPagina)
				.then(getBuscarGeneroPorId)
				.catch(getGeneroErro);

			function getBuscarGeneroPorId(response) {
				return response.data;
			}

			function getGeneroErro(error) {
				console.log("Erro ao buscar genero");
				return error.data;
			}
		}

		function carregarGeneros() {
			return $http.get('/api/genero')
				.then(getCarregarGeneros)
				.catch(getCarregarGenerosErro);

			function getCarregarGeneros(response) {
				return response.data;
			}

			function getCarregarGenerosErro(error) {
				console.log("Erro ao carregar genero");
				return error.data;
			}
		}

		function listaGeneros() {
			return $http.get('/api/genero/lista')
				.then(getListaGeneros)
				.catch(getListaGenerosError);

			function getListaGeneros(response) {
				return response.data;
			}

			function getListaGenerosError(error) {
				console.log("Erro ao listar generos: " + error.data);
				return error.data;
			}
		}

		function salvarGenero(genero) {
			return $http({
				method: 'POST',
				url: '/api/genero', data: genero
			}).then(getSalvarGenero)
				.catch(getSalvarGeneroError);

			function getSalvarGenero(response) {
				return "Salvo com sucesso";
			}

			function getSalvarGeneroError(error) {

				return error.data.message;
			}
		}

		function atualizarGenero(genero) {
			return $http({
				method: 'PUT',
				url: '/api/genero', data: genero
			}).then(getAtualizarGenero)
				.catch(getAtualizarGenerosError);

			function getAtualizarGenero(response) {
				return "Genero: " + genero.nome + " Atualizado";
			}

			function getAtualizarGenerosError(error) {
				console.log("Erro atualizar genero: " + genero.nome);
				return error.data;
			}
		}

		function excluirGenero(genero) {
			return $http.delete('/api/genero/' + genero.id)
				.then(getExcluirGenero)
				.catch(getExcluirGenerosError);

			function getExcluirGenero(response) {
				return "Genero: " + genero.nome + " Deletado";
			}

			function getExcluirGenerosError(error) {
				console.log("Erro ao excluir genero: " + genero.nome);
				return error.data;
			}
		}
	}
})();