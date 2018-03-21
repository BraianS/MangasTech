angular
.module('appCliente',['ui.router','ngRoute','ngFileUpload','ui.bootstrap'])
.run(function(AuthService,$rootScope, $state,$transitions,$rootScope)  {
	// For implementing the authentication with ui-router we need to listen the
	// state change. For every state change the ui-router module will broadcast
	// the '$stateChangeStart'.
	/*$rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams) {
		// checking the user is logged in or not
		if (!AuthService.user) {
			// To avoiding the infinite looping of state change we have to add a
			// if condition.
			if (toState.name != 'login' && toState.name
					!= 'registrar') {
				event.preventDefault();
				$state.go('login');
			}
			
		} else {
			// checking the user is authorized to view the states
			if (toState.data && toState.data.role) {
				var hasAccess = false;
				for (var i = 0; i < AuthService.user.roles.length; i++) {
					var role = AuthService.user.roles[i];
					if (toState.data.role == role) {
						hasAccess = true;
						break;
					}
				}
				if (!hasAccess) {
					event.preventDefault();
					$state.go('acesso-negado');
				}

			}
		}
		
		
	});*/
	
	/*$transitions.onStart({to : 'nav.**' }, function(trans) {
		var auth = trans.injector().get('AuthService');
		
		if(!auth.isAuthenticated()) {
			
			return trans.router.stateService.target('login');
		}
	});*/
	
	
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
/*angular
.module("appCliente")
	.filter('startFrom', function(){
		return function(data, start) {
			return data.slice(start);
		}
	})*/

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
	