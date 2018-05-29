var cartApp = angular.module('cartApp', []);

cartApp.controller('cartCtrl',  function ($scope, $http) {
    $scope.refreshCart = function(cartId) {
        $http.get('/webstore/rest/cart/'+cartId)
            .success(function(data) {
                console.log(data);
                $scope.cart = data;
                //alert("odswiezono koszyk ");
            });
    };

    $scope.clearCart = function() {
        $http.delete('/webstore/rest/cart/'+$scope.cartId)
            .success($scope.refreshCart($scope.cartId));
    };

    $scope.initCartId = function(cartId) {
        //alert("inicjalizowanie koszyka");
        $scope.cartId=cartId;
        $scope.refreshCart($scope.cartId);
    };

    $scope.addToCart = function(productId) {
        $http.put('/webstore/rest/cart/add/'+productId)
            .success(function(data) {
                alert("Produkt pomyślnie dodany do koszyka!");
            });
    };
    $scope.removeFromCart = function(productId) {
        $http.put('/webstore/rest/cart/remove/'+productId)
            .success(function(data) {
                $scope.refreshCart($scope.cartId);
            });
    };
});
