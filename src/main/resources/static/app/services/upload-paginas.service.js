(function () {
    'use strict';
    /* Adiciona o factory ao modulo */
    angular
        .module('appCliente')
        .factory('uploadService', uploadService);

    //Injeta as dependências
    uploadService.$inject = ['Upload'];

    function uploadService(Upload) {
        return {
            salvarPaginas: salvarPaginas
        }

        function salvarPaginas(pagina, descricao, capitulo, count) {

            return Upload.upload({
                url: '/admin/pagina',
                method: 'POST',
                arrayKey: '',
                data: { paginas: pagina[count], nome: descricao, capitulo: capitulo, numCapitulo: count }
            }).then(getsalvarPaginas)
                .catch(getsalvarPaginasError);

            function getsalvarPaginas(response) {
                return "Salvo com sucesso";
            }

            function getsalvarPaginasError(error) {
                console.log("Erro ao salvar paginas");
                return error.data;
            }
        }
    }
})();
