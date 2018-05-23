angular
.module('appCliente')
.controller('admUsuarioController', function($http, $scope,AuthService) {
	var vm = this;
	$scope.user = AuthService.user;
});