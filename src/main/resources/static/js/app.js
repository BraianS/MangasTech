var appCliente = angular.module("appCliente",['ngRoute','checklist-model']);

appCliente.config(function ($routeProvider) {
	$routeProvider
	.when("/mangas", {templateUrl:'view/manga.html', controller:'mangasController'})
	.otherwise({redirectTo:'/'});
}); 
	
	