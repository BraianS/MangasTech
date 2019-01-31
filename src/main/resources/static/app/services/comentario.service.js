(function(){
    'use strict';

    angular
        .module('appCliente')
        .factory('comentarioService',comentarioService);

    comentarioService.$inject = ['$http'];

    function comentarioService($http){
        return {
            salvarComentario: salvarComentario,
            salvarComentarioPai:salvarComentarioPai,
            buscarComentariosPorCapituloId:buscarComentariosPorCapituloId,
            deletarComentario:deletarComentario,
            atualizarComentario:atualizarComentario
        }

        function salvarComentario(capituloId,comentario){
            return $http({
                method:'POST',
                url:'/api/comentario/'+capituloId,
                data:comentario
            }).then(getSalvarComentario)
                .catch(getSalvarComentarioError);
            
                function getSalvarComentario(response){
                    return "Salvo com sucesso";
                }

                function getSalvarComentarioError(error){
                    console.log("Erro salvar comentario: "+ capituloId);
                    return error.data.message;
                }
        }

        function salvarComentarioPai(comentarioId,comentario){
            return $http({
                method:'POST',
                url:'/api/comentarioPai/'+comentarioId,
                data:comentario
            }).then(getSalvarComentarioPai)
                .catch(getSalvarComentarioPaiError);

            function getSalvarComentarioPai(response){
                return "salvo com sucesso";
            }

            function getSalvarComentarioPaiError(error){
                console.log("Error salvar comentario pai"+comentarioId);
                return error.data.message;
            }
        }

        function buscarComentariosPorCapituloId(capituloId){
          return  $http({
                method:'GET',
                url:'/api/comentario/'+capituloId
            }).then(getBuscarComentarioPorCapituloId)
                .catch(getBuscarComentarioPorCapituloIdError);

            function getBuscarComentarioPorCapituloId(response){
                return response;
            }

            function getBuscarComentarioPorCapituloIdError(error){
                console.log("Erro buscar comentarios");
                return error;
            }
        }

        function deletarComentario(comentarioId){
            return $http({
                method:'DELETE',
                url:'/api/comentario/'+comentarioId
            }).then(getDeletarComentario)
                .catch(getDeletarComentarioError);

            function getDeletarComentario(response){
                console.log("Capitulo deletado");
            }

            function getDeletarComentarioError(error){
                return error;
            }
        }

        function atualizarComentario(comentario){
            return $http({
                method:'PUT',
                url:'/api/comentario',
                data:comentario
            }).then(getAtualizarComentario)
                .catch(getAtualizarComentarioError);
            
            function getAtualizarComentario(response){
                return "atualizado com sucesso";
            }

            function getAtualizarComentarioError(error){
                console.log("Erro atualizar comentario");
                return error;
            }
        }
    }
})();