angular
.module("appCliente")
.controller("mainController",['$scope','$location','$route','$routeParams', function($scope, $location,$route, $routeParams){
	$scope.$location = $location;
	$scope.$route=$route;
	$scope.$routeParams = $routeParams;
	
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
	
}]);