angular
.module('appCliente')
.config(function($httpProvider ,$locationProvider,$stateProvider , $urlRouterProvider) {
$urlRouterProvider.otherwise('/pagina-nao-encontrada');
		
	 $locationProvider.html5Mode({
		  enabled: true	,
		requireBase: false
		});
	
	$stateProvider.state('nav',{
		abstract : true,
		url:'',		
		views : {
			'nav@' : {
				
				templateUrl:'/view/nav.html',
				controller:'navController',
				controllerAs:"vm"
			}
		}
	
	})
	.state('home',{	
		parent : 'nav',
		url: '/',
		needToLogin : false,
		views : {
			'content@' : {
				templateUrl : '/view/home.html',
				controller : 'homeController',
				controllerAs : 'vm'
			}
		}		
	})
		
	.state('grupos', {
		parent: 'nav',
		url: '/grupo',
		needToLogin: false,
		views: {
			'content@' :{
				templateUrl : '/view/grupo.html',
				
				controller: "grupoController",
				controllerAs: "vm"
			}
		}		
	})
	.state('grupodetalhe',{
		parent: 'nav',
		url:'/grupodetalhe/:gruposId',
		needToLogin: false,
		views : {
			'content@' : {
				templateUrl:'/view/grupo-detalhe.html',
				controller: 'grupoDetalheController',
				controllerAs: 'vm'
			}
		}
		
	})
	.state('generos',{
		parent: 'nav',
		url: '/genero',
		needToLogin : false,
		views: {
			'content@': {
				templateUrl: '/view/genero.html',
				controller: "generosController",
				controllerAs: "vm"
			}
		}
	})
	.state('generosDetalhe',{
		parent: 'nav',
		url:'/genero/:generoId',
		needToLogin: false,
		views : {
			'content@' : {
				templateUrl: '/view/generoDetalhe.html',
				controller: 'generodetalhe',
				controllerAs :'vm'
			}
		}		
	})
	.state('manga', {
		parent: 'nav',
		url: '/manga?az:ordenado',
		views : {
			'content@' :{
				controller: 'listaController',
				controllerAs: 'vm',
				templateUrl: 'view/lista.html'									
			}
		},
		
		params: {
			pages : {
				value : '0',
				squash: true
			}
		}
	})
	.state('mangaDetalhe',{
		parent: 'nav',
		url: '/manga/:mangasId',
		needToLogin : false,
		views : {
			'content@' :{
				templateUrl: '/view/manga-detalhe.html',
				controller: 'mangaDetalheController',
				controllerAs: 'vm'
			}
		}
		
	})
	.state('capituloDetalhes',{
		parent: 'nav',
		url : '/manga/capitulo/:capituloId',
		neetToLogin: false,
		views : {
			'content@' : {
				templateUrl : '/view/capitulo.html',
				controller: 'capituloController',
				controllerAs : 'vm'
			}
		}
	})
	.state('login',{
		parent: 'nav',
		url: '/login',
		views : {
			'content@' : {
				templateUrl: '/view/login.html',
				controller: 'loginController',
				controllerAs: 'vm'
			}
		}
	})
	.state('acesso-negado', {
		parent : 'nav',
		url: '/acesso-negado',
		views : {
			'content@' : {
				templateUrl : '/view/acessoNegado.html',
				controller: 'acessoNegadoController',
				controllerAs : 'vm'
			}	
		}		
	})
	.state('pagina-nao-encontrada',{
		parent : 'nav',
		url : 'pagina-nao-encontrada',
		views : {
			'content@' : {
				template: '<h1> Pagina nao encontrada </h1>'
			}
		}
	})
	.state('autor',{
		parent : 'nav',
		url: '/autor',
		neetToLogin: false,
		views :{
			'content@' : {
				templateUrl: '/view/autor.html',
				controller : 'autorController',
				controllerAs : 'vm'
			}
		}		
	})
	.state('autorDetalhe',{
		parent: 'nav',
		url: '/autor/:autorId',
		needToLogin: false,
		views : {
			'content@' : {
				templateUrl: '/view/autordetalhe.html',
				controller: 'autorDetalhe',
				controllerAs:'vm'					
			}
		}
		
	})
	.state('pesquisa',{
	parent: 'nav',
	url: '/pesquisa',
		views: {
			'content@' : {
				templateUrl: '/view/pesquisa.html',
				controller: 'pesquisaController',
				controllerAs: 'vm'				
			}
		}
	})
	/*.state('admgrupo',{
		parent : 'nav',
		url: '/admgrupo',
		data : {
			role : 'ADMIN'
		},
		needToLogin: true,
		views : {
			'content@' : {
				templateUrl : '/view/admin/adminGrupos.html',
				controller:'adminGruposController',
				controllerAs : 'vm'
			}
		}
	})
	.state('admautor',{
		parent :'nav',
		url:'/admautor',
		data : {
			role : 'ADMIN'
		},
		needToLogin: true,
		views : {
			'content@' :{
				url:'/admin/admautor',
				templateUrl: '/view/admin/adminAutor.html',
				controller:'admAutorController',
				controllerAs: "vm"
			}
		}
	})
	.state('admgenero',{
		parent :'nav',
		url: '/admgenero',
		data : {
			role : 'ADMIN'
		},
		needToLogin: true,
		views : {
			'content@' : {
				url:'/admin/admgenero',
				controller:'admGenerosController',
				templateUrl: '/view/admin/adminGeneros.html',
				controllerAs: "vm"
			}
		}
	})
	.state('admmangas',{
		parent : 'nav',
		url: '/admmangas',
		data : {
			role : 'ADMIN'
		},
		needToLogin: true,
		views : {
			'content@' :{
				url: '/admin/admmangas',
				controller: 'admMangaController',
				templateUrl:'/view/admin/adminMangas.html',
				controllerAs: "vm"
			}
		}
	})
	.state('admcapitulo',{
		parent : 'nav',
		url : '/admcapitulo',
		data : {
			role : 'ADMIN'
		},
		needToLogin: true,
		views : {
			'content@' : {
				url: '/admin/admcapitulo',
				templateUrl: '/view/admin/adminCapitulos.html',
				controller: 'admCapituloController',
				controllerAs: 'vm'
			}
		}
		
	})*/
	.state('lista',{
		parent : 'nav',
		url: '/manga?page:pages',
		needToLogin: false,
		views : {
			'content@' : {
				templateUrl: '/view/lista.html',
				controller: 'listaController',
				controllerAs : 'vm'					
			}
		}		
	})
	.state('registrar',{
		parent:'nav',
		url:'/registrar',
		views : {
			'content@' : {
				templateUrl:'/view/registrar.html',
				controller: 'registrarController',
				controllerAs:'vm'
			}
		}
		
	})
	.state('admin',{
		parent: 'nav',
		url:'/admin',
		data: {
			role: 'ADMIN'
		},
		views : {
			'content@': {
				templateUrl: '/view/admin/admin.html'
			}
		}		
	})
	
	.state('admcap',{
		parent: 'admin',
		url: '/capitulo',
		data : {
			role : 'ADMIN'
		},
		needToLogin: true,
		views : {
			'admin@' : {				
				url: '/admin/admcapitulo',
				templateUrl: '/view/admin/adminCapitulos.html',
				controller: 'admCapituloController',
				controllerAs: 'vm'
			}
		}
		
	})
	.state('admmangar',{
		parent : 'admin',
		url: '/admmangas',
		data : {
			role : 'ADMIN'
		},
		needToLogin: true,
		views : {
			'admin@' :{
				url: '/admin/admmangas',
				controller: 'admMangaController',
				templateUrl:'/view/admin/adminMangas.html',
				controllerAs: "vm"
			}
		},
		params: {
			pages : {
				value : 0,
				squash: true
			}
		}
	})
	.state('admautorr',{
		parent :'admin',
		url:'/admautor',
		data : {
			role : 'ADMIN'
		},
		needToLogin: true,
		views : {
			'admin@' :{
				url:'/admin/admautor',
				templateUrl: '/view/admin/adminAutor.html',
				controller:'admAutorController',
				controllerAs: "vm"
			}
		}
	})
	.state('admgrupor',{
		parent : 'admin',
		url: '/admgrupo',
		data : {
			role : 'ADMIN'
		},
		needToLogin: true,
		views : {
			'admin@' : {
				templateUrl : '/view/admin/adminGrupos.html',
				controller:'adminGruposController',
				controllerAs : 'vm'
			}
		}
	})
	.state('admgenerorr',{
		parent :'admin',
		url: '/admgenero',
		data : {
			role : 'ADMIN'
		},
		needToLogin: true,
		views : {
			'admin@' : {
				url:'/admin/admgenero',
				controller:'admGenerosController',
				templateUrl: '/view/admin/adminGeneros.html',
				controllerAs: "vm"
			}
		}
	})
	/*.state('listaOrdenada',{
		parent: 'nav',
		url: 'manga?az:ordenado',
		views : {
			'content@' : {
				templateUrl: '/view/lista.html',
				controller: 'listaController',
				controllerAs: 'vm'
			}
		}
	})*/
	/*.state('listaOrdenada', {
		url : '/manga?az:ordenado',
		controller: 'listaController',
		controllerAs : 'vm',
		templateUrl: '/view/lista.html',
		params: {
			ordenado: {
				value : 'a',
				squash: true
			}
		}
		resolve: {
			getModel: function (someService) {
				return someService.get();
			}
		}
	})*/
});