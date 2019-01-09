(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('registrarService', registrarService);

    //Injeta as dependências
    registrarService.$inject = ['$http'];

    function registrarService($http) {
        return {
            registrarUsuario: registrarUsuario
        }

        function registrarUsuario(usuario) {
            return $http({
                method: 'POST',
                url: '/api/auth/registrar', data: usuario
            })
                .then(getUsuarioSalvo)
                .catch(getUsuarioError);

            function getUsuarioSalvo(response) {
                return "Registrado";
            }

            function getUsuarioError(error) {
                console.log("Erro ao registrar usuario");
                return error.data.message;
            }
        }
    }
})();