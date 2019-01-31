(function () {
    'use strict';

    angular
        .module('appCliente')
        .controller('comentarioController', comentarioController);

    comentarioController.$inject = ['mangaService', 'AuthService', '$stateParams', '$http', '$state', 'comentarioService'];

    function comentarioController(mangaService, AuthService, $stateParams, $http, $state, comentarioService) {

        var vm = this;

        vm.mangaId = $stateParams.mangaId;
        vm.capituloId = $stateParams.capituloId;
        vm.mangaNome = {};
        vm.comentario = {};
        vm.comentarioPai = {};
        vm.idSelecionado = {};
        vm.salvarcomentario = salvarComentario;
        vm.salvarComentarioPai = salvarComentarioPai;
        vm.buscarUsuarioAutenticado = buscarUsuarioAutenticado;
        vm.cancelar = cancelar;
        vm.deletarComentario = deletarComentario;
        vm.atualizarComentario = atualizarComentario;
        vm.editar = false;
        vm.usernameAutenticado = [];
        vm.selecionarResposta = selecionarResposta;
        vm.atualizar = atualizar;
        vm.submit = submit;
        vm.submitComentarioPai = submitComentarioPai;

        function selecionarResposta (id) {
            vm.idSelecionado = id;
        }

        buscarUsuario();
        buscarMangaNome();
        buscarComentariosDoCapitulo();

        function buscarUsuario() {
            if (AuthService.user == null) {
                return false;
            } else {
                vm.usernameAutenticado = AuthService.user.username;
            }
        }

        function buscarUsuarioAutenticado() {
            if (AuthService.user == null) {
                $state.go('nav.registrar');
            } else {
                return false;
            }
        }

        function buscarMangaNome() {
            return mangaService.buscarMangaPorId(vm.mangaId)
                .then(function (data) {
                    vm.mangaNome = data.nome;
                })
        }

      function submit() {
            if (vm.editar) {
                atualizarComentario();
                vm.editar = false;
            } else {
                salvarComentario()
            }
        }

        function submitComentarioPai(comentarioId){
            if(vm.editar){
                atualizarComentario();
                vm.editar = false;
            } else {
                salvarComentarioPai(comentarioId);
            }
        }

       function atualizar(comentario) {
            vm.idSelecionado = comentario.id;
            vm.comentarioPai = comentario;
            vm.editar = true;
        }

        function cancelar() {
            vm.comentario = {};
            vm.idSelecionado = {};
            vm.comentarioPai = {};
        }

        function salvarComentario() {
            return comentarioService.salvarComentario(vm.capituloId, vm.comentario)
                .then(function (response) {
                    vm.formComentario.$setPristine(true);
                    cancelar();
                    buscarComentariosDoCapitulo();
                    console.log(response);
                })
        }

        function salvarComentarioPai(comentarioId) {
            return comentarioService.salvarComentarioPai(comentarioId, vm.comentarioPai)
                .then(function (response) {
                    vm.comentarioPai = {};
                    vm.comentario = {};
                    buscarComentariosDoCapitulo();
                    vm.cancelar();
                    console.log(response);
                })
        }

        function buscarComentariosDoCapitulo() {
            return comentarioService.buscarComentariosPorCapituloId(vm.capituloId).then(function (response) {
                vm.comentarios = response.data;
                console.log(response);
            })
        }

        function deletarComentario(comentarioId) {
            return comentarioService.deletarComentario(comentarioId)
                .then(function (response) {
                    console.log(response);
                    buscarComentariosDoCapitulo();
                    vm.cancelar();
                })
        }

        function atualizarComentario() {
            return comentarioService.atualizarComentario(vm.comentarioPai)
                .then(function (response) {
                    console.log(response);
                    buscarComentariosDoCapitulo();
                    vm.cancelar();
                })
        }
    }
})()