(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('autorService', autorService);

    //Injeta as dependências
    autorService.$inject = ['$http'];

    function autorService($http) {
        return {
            buscarAutorPorLetra: buscarAutorPorLetra,
            buscarAutorPorId: buscarAutorPorId,
            listaAutores: listaAutores,
            carregarAutores: carregarAutores,
            salvarAutor: salvarAutor,
            atualizarAutor: atualizarAutor,
            excluirAutor: excluirAutor
        }

        function buscarAutorPorId(autorId, numPagina) {
            return $http.get('/user/autor/' + autorId + '?page=' + numPagina)
                .then(getBuscarAutorPorId)
                .catch(getBuscarAutorPorIdErro);

            function getBuscarAutorPorId(response) {
                return response.data;
            }

            function getBuscarAutorPorIdErro(error) {
                console.log('Erro ao buscar autor');
                return error.data;
            }
        }

        function buscarAutorPorLetra(letra, numPagina) {
            return $http.get('/user/autor/letra/' + letra + "?page=" + numPagina)
                .then(getBuscarAutorPorLetra)
                .catch(getBuscarAutorPorLetraError);

            function getBuscarAutorPorLetra(response) {
                return response.data;
            }

            function getBuscarAutorPorLetraError(error) {
                console.log("Erro ao buscar por letra: " + letra);
                return error.data;
            }
        }

        function listaAutores() {
            return $http.get('/user/autor/lista')
                .then(getListaAutor)
                .catch(getLisAutorError);

            function getListaAutor(response) {
                return response.data;
            }

            function getLisAutorError(error) {
                console.log("Erro ao listar autores");
                return error.data;
            }
        }

        function carregarAutores(numeroPagina) {
            return $http.get('/user/autor?page=' + numeroPagina)
                .then(getCarregarAutores)
                .catch(getCarregarAutoresError);

            function getCarregarAutores(response) {
                return response.data;
            }

            function getCarregarAutoresError(error) {
                console.log("Erro ao carregar autores");
                return error.data;
            }
        }

        function salvarAutor(autor) {
            return $http({
                method: 'POST',
                url: '/admin/autor', data: autor
            }).then(getSalvarAutor)
                .catch(getSalvarAutorError);

            function getSalvarAutor(response) {
                return "Salvo com sucesso";
            }

            function getSalvarAutorError(error) {
                console.log("Erro ao salvar autor: " + autor.nome);
                return error.data.message;
            }
        }

        function atualizarAutor(autor) {
            return $http({
                method: 'PUT',
                url: '/admin/autor', data: autor
            }).then(getAtualizarAutor)
                .catch(getAtualizarAutorError);

            function getAtualizarAutor(response) {
                return "Autor: " + autor.nome + " Atualizado";
            }

            function getAtualizarAutorError(error) {
                console.log("Erro ao atualizar autor: " + autor.nome);
                return error.data.message;
            }
        }

        function excluirAutor(autor) {
            return $http.delete('/admin/autor/' + autor.id)
                .then(getExcluirAutor)
                .catch(getExcluirAutorError);

            function getExcluirAutor(response) {
                return "Autor: " + autor.nome + " Deletado";
            }

            function getExcluirAutorError(error) {
                console.log("Erro ao deletar autor: " + autor.nome);
                return error.data;
            }
        }
    }
})();