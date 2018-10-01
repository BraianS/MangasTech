(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('grupoService', grupoService);

    //Injeta as dependências
    grupoService.$inject = ['$http'];

    function grupoService($http) {
        return {
            listaGrupos: listaGrupos,
            carregarGrupos: carregarGrupos,
            buscarAutorPorId: buscarAutorPorId,
            salvarGrupos: salvarGrupos,
            excluirGrupos: excluirGrupos,
            buscarPorLetra: buscarPorLetra
        }

        function listaGrupos() {
            return $http.get('/user/grupo/lista')
                .then(getListaGrupo)
                .catch(getListaGruposError);

            function getListaGrupo(response) {
                return response.data;
            }

            function getListaGruposError(error) {
                console.log("Erro ao listar grupos");
                return error.data;
            }
        }

        function carregarGrupos(numeroPagina) {
            return $http.get('/user/grupo?page=' + numeroPagina)
                .then(getCarregarGrupos)
                .catch(getCarregarGruposError);

            function getCarregarGrupos(response) {
                return response.data;
            }

            function getCarregarGruposError(error) {
                console.log("erro ao carregar grupos");
                return error.data;
            }
        }

        function buscarAutorPorId(grupoId, pagina) {
            return $http.get('/user/grupo/' + grupoId + "?page=" + pagina)
                .then(getBuscarAutorPorId)
                .catch(getBuscarAutorPorIdError);

            function getBuscarAutorPorId(response) {
                return response.data;
            }

            function getBuscarAutorPorIdError(error) {
                console.log("Erro ao retornar grupo");
                return error.data;
            }
        }

        function salvarGrupos(grupo) {
            return $http({
                method: 'POST',
                url: '/admin/grupo', data: grupo
            }).then(getsalvarGrupos)
                .catch(getsalvarGruposError);

            function getsalvarGrupos(response) {
                return "Salvo com sucesso";
            }

            function getsalvarGruposError(error) {
                console.log("Erro ao salvar grupo: " + grupo.nome);
                return error.data;
            }
        }

        function excluirGrupos(grupo) {
            return $http.delete('/admin/grupo/' + grupo.id)
                .then(getExcluirGrupos)
                .catch(getExcluirGruposError);

            function getExcluirGrupos(response) {
                return "Grupo: " + grupo.nome + " Deletado";
            }

            function getExcluirGruposError(error) {
                console.log("Erro ao  excluir grupo: " + grupo.nome);
                return error.data;
            }
        }

        function buscarPorLetra(letra, pagina) {
            return $http.get('/user/grupo/letra/' + letra + '?page=' + pagina)
                .then(getBuscarPorLetra)
                .catch(getBuscarPorLetraErro);

            function getBuscarPorLetra(response) {
                return response.data;
            }

            function getBuscarPorLetraErro(error) {
                console.log("Erro ao pegar paginação por letra");
                return error.data;
            }
        }
    }
})();