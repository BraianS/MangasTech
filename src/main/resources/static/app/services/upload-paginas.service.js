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

        function salvarPaginas(pagina, capitulo, count) {
            return Upload.upload({
                url: '/api/pagina',
                method: 'POST',
                arrayKey: '',
                data: { paginas: pagina, capitulo: capitulo, numCapitulo: count },
                uploadEventHandlers: {
                    progress: function (evt) {
                        pagina.progress = Math.min(100, parseInt(100.0 *
                            evt.loaded / evt.total))
                    }
                }
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