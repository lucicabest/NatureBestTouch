var cartApp = angular.module('cartApp', []);
cartApp.controller('cartCtrl', function($scope, $http) {
	$scope.refreshCart = function(cartId) {
		$http.get('/naturebesttouch/rest/cart/' + $scope.cartId)
			.success(function(data) {
				$scope.cart = data;
			});
	};
	
	$scope.clearCart = function() {
		$http.delete('/naturebesttouch/rest/cart/' + $scope.cartId)
			.success(function(data) {
				$scope.refreshCart($scope.cartId);
			});
	};
	
	$scope.initCartId = function(cartId) {
		$scope.cartId = cartId;
		$scope.refreshCart($scope.cartId);
	};
	
	$scope.addToCart = function() {
//		debugger;
//		console.log('bash');
		var productSPQId = $('#order').serialize().split("=")[1];
		$http.put('/naturebesttouch/rest/cart/add/' + productSPQId)
			.success(function(data) {
				alert("Product Successfully added to the Cart!");
			});
	};
	
	$scope.removeFromCart = function(productId) {
		$http.put('/naturebesttouch/rest/cart/remove/' + productId)
			.success(function(data) {
				$scope.refreshCart($scope.cartId);
			});
	};
});