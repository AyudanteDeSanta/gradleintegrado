(function(angular){
	var AppController = function ($scope, Item){
		
		Item.query(function(response){
			console.log("paso controlador");
			$scope.items = response? response : [];
		});
		
		//$scope.items = Item.get(function(response){
			//console.log("paso controlador");
			//console.log(response);
			//$scope.items = response? response : [];
			//console.log($scope.items);
		//});
	};
	
	AppController.$inject = ['$scope','Item'];
	angular.module("myApp.controllers").controller("AppController",AppController);
}(angular));