angular
.module("appCliente")
.controller("capituloController",['$scope', '$routeParams','$rootScope','$http','$location','$stateParams',function ($scope, $routeParams,$rootScope, $http, $location,$stateParams) {
	var vm = this;
	vm.Model = {};
	
	$scope.capituloId = $stateParams.capituloId;
	
	vm.foto = {};
		
	$http.get("capitulo/"+$stateParams.capituloId).then(function (response) {
		vm.foto = response.data;
	console.log(response.data);
	console.log(response.status);
	}, function (response) {
		console.log(response);
	});
	
	
}]);

angular
.module("appCliente")
.directive("helloWorld", function(){
	return {
		replace: true,
		restrict: 'AECM',
		template: '<h3>Hello World!! </h3>'
	}
});