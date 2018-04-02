angular
.module('appCliente')
.service('pesquisaService', function() {
	
	var AccountNumber; 
	
	return {
		getValue: function () {
			return AccountNumber;
		},
		
		setValue: function(value) {
			AccountNumber = value;
		}
	}
});