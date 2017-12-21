angular
.module("appCliente")
.controller("mainController",['$scope','$location','$route','$routeParams', function($scope, $location,$route, $routeParams){
	$scope.$location = $location;
	$scope.$route=$route;
	$scope.$routeParams = $routeParams;
	
	$scope.ListAnswer = function(answerId) {
	    $state.go('answer', {answerId: answerId});
	};
	
}]);