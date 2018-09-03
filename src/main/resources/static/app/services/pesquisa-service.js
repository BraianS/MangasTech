angular
	.module('appCliente')
	.service('pesquisaService', function () {

		var nome;

		var pesquisaService = {
			getNome: getNome,
			setNome: setNome
		};

		return pesquisaService;

		function getNome() {
			return nome;
		}

		function setNome(nomePesquisado) {
			nome = nomePesquisado
		}

	});