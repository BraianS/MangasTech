(function () {
    'use strict';
    //Cria o modulo appCliente.admin
    //Seta as Dependências 
    angular
        .module('appCliente.admin', ['ui.router', 'ngFileUpload']);

    //Injeta as dependencias
    adminConfig.$inject = ['$stateProvider'];

    //Configuração do Modulo Admin
    function adminConfig($stateProvider) {

        var admin = {
            name: 'nav.admin',
            url: 'admin',
            data: {
                role: 'ROLE_ADMIN'
            },
            needToLogin: true,
            views: {
                'home@': {
                    templateUrl: '/app/views/admin/nav-admin.html'
                }
            }
        };

        var capitulo = {
            name: 'nav.admin.capitulo',
            url: '/capitulo',
            data: {
                role: 'ROLE_ADMIN'
            },
            needToLogin: true,
            views: {
                'adminView': {
                    templateUrl: '/app/views/admin/admin-capitulos.html',
                    controller: 'admCapituloController',
                    controllerAs: 'vm'
                }
            }
        };

        var manga = {
            name: 'nav.admin.Manga',
            url: '/manga',
            views: {
                'adminView': {
                    templateUrl: '/app/views/admin/admin-mangas.html',
                    controller: 'admMangaController',
                    controllerAs: 'vm',

                }
            }
        };

        var autor = {
            name: 'nav.admin.autor',
            url: '/autor',
            views: {
                'adminView': {
                    templateUrl: '/app/views/admin/admin-autor.html',
                    controller: 'admAutorController',
                    controllerAs: 'vm'
                }
            }
        };

        var grupo = {
            name: 'nav.admin.grupo',
            url: '/grupo',
            views: {
                'adminView': {
                    templateUrl: '/app/views/admin/admin-grupos.html',
                    controller: 'adminGruposController',
                    controllerAs: 'vm'
                }
            }
        };

        var genero = {
            name: 'nav.admin.genero',
            url: '/genero',
            views: {
                'adminView': {
                    templateUrl: '/app/views/admin/admin-generos.html',
                    controller: 'admGenerosController',
                    controllerAs: 'vm'
                }
            }
        };

        var usuario = {
            name: 'nav.admin.usuario',
            url: '/usuario',
            views: {
                'adminView': {
                    templateUrl: '/app/views/admin/user.html',
                    controller: 'admUsuarioController',
                    controllerAs: 'vm'
                }
            }
        };

        //Adiciona cada variavel a sua rota
        $stateProvider
            .state('nav.admin', admin)
            .state('nav.admin.capitulo', capitulo)
            .state('nav.admin.manga', manga)
            .state('nav.admin.autor', autor)
            .state('nav.admin.grupo', grupo)
            .state('nav.admin.genero', genero)
            .state('nav.admin.usuario', usuario);
    }

    //Adiciona a configuração ao modulo
    angular
        .module('appCliente.admin')
        .config(adminConfig);
})();