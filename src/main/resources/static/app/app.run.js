(function () {
		
	//Cria o modulo Run
	angular
		.module("appCliente.run", ['ngRoute']);
		
	//injetar as dependencias 
	AuthService.$inject = ['AuthService', '$state', '$transitions'];

	/* Run para autenticação */
	function AuthService(AuthService, $state, $transitions) {

		$transitions.onSuccess({}, function ($transition) {

			console.log("\n %c From: " + $transition.$from() + " To: " + $transition.$to() + "\n\n", 'background: #222; calor: #bada55');
			printFromTo("onSuccess", $transition.$from(), $transition.$to());

			//Recebe cada estado
			var newToState = $transition.$to();

			if (newToState.needToLogin && !AuthService.user) {
				return $state.go('nav.acessoNegado');
			}

			/* Verifica se o usuario é admin. */
			else {
				//Se o estado é admin		
				if (newToState.data && newToState.data.role) {
					var hasAccess = false;
					for (var i = 0; i < AuthService.user.roles.length; i++) {
						var role = AuthService.user.roles[i];
						//Se a Data do estado e o usuario for admin
						//Libera o acesso					
						if (newToState.data.role == role) {
							hasAccess = true;
							break;
						}
					}
					/* Se nao é admin
					O acesso é negado */
					if (!hasAccess) {
						return $state.go('nav.acessoNegado');
					}
				}
			}
		})

		function printFromTo(evtName, from, to) {
			console.log(evtName + ": (" + from + " - " + to + ")" + new Date().getTime());
		}
	}

	/*Iinicializa a aplicação */
	angular
		.module("appCliente.run")
		.run(AuthService);
})();