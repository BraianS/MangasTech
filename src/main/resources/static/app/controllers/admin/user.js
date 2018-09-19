(function () {
	'use strict';
	//Carrega o modulo
	//Adiciona o controller ao modulo
	angular
		.module('appCliente')
		.controller('admUsuarioController', admUsuarioController);

	//Injeta as dependências
	admUsuarioController.$inject = ['AuthService', '$http'];

	function admUsuarioController(AuthService, $http) {

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
			$http({
				method: 'GET',
				url: '/admin/usuario'
			}).then(function (response) {
				vm.usuarios = response.data;
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
		}

		function salvarUsuario() {
			if (vm.formUsuario.$valid) {
				$http({
					method: 'POST',
					url: '/admin/usuario', data: vm.usuario
				}).then(function (response) {
					vm.mensagem = "Salvo com Sucesso.";
					carregarUsuarios();
					cancelarUsuario();
				}, function (response) {
					vm.mensagem = response.data.message;
					console.log(response.data);
					console.log(response.status);
				})
			} else {
				vm.mensagem = "Erro no Formulario";
			}
		}

		function atualizarUsuario() {
			$http({
				method: 'PUT',
				url: '/admin/usuario', data: vm.usuario
			}).then(function (response) {
				vm.mensagem = "Atualizado com sucesso";
				carregarUsuarios();
				cancelarUsuario();
			}, function (response) {
				console.log(response.data);
				console.log(response.status);
			});
		}

		function deletarUsuario(usuario) {
			$http({
				method: 'DELETE',
				url: '/admin/usuario/' + usuario.id
			}).then(function (response) {
				vm.mensagem = "Usuario:" + usuario.nome + " Deletado";
				carregarUsuarios();
			}, function (response) {
				vm.mensagem = response.data.message;
				console.log(response.data);
				console.log(response.status);
			});
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