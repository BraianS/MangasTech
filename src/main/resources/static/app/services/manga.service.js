(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('mangaService', mangaService);

    //Injeta as dependências
    mangaService.$inject = ['$http'];

    function mangaService($http) {
        return {
            listaMangas: listaMangas,
            carregarMangas: carregarMangas,
            salvarMangas: salvarMangas,
            excluirMangas: excluirMangas,
            atualizarManga: atualizarManga,
            buscarMangaPorLetra: buscarMangaPorLetra,
            buscarMangaPorId: buscarMangaPorId
        };

        function listaMangas() {
            return $http.get('/api/manga/nomes')
                .then(getListaMangas)
                .catch(getListaMangasError);

            function getListaMangas(response) {
                return response.data;
            }

            function getListaMangasError(error) {
                console.log("Erro ao listar mangas");
                return error.data;
            }
        }

        function carregarMangas(numPagina) {
            return $http.get('/api/manga?page=' + numPagina)
                .then(getCarregarMangas)
                .catch(getCarregarMangasError);

            function getCarregarMangas(response) {
                return response.data;
            }

            function getCarregarMangasError(error) {
                console.error('Erro ao carregar manga, numero da pagina: ' + numPagina);
            }
        }

        function salvarMangas(manga, imagem) {
            return $http({
                method: 'POST',
                url: '/api/manga',
                headers: { 'Content-Type': undefined },
                transformRequest: function (data) {
                    var formData = new FormData();
                    formData.append('mangas', new Blob([angular.toJson(data.mangas)], {
                        type: "application/json"
                    }));
                    formData.append('file', data.file);
                    return formData;
                },
                data: { mangas: manga, file: imagem }
            })
                .then(getSalvarMangas)
                .catch(getSalvarMangasError)

            function getSalvarMangas(response) {
                return "Salvo com suceso";
            }

            function getSalvarMangasError(error) {
                console.error("Erro ao salvar manga: " + manga.nome + " imagem: " + imagem.name)
                return error.data.message;
            }
        }

        function excluirMangas(manga) {
            return $http.delete('/api/manga/' + manga.id)
                .then(getExcluirManga)
                .catch(getExcluirMangaError);

            function getExcluirManga(response) {
                return "Manga: " + manga.nome + " deletado";
            }

            function getExcluirMangaError(error) {
                console.error("Erro ao excluir manga: " + manga.nome);
                return error.data;
            }
        }

        function atualizarManga(manga, imagem) {
            return $http({
                method: 'PUT',
                url: '/api/manga',
                headers: { 'Content-Type': undefined },
                transformRequest: function (data) {
                    var formData = new FormData();
                    formData.append('mangas', new Blob([angular.toJson(data.mangas)], {
                        type: 'application/json'
                    }));
                    formData.append('file', data.file);
                    return formData;
                },
                data: { mangas: manga, file: imagem }
            }).then(getAtualizarManga)
                .catch(getAtualizarMangaError);

            function getAtualizarManga(response) {
                return "Alterado com sucesso";
            }

            function getAtualizarMangaError(error) {
                console.error("Erro ao atualizar: " + manga.nome);
                return error.data;
            }
        }

        function buscarMangaPorLetra(letra, pagina) {
            return $http.get('/api/manga/az/' + letra + '?page=' + pagina)
                .then(getBuscarMangaPorLetra)
                .catch(getBuscarMangaPorLetraError);

            function getBuscarMangaPorLetra(response) {
                return response.data;
            }

            function getBuscarMangaPorLetraError(error) {
                console.log("Erro ao buscar manga por letra");
                return error.data;
            }
        }

        function buscarMangaPorId(mangaId) {
            return $http.get('/api/manga/' + mangaId)
                .then(getMangaId)
                .catch(getMangaIdError);

            function getMangaId(response) {
                return response.data;
            }

            function getMangaIdError(error) {
                console.log("Erro ao buscar id do manga");
                return error.data;
            }
        }
    }
})();