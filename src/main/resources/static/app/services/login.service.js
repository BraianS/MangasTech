(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('loginService', loginService);

    //Injeta as dependências
    loginService.$inject = ['$http'];

    function loginService($http) {
        return {
            login: login
        }

        function login(username, password) {
            return $http({
                method: 'POST',
                url: '/autenticar',
                params: {
                    username: username,
                    password: password
                }
            }).then(getLogin)
                .catch(getLoginError);

            function getLogin(response) {
                return response.data;
            }

            function getLoginError(error) {
                console.log("Erro no login");
                return "Autenticação falhou";
            }
        }
    }
})();