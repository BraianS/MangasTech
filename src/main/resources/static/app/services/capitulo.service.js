(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('capituloService', capituloService);

    //Injeta as dependências
    capituloService.$inject = ['$http', '$log'];

    function capituloService($http) {
        return {
            listaCapitulosPorManga: listaCapitulosPorManga,
            carregarPaginas: carregarPaginas,
            excluirCapitulo: excluirCapitulo,
            carregarCapituloPorManga: carregarCapituloPorManga,
            salvarCapitulos: salvarCapitulos
        }

        function carregarPaginas(capituloId, numPagina) {
            return $http.get('/user/pagina/' + capituloId + '?page=' + numPagina)
                .then(getAutor)
                .catch(getAutorErro);

            function getAutor(response) {
                return response.data;
            }

            function getAutorErro(error) {
                console.log('Erro ao carregar paginas');
                return error.data;
            }
        }

        function listaCapitulosPorManga(mangaId) {
            return $http.get('/user/capitulo/lista/' + mangaId)
                .then(getListaCapitulosPorManga)
                .catch(getListaCapitulosPorMangaError);

            function getListaCapitulosPorManga(response) {
                return response.data;
            }

            function getListaCapitulosPorMangaError(error) {
                console.log("Erro ao listar de capitulos por manga");
                return error.data;
            }
        }

        function excluirCapitulo(capitulo) {
            return $http.delete('/user/capitulo/lista/' + capitulo.id)
                .then(getExcluirCapitulo)
                .catch(getCapituloError);

            function getExcluirCapitulo(response) {
                return "Capitulo: " + capitulo.capitulo + " Deletado";
            }

            function getCapituloError(error) {
                console.log("Erro ao excluir capitulo");
                return error.data;
            }
        }

        function carregarCapituloPorManga(manga) {
            return $http.get('/user/capitulo/' + manga)
                .then(getCapituloManga)
                .catch(getCapituloErro);

            function getCapituloManga(response) {
                return response.data;
            }

            function getCapituloErro(error) {
                console.log("Erro ao carregar capitulo por manga: " + manga.nome);
                return error.data;
            }
        }

        function salvarCapitulos(capitulo) {
            return $http({
                method: 'POST',
                url: '/admin/capitulo',
                data: capitulo
            }).then(getCapitulo)
                .catch(getCapituloError);

            function getCapitulo(response) {
                return "Salvo com sucesso";
            }

            function getCapituloError(error) {
                console.log("Erro ao salvar manga");
                return error.data;
            }
        }
    }
})();