angular
.module('appCliente')
.service('GeneroService', ['$http', function($http) {
	
	this.getGenero = function(Genero) {
		return $http({method : 'GET', ur: 'genero/'+Genero.id});
	}
}]);