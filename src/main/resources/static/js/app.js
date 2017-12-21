angular
.module("appCliente",['ui.router','ngRoute','ngFileUpload'])
.config(["$stateProvider", "$urlRouterProvider",'$locationProvider','$compileProvider',function ($stateProvider, $urlRouterProvider,$locationProvider,$compileProvider) {
	$urlRouterProvider.otherwise("/");
	
	 $compileProvider.imgSrcSanitizationWhitelist(/^\s(https|file|blob|cdvfile):|data:image\//);

	
	
	$stateProvider
	.state('manga', {
		url: '/',
		template : '<h1> Hello i´m / <h1/>',
		controller: "mainController",
		controllerAs: "vm"
	})
	.state('mangas', {
		url: '/manga',
		templateUrl : '/view/manga.html',
		controller: "mangasController",
		controllerAs: "vm"
	})
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
	.state('paginas', {
		url: '/paginas',
		templateUrl : '/view/adiciona-pagina.html',
		controller: "paginasController",
		controllerAs :"vm"
	})
	.state('capitulos', {
		url: '/capitulos',
		templateUrl : '/view/adiciona-capitulo.html',
		controller: "adicionaCapituloController",
		controllerAs :"vm"
	})
	.state('generos', {
		url: '/generos',
		templateUrl : '/view/genero.html',
		controller: "generosController",
		controllerAs :"vm"
	})
	
	
	
	
	
	$locationProvider.html5Mode(true);
	
}]); 
	
	