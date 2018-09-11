angular
    .module('appCliente.owl')
    .directive('owlCarouselItem', owlCarouselItem);

function owlCarouselItem() {
    var directive = {
        link: link,
        restrict: 'A',
        transclude: false
    };
    return directive;

    function link(scope, element) {
        /* Ultimo item do ng-repeat */
        if (scope.$last) {
            console.log('1st element');
            scope.initCarousel(element.parent());
        }
    }
}