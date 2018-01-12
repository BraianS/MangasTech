angular
.module("appCliente",['ui.router','ngRoute','ngFileUpload','ui.bootstrap'])
.config(["$stateProvider", "$urlRouterProvider",'$locationProvider','$compileProvider',function ($stateProvider, $urlRouterProvider,$locationProvider,$compileProvider) {
	$urlRouterProvider.otherwise("/");
	
	/* $compileProvider.imgSrcSanitizationWhitelist(/^\s(https|file|blob|cdvfile):|data:image\//);*/

	
	$stateProvider
	.state('manga', {
		url: '/',
		template : '<h1> Hello i´m / <h1/>',
		controller: "mainController",
		controllerAs: "vm"
	})/*
	.state('mangas', {
		url: '/manga',
		templateUrl : '/view/manga.html',
		controller: "mangasController",
		controllerAs: "vm"
	})*/
	.state('grupos', {
		url: '/grupos',
		templateUrl : '/view/grupo.html',
		controller: "grupoController",
		controllerAs: "vm"
	})
	.state('mangasDetalhe', {
		url: '/manga/:mangasId',
		templateUrl : '/view/manga-detalhe.html',
		controller: "mangaDetalheController",
		controllerAs : "vm"
	})
	.state('capituloDetalhe', {
		url: '/manga/capitulo/:capituloId',
		templateUrl : '/view/capitulo.html',
		controller: "capituloController",
		controllerAs: "vm"
	})	
	.state('capitulos', {
		url: '/capitulos',
		templateUrl : '/view/adiciona-capitulo.html',
		controller: "adicionaCapituloController",
		controllerAs :"vm"
	})
	.state('login', {
		url: '/login',
		templateUrl : '/view/login.html',
		controller : 'loginController',
		controllerAs: 'vm'
	})
	.state('acessoNegado', {
		templateUrl : '/view/acessoNegado.html',
		controller: 'acessoNegadoController',
		controllerAs : 'vm'
	})
	.state('generos', {
		url: '/generos',
		templateUrl : '/view/genero.html',
		controller: "generosController",
		controllerAs :"vm"
	})
	.state('autor', {
		url: '/autor',
		templateUrl: '/view/autor.html',
		controller : "autorController",
		controllerAs: "vm"
	})
	.state('list',{
		url: '/manga?page:pages',
		controller: 'listaController',
		controllerAs: 'vm',
		templateUrl: '/view/lista.html',
		params: {
			pages: {
				value : '0',
				squash : true
			},
			sort: {
				value : 'upvotes',
				squash: true
			}
		}
	})
	.state('listaOrdenada', {
		url : '/manga?az:ordenado',
		controller: 'listaController',
		controllerAs : 'vm',
		templateUrl: '/view/lista.html',
		/*params: {
			ordenado: {
				value : 'a',
				squash: true
			}
		}*/
		resolve: {
			getModel: function (someService) {
				return someService.get();
			}
		}
	})
	
	$locationProvider.html5Mode(true);
	
}])
	.filter('startFrom', function(){
		return function(data, start) {
			return data.slice(start);
		}
	})

angular
.module('appCliente')
.filter('startsWithLetter', function() {
	  return function(items, letter) {

	    var filtered = [];
	    var letterMatch = new RegExp(letter, 'i');
	    for (var i = 0; i < items.length; i++) {
	      var item = items[i];
	      if (letterMatch.test(item.name.substring(0, 1))) {
	        filtered.push(item);
	      }
	    }
	    return filtered;
	  };
	});

angular
.module('appCliente')
.service('someService', [
	'$http', function ($http) {
		var model;
		
		return {
			get : function(letter) {
				return $http.get('http://localhost:8080/manga/az/'+letter).then(function(response) {
					model = response.data.content;
				});
			},
			getModel : function () {
				return model;
			},
			setModel: function(newModel) {
				model = newModel;
			}
		};
	}
])

/*angular
.module('appCliente')
.run(function ($rootScope, $location) {
	var rotasBloqueadasUsuariosNaoLogados = ['/generos', '/autor'];
	var rotasBloqueadasUsuariosComuns = ['/generos'];
	$rootScope.$on('$locationChangeStart', function () {
		if($rootScope.usuarioLogado == null && rotasBloqueadasUsuariosNaoLogados.indexOf($location.path()) != -1){
			$location.path('/acessoNegado');
		} else 
			if($rootScope.usuarioLogado != null &&
					rotasBloqueadasUsuariosComuns.indexOf($location.path()) != -1 &&
					$rootScope.usuarioLogado.admin == false) {
				$location.path('/acessoNegado')
			}
				
			
	})
})*/
	