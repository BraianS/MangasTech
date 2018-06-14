angular
	.module("appCliente")
	.controller("admMangaController", function ($http, $scope, $filter, $stateParams, $state, Upload) {
		var vm = this; /* view Model */

		vm.Model = {};

		vm.Model.Manga = {}; // Guarda os valores do formulario para depois salvar.
		vm.Model.autor = []; // Guarda os Autores
		vm.Mangas = []; //Guarda os Mangas 
		
		vm.pagina = 0;
		vm.size = []; //tamanho de paginas = 20
		vm.totalPaginas = []; //Total de paginas do backend
		vm.totalElementos = []; //Total de elementos
		vm.Model.generos = []; //Guarda os Generos   
		vm.picFile = {}; //vai receber a imagem


		/* Opcao para carregar os anos */
		vm.years = [{ value: '2021', disabled: true }];
		for (var i = 2020; i >= 1990; i--) {
			vm.years.push({ value: i });
		}

		/* Status de cada Manga */
		vm.status = [
			"COMPLETO",
			"LANCANDO",
			"PAUSADO"
		];

		/* Selecionar a imagem */
		vm.image = function (imagem) {
			/* Guardar a imagem */
			vm.picFile = imagem;
		}

		/* Salvar Mangas */
		vm.salvarMangas = function () {
			if (vm.formNovoManga.$valid) {
				$http({
					method: 'POST', url: '/admin/manga',
					headers: { 'Content-Type': undefined },
					transformRequest: function (data) {
						var formData = new FormData();

						formData.append('mangas', new Blob([angular.toJson(data.mangas)], {
							type: "application/json"
						}));
						formData.append("file", data.file);
						return formData;
					},
					data: { mangas: vm.Model.Manga, file: vm.picFile },
				}).then(function (response) {
					vm.Model.Manga = {};
					vm.picFile = "";
					vm.carregarMangas();
					vm.mensagem = "Salvo com Sucesso";
					vm.Model.carregarGeneros();
					vm.formNovoManga.$setPristine(true);
					vm.Load();
				}, function (response) {

					console.log(response.data);
					console.log(response.status);
					vm.mensagem = response.data.message;
				});

			}
			else {
				alert('não foi salvo');
			}
		};

		vm.Load = function () {
			if (vm.Model.generos && vm.Model.Generos) {
				angular.forEach(vm.Model.generos, function (_genero, i) {
					_genero.Selecionado = $filter('filter')(vm.Model.generos, { id: _genero.id }).length > 0;
					console.log(i, vm.Model.Manga.genero, $filter('filter')(vm.Model.Manga.genero, { id: _genero.id })[0]);
				});
			};
		};

		//função após mudar os checkboxes
		vm.Selecionar = function (argument) {
			vm.Model.Manga.genero = $filter('filter')(vm.Model.generos, { Selecionado: true });
			console.log($filter('filter')(vm.Model.generos, { Selecionado: true }));
		}

		/* Carregar Generos */
		vm.Model.carregarGeneros = function () {
			$http({
				method: 'GET', url: '/user/genero'
			})
				.then(function (response) {
					vm.Model.generos = response.data.content;
					console.log("genero buscado com sucesso");
					console.log(response.data);
					console.log(response.status);
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
				});
		};

		/* Carregar Autores */
		vm.Model.carregarautor = function () {
			$http({
				method: 'GET', url: '/user/autor'
			})
				.then(function (response) {
					vm.Model.autor = response.data.content;
					console.log(response);
					console.log(response.data);
				}, function (response) {
					console.log(response.data);
					console.log(response.status);
				});
		};

		/* Excluir Manga */
		vm.excluirMangas = function (Manga) {
			$http({
				method: 'DELETE', url: '/admin/manga/' + Manga.id
			})
				.then(function (response) {
					pos = vm.Mangas.indexOf(vm.Model.Manga);
					vm.Mangas.splice(pos, 1);
					vm.carregarMangas();
					console.log(response.data);

				}, function (response) {
					console.log(response.data);
					console.log(response.status)
				});
		};

		/* Alerta cada vez que muda de pagina */
		vm.pageChange = function () {
			alert("pagina atual e : " + vm.pagina)
		}

		/* Carregar os Mangas */
		vm.carregarMangas = function () {
			$http({
				method: 'GET',
				 url: '/user/manga?page=' + vm.pagina //Carrega por pagina
			})
				.then(function (response) {
					vm.Mangas = response.data.content;
					vm.totalDePaginas = response.data.totalPages;
					vm.totalElementos = response.data.totalElements;
					vm.size = response.data.size;

					console.log(response);
					console.log(response.data);
				}, function (response) {
					console.log(response);
					console.log(response.data);
				});
		};

		vm.carregarMangas();
		vm.Model.carregarautor();
		vm.Model.carregarGeneros();

		/* Alterar os Mangas */
		vm.alterarMangas = function (manga) {
			vm.Model.Manga = manga;
		}

		/* Cancelar o formulario */
		vm.Cancelar = function () {
			vm.Model.Manga = {};
		}

	});
