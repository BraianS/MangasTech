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
				
				templateUrl:'/app/views/nav.html',
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
				templateUrl : '/app/views/home.html',
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
				templateUrl : '/app/views/grupo.html',
				
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
				templateUrl:'/app/views/grupo-detalhe.html',
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
				templateUrl: '/app/views/genero.html',
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
				templateUrl: '/app/views/generoDetalhe.html',
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
				templateUrl: '/app/views/lista.html'									
			}
		}
	})
	.state('mangaDetalhe',{
		parent: 'nav',
		url: '/manga/:mangasId',
		needToLogin : false,
		views : {
			'content@' :{
				templateUrl: '/app/views/manga-detalhe.html',
				controller: 'mangaDetalheController',
				controllerAs: 'vm'
			}
		}
		
	})
	.state('capituloDetalhes',{
		parent: 'nav',
		url : '/manga/capitulo/:capituloId',
		neetToLogin: false,
		reload: true,
		views : {
			'content@' : {
				templateUrl : '/app/views/capitulo.html',
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
				templateUrl: '/app/views/login.html',
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
				templateUrl : '/app/views/acessoNegado.html',
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
				templateUrl: '/app/views/autor.html',
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
				templateUrl: '/app/views/autordetalhe.html',
				controller: 'autorDetalhe',
				controllerAs:'vm'					
			}
		}
		
	})
	.state('pesquisa',{
	parent: 'nav',
	url: '/pesquisa/:txtPesquisado',
	reload: true,
	needToLogin: false,
		views: {
			'content@' : {
				templateUrl: '/app/views/pesquisa.html',
				controller: 'pesquisaController',
				controllerAs: 'vm'				
			}
		}
	})
	
	.state('lista',{
		parent : 'nav',
		url: '/manga?page:pages',
		needToLogin: false,
		views : {
			'content@' : {
				templateUrl: '/app/views/lista.html',
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
				templateUrl:'/app/views/registrar.html',
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
				templateUrl: '/app/views/admin/nav-admin.html'
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
				templateUrl: '/app/views/admin/adminCapitulos.html',
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
				templateUrl:'/app/views/admin/adminMangas.html',
				controllerAs: "vm"
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
				templateUrl: '/app/views/admin/adminAutor.html',
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
				templateUrl : '/app/views/admin/adminGrupos.html',
				controller:'adminGruposController',
				controllerAs : 'vm'
			}
		}
	})
	.state('admgenero',{
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
				templateUrl: '/app/views/admin/adminGeneros.html',
				controllerAs: "vm"
			}
		}
	})
	.state('admusuario',{
		parent: 'admin', 
			url:'/admuser',
			data: { 
				role: 'ADMIN'
			},
			needToLogin: true,
			views: {
				'admin@' : {
					url: '/admin/usuario',
					controller: 'admUsuarioController',
					templateUrl: '/app/views/admin/user.html',
					controllerAs: 'vm'
				}
			}
		
	})	
});