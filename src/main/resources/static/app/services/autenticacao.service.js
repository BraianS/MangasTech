(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('autenticacaoService', autenticacaoService);

    //Injeta as dependências
    autenticacaoService.$inject = ['$http'];

    function autenticacaoService($http) {
        return {
            carregarNovosCapitulos: carregarNovosCapitulos,
            carregarMangasPorUmDia: carregarMangasPorUmDia,
            carregarMangasDoisDiasAtras: carregarMangasDoisDiasAtras,
            carregarNovosMangas: carregarNovosMangas,
            carregarTop10Mangas: carregarTop10Mangas
        }

        function carregarNovosCapitulos(dataHoje) {
            return $http.get('/api/manga/listarCapitulosPorData?date=' + dataHoje)
                .then(getCarregarNovosCapitulos)
                .catch(getCarregarNovosCapitulosError);

            function getCarregarNovosCapitulos(response) {
                return response.data;
            }

            function getCarregarNovosCapitulosError(error) {
                console.log("Erro ao carregar capitulos de hoje: " + dataHoje);
                return error.data;
            }
        }

        function carregarMangasPorUmDia(dataOntem) {
            return $http.get('/api/manga/listarCapitulosPorData?date=' + dataOntem)
                .then(getCarregarMangasPorUmDia)
                .catch(getCarregarMangasPorUmDiaError);

            function getCarregarMangasPorUmDia(response) {
                return response.data;
            }

            function getCarregarMangasPorUmDiaError(error) {
                console.log("Erro ao carregar capitulos de ontem:" + dataOntem + " " + error.data.message);
                return error.data.message;
            }
        }

        function carregarMangasDoisDiasAtras(dataAnteOntem) {
            return $http.get('/api/manga/listarCapitulosPorData?date=' + dataAnteOntem)
                .then(getCarregarMangasDoisDiasAtras)
                .catch(getCarregarMangasDoisDiasAtrasError);

            function getCarregarMangasDoisDiasAtras(response) {
                return response.data;
            }

            function getCarregarMangasDoisDiasAtrasError(error) {
                console.log("erro ao carregar capitulos de Ante ontem: " + dataAnteOntem);
                return error.data;
            }
        }

        function carregarNovosMangas() {
            return $http.get('/api/manga/top10')
                .then(getCarregarNovosMangas)
                .catch(getCarregarNovosMangasError);

            function getCarregarNovosMangas(response) {
                return response.data;
            }

            function getCarregarNovosMangasError(error) {
                console.log("erro ao carregar novos mangas");
                return error.data;
            }
        }

        function carregarTop10Mangas() {
            return $http.get('/api/manga/listaAcessos')
                .then(getCarregarListaTop10Mangas)
                .catch(getCarregarListaTop10MangasError);

            function getCarregarListaTop10Mangas(response) {
                return response.data;
            }

            function getCarregarListaTop10MangasError(error) {
                console.log("erro ao carregar lista top 10 mangas");
                return error.data;
            }
        }
    }
})();