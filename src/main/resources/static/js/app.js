angular
.module('appCliente',['ui.router','ngRoute','ngFileUpload','ui.bootstrap'])
.run(function(AuthService,$rootScope, $state,$transitions,$rootScope)  {
	
		$transitions.onStart({}, function($transition,event) {
			console.log("\n %c From: " + $transition.$from() + " To: " + $transition.$to() + "\n\n", 'background: #222; calor: #bada55');
			printFromTo("onStart", $transition.$from(), $transition.$to());
			var newToState = $transition.$to();
									
			if (newToState.needToLogin && !AuthService.user) {
									
					$state.go('acesso-negado');				
			}			
			
			else {
				if (newToState.data && newToState.data.role) {
					var hasAccess = false;
					for (var i = 0; i < AuthService.user.roles.length; i++) {
						var role = AuthService.user.roles[i];
						if (newToState.data.role == role) {
							hasAccess = true;
							break;
						}
					} 
					if (!hasAccess) {
						 
						$state.go('acesso-negado');
						return $q.inject();
					}
				}
			}
		
		})
		
		function printFromTo(evtName, from , to) {
			console.log(evtName + ": (" + from + " - " + to + ")" + new Date().getTime());
		}
	
});
angular
.module("appCliente")
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


	