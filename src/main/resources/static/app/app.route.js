(function() {
	
	//Cria o modulo AppCliente
	//Seta as Dependências
	angular
		.module('appCliente', ["ui.bootstrap", "appCliente.admin", "appCliente.run"]);

	//Injeta as dependencias
	MainModuleConfig.$inject = ["$locationProvider", "$stateProvider", "$urlRouterProvider"];

	//Configuração do Modulo Principal
	function MainModuleConfig($locationProvider, $stateProvider, $urlRouterProvider) {

		//Redireciona se tiver uma rota invalida		
		$urlRouterProvider.otherwise('/');
		
		//Remove a <base> do index e #!/ da url
		$locationProvider.html5Mode({
			enabled: true,
			requireBase: false
		});

		var nav = {
			name: 'nav',
			url: "/",
			views: {
				'navView': {
					templateUrl: '/app/views/nav.html',
					controller: 'navController',
					controllerAs: "vm"
				},
				'home': {
					templateUrl: '/app/views/home.html',
					controller: 'homeController',
					controllerAs: 'vm'
				}
			}
		};

		var grupo = {
			name: 'nav.grupo',
			url: 'grupo',
			needToLogin: false,
			views: {
				'home@': {
					templateUrl: '/app/views/grupo.html',
					controller: "grupoController",
					controllerAs: "vm"
				}
			}
		};

		var grupoDetalhe = {
			name: 'nav.grupo.detalhe',
			url: '/:gruposId',
			needToLogin: false,
			views: {
				'home@': {
					templateUrl: '/app/views/grupo-detalhe.html',
					controller: 'grupoDetalheController',
					controllerAs: 'vm'
				}
			}
		};

		var genero = {
			name: 'nav.genero',
			url: 'genero',
			needToLogin: false,
			views: {
				'home@': {
					templateUrl: '/app/views/genero.html',
					controller: 'generosController',
					controllerAs: 'vm'
				}
			}
		};

		var generoDetalhe = {
			name: 'nav.genero.detalhe',
			url: '/:generoId',
			needToLogin: false,
			views: {
				'home@': {
					templateUrl: '/app/views/generoDetalhe.html',
					controller: 'generodetalhe',
					controllerAs: 'vm'
				}
			}
		};

		var autor = {
			name: 'nav.autor',
			url: 'autor',
			needToLogin: false,
			params: { dateFrom: null, dateTo: null },
			views: {
				'home@': {
					templateUrl: '/app/views/autor.html',
					controller: 'autorController',
					controllerAs: 'vm'
				}
			}
		};

		var autorDetalhe = {
			name: 'nav.autor.detalhe',
			url: '/:autorId',
			needToLogin: false,
			views: {
				'home@': {
					templateUrl: '/app/views/autordetalhe.html',
					controller: 'autorDetalhe',
					controllerAs: 'vm'
				}
			}
		};

		var manga = {
			name: 'nav.manga',
			url: 'manga?page:pages',
			needToLogin: false,
			views: {
				'home@': {
					templateUrl: '/app/views/manga.html',
					controller: 'mangaController',
					controllerAs: 'vm'
				}
			}
		};

		var mangaDetalhe = {
			name: 'nav.manga.detalhe',
			url: '/:mangasId',
			needToLogin: false,
			views: {
				'home@': {
					templateUrl: '/app/views/manga-detalhe.html',
					controller: 'mangaDetalheController',
					controllerAs: 'vm'
				}
			}
		};

		var pesquisa = {
			name: 'nav.pesquisa',
			url: '/:txtPesquisado',
			reload: true,
			needToLogin: false,
			views: {
				'home@': {
					templateUrl: '/app/views/pesquisa.html',
					controller: 'pesquisaController',
					controllerAs: 'vm'
				}
			}
		};

		var capitulo = {
			name: 'nav.capitulo',
			url: 'manga/capitulo/:capituloId',
			needToLogin: false,
			reload: true,
			views: {
				'home@': {
					templateUrl: '/app/views/capitulo.html',
					controller: 'capituloController',
					controllerAs: 'vm'
				}
			}
		};

		var registrar = {
			name: 'nav.registrar',
			url: 'registrar',
			views: {
				'home@': {
					templateUrl: '/app/views/registrar.html',
					controller: 'registrarController',
					controllerAs: 'vm'
				}
			}
		};

		var login = {
			name: 'nav.login',
			url: 'login',
			views: {
				'home@': {
					templateUrl: '/app/views/login.html',
					controller: 'loginController',
					controllerAs: 'vm'
				}
			}
		};

		var acessoNegado = {
			name: 'acessoNegado',
			url: 'acesso-negado',
			needToLogin: false,
			views: {
				'home@': {
					templateUrl: '/app/views/acessoNegado.html'
				}
			}
		};

		//Adiciona cada variavel a sua rota
		$stateProvider
			.state('nav', nav)
			.state('nav.grupo', grupo)
			.state('nav.grupo.detalhe', grupoDetalhe)
			.state('nav.genero', genero)
			.state('nav.genero.detalhe', generoDetalhe)
			.state('nav.autor', autor)
			.state('nav.autor.detalhe', autorDetalhe)
			.state('nav.manga', manga)
			.state('nav.manga.detalhe', mangaDetalhe)
			.state('nav.pesquisa', pesquisa)
			.state('nav.capitulo', capitulo)
			.state('nav.registrar', registrar)
			.state('nav.login', login)
			.state('nav.acessoNegado', acessoNegado);
	}

	//Adiciona a configuração ao modulo
	angular
		.module('appCliente')
		.config(MainModuleConfig);
})();