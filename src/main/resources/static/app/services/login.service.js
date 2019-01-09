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

        function login(login) {
            return $http({
                method: 'POST',
                url: '/api/auth/autenticar',
                data:login
            }).then(getLogin)
                .catch(getLoginError);

            function getLogin(response) {
                return response.data;
            }

            function getLoginError(error) {
                console.log("Erro no login");
                return error.data;
            }
        }
    }
})();