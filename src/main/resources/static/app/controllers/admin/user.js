(function () {
	'use strict';
	//Carrega o modulo
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admUsuarioController', admUsuarioController);

	//Injeta as dependências
	admUsuarioController.$inject = ['AuthService', 'userService'];

	function admUsuarioController(AuthService, userService) {

		var vm = this;
		vm.user = AuthService.user;
		vm.deletarUsuario = deletarUsuario;
		vm.cancelarUsuario = cancelarUsuario;
		vm.alterarUsuario = alterarUsuario;
		vm.salvarUsuario = salvarUsuario;
		vm.atualizarUsuario = atualizarUsuario;
		vm.closeMsg = closeMsg;
		vm.alterado = false;
		vm.usuarios = [];
		vm.usuario = {};
		vm.submit = submit;

		carregarUsuarios();

		function closeMsg() {
			vm.mensagem = "";
		}

		function carregarUsuarios() {
			return userService.carregarUsuarios()
				.then(function (data) {
					vm.usuarios = data;
				})
		}

		function salvarUsuario() {
			if (vm.formUsuario.$valid) {
				return userService.salvarUsuario(vm.usuario)
					.then(function (data) {
						vm.mensagem = data;
						carregarUsuarios();
						cancelarUsuario();
					})
			} else {
				vm.mensagem = "Erro no Formulario";
			}
		}

		function atualizarUsuario() {
			return userService.atualizarUsuario(vm.usuario)
				.then(function (data) {
					vm.mensagem = data;
					carregarUsuarios();
					cancelarUsuario();
				})
		}

		function deletarUsuario(usuario) {
			return userService.deletarUsuario(usuario)
				.then(function (data) {
					vm.mensagem = data;
					carregarUsuarios();
				})
		}

		function cancelarUsuario() {
			vm.usuario = {};
			vm.formUsuario.$setPristine(true);
		}

		function alterarUsuario(usuario) {
			vm.alterado = true;
			vm.usuario = usuario;
		}

		function submit() {
			if (vm.alterado) {
				vm.alterado = false;
				atualizarUsuario();
			}
			else {
				salvarUsuario();
			}
		}
	}
})();