(function () {
	'use strict';
	angular
		.module('appCliente.owl', []);

	function owlCarousel() {
		var directive = {
			restrict: 'E',
			transclude: false,
			link: link
		};
		return directive;

		function link(scope) {
			scope.initCarousel = function (element) {

				console.log('initcarousel');
				var defaultOptions = {};

				var customOptions = scope.$eval($(element).attr('data-options'));
				// combine the two options objects
				for (var key in customOptions) {
					defaultOptions[key] = customOptions[key];
				}
				// Inicia o OwlCarousel
				var curOwl = $(element).data('owlCarousel');

				if (!angular.isDefined(curOwl)) {
					$(element).owlCarousel(defaultOptions);
				}
			}
		}
	}

	angular
		.module('appCliente.owl')
		.directive('owlCarousel', owlCarousel);
})();