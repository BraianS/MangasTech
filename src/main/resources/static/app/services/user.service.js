(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('userService', userService);

    //Injeta as dependências
    userService.$inject = ['$http'];

    function userService($http) {
        return {
            carregarUsuarios: carregarUsuarios,
            salvarUsuario: salvarUsuario,
            deletarUsuario: deletarUsuario,
            atualizarUsuario: atualizarUsuario
        }

        function carregarUsuarios() {
            return $http.get('/api/usuario')
                .then(getCarregarUsuarios)
                .catch(getCarregarUsuariosError);

            function getCarregarUsuarios(response) {
                return response.data;
            }

            function getCarregarUsuariosError(error) {
                console.log("Erro ao carregar usuario");
                return error.data;
            }
        }

        function salvarUsuario(usuario) {
            return $http({
                method: 'POST',
                url: '/api/usuario', data: usuario
            }).then(getSalvarUsuario)
                .catch(getUsuarioErro);

            function getSalvarUsuario(response) {
                return "Salvo com Sucesso";
            }

            function getUsuarioErro(error) {
                console.log("Erro ao salvar usuario");
                return error.data.message;
            }
        }

        function deletarUsuario(usuario) {
            return $http.delete("/api/usuario/" + usuario.id)
                .then(getDeletarUsuario)
                .catch(getDeletarUsuarioError);

            function getDeletarUsuario(response) {
                return "Usuario: " + usuario.nome + " deletado";
            }

            function getDeletarUsuarioError(error) {
                console.log("Erro ao deletar usuario");
                return error.data.message;
            }
        }

        function atualizarUsuario(id,usuario) {
            return $http({
                method: 'PUT',
                url: '/api/usuario/'+id, data: usuario
            })
                .then(getAtualizarUsuario)
                .catch(getAtualizarUsuarioError);

            function getAtualizarUsuario(response) {
                return "Atualizado com sucesso";
            }

            function getAtualizarUsuarioError(error) {
                console.log("Erro ao atualizar usuario");
                return error.data.message;
            }
        }
    }
})();