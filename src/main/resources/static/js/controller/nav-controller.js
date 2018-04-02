angular
.module("appCliente")
.controller("navController",['$scope','$location','$route','$routeParams','AuthService','$http','$rootScope','pesquisaService', function($scope, $location,$route, $routeParams,AuthService,$http,$rootScope,pesquisaService){
	$scope.$location = $location;
	$scope.$route=$route;
	$scope.$routeParams = $routeParams;
	
	$scope.$on('LoginSuccessful', function() {
		$scope.user = AuthService.user;
	});
	
	$scope.$on('LoginSuccessful', function() {
		$scope.user = null;
	});
	
	
	var vm = this;
	
	vm.d = [];
	vm.nome = [];
	
	vm.service = function(nome) {
		pesquisaService.setValue(nome);
		vm.nome = [];
	}
	
		vm.pesquisarNome = function(nome){
		$http({
			method: 'GET',
			url: 'manga/nome/'+nome
		}).then(function(res) {
			console.log(res);
			console.log(res.data);
			vm.d = res.data;
		}, function(res) {
			console.log(res);
			console.log(res.data);
		})
	}
	
	
	var vm = this;
	$scope.myInterval = 5000;
	  var slides = $scope.slides = [];
	  $scope.addSlide = function() {
	    var newWidth = 600 + slides.length + 1;
	    slides.push({
	      image: 'http://placekitten.com/' + newWidth + '/300',
	      text: ['More','Extra','Lots of','Surplus'][slides.length % 4] + ' ' +
	        ['Cats', 'Kittys', 'Felines', 'Cutes'][slides.length % 4]
	    });
	  };
	  for (var i=0; i<4; i++) {
	    $scope.addSlide();
	  }
	  
	  vm.isCollapsed = true;
	    vm.status = {
	      isopen: false
	    }
	    
	    $scope.items = [
	        'The first choice!',
	        'And another choice for you.',
	        'but wait! A third!'
	      ];

	      $scope.status = {
	        isopen: false
	      };

	      $scope.toggled = function(open) {
	        $log.log('Dropdown is now: ', open);
	      };

	      $scope.toggleDropdown = function($event) {
	        $event.preventDefault();
	        $event.stopPropagation();
	        $scope.status.isopen = !$scope.status.isopen;
	      };

	      $scope.appendToEl = angular.element(document.querySelector('#dropdown-long-content'));
	vm.title = "hola";
}]);