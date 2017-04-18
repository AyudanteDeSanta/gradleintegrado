(function(angular){
	var itemFactory = function($resource){
		console.log('servicio');
		//return null;
		return $resource('/greetingrest');
	};
	
	itemFactory.$inject = ['$resource'];
	angular.module("myApp.services").factory("Item", itemFactory);
	
}(angular));