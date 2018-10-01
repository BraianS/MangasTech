(function () {
	'use strict';
	/* Adiciona o service ao modulo */
	angular
		.module('appCliente')
		.service('AuthService', function () {
			return {
				user: null
			}
		})
})();