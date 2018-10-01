(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('homeService', homeService);

    //Injeta as dependências
    homeService.$inject = ['$http'];

    function homeService($http) {
        return {
            carregarNovosCapitulos: carregarNovosCapitulos,
            carregarMangasPorUmDia: carregarMangasPorUmDia,
            carregarMangasDoisDiasAtras: carregarMangasDoisDiasAtras,
            carregarNovosMangas: carregarNovosMangas
        }

        function carregarNovosCapitulos(dataHoje) {
            return $http.get('/user/capitulo/lista/ordenado?date=' + dataHoje)
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
            return $http.get('/user/capitulo/lista/ordenado?date=' + dataOntem)
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
            return $http.get('/user/capitulo/lista/ordenado?date=' + dataAnteOntem)
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
            return $http.get('/user/manga/top10')
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
    }
})();