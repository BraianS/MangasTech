(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('capituloService', capituloService);

    //Injeta as dependências
    capituloService.$inject = ['$http', '$state', 'Upload'];

    function capituloService($http, $state, Upload) {
        return {
            carregarPaginas: carregarPaginas,
            excluirCapitulo: excluirCapitulo,
            carregarCapituloPorManga: carregarCapituloPorManga,
            salvarCapitulos: salvarCapitulos,
            mudarDeCapitulo: mudarDeCapitulo,
            listaNumerosDePaginas: listaNumerosDePaginas
        }

        function carregarPaginas(capituloId, numPagina) {
            return $http.get('/api/pagina/' + capituloId + '?page=' + numPagina)
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

        function excluirCapitulo(manga,capitulo) {
            return $http.delete('/api/manga/'+manga.id+'/'+capitulo.id)
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
            return $http.get('/api/capitulo/' + manga)
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

        function salvarCapitulos(capitulo, paginas) {
            var cap = new Blob([angular.toJson(capitulo)], {
                type: "application/json"
            });
            return Upload.upload({
                method: 'POST',
                url: '/api/capitulo',
                headers: { 'Content-Type': undefined },
                arrayKey: '',
                data: { capitulo: cap, paginas: paginas }
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

        function listaNumerosDePaginas(capituloId) {
            return $http.get('/api/pagina/numeroDePaginas/' + capituloId)
                .then(getPaginas)
                .catch(getPaginasError);

            function getPaginas(response) {
                return response.data;
            }

            function getPaginasError(error) {
                console.log('Erro ao carregar paginas');
                return error.data;
            }
        }

        function mudarDeCapitulo(mangaId, capitulo, pagina) {
            return $http.get('/api/pagina/' + capitulo + '?page=' + pagina)
                .then(getMudarPagina)
                .catch(getMudarPaginaError);

            function getMudarPagina(response) {
                $state.go('nav.capitulo', { 'mangaId': mangaId, 'capituloId': capitulo })
                return response.data;
            }

            function getMudarPaginaError(error) {
                console.log("Erro ao carregar capitulo");
                return error.data;
            }
        }
    }
})();