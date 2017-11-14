mainPage.controller('signinCtrl', function($scope, $state, $rootScope,
		userServices) {
	$scope.email;
	$scope.pwd;
	$scope.login = function() {
		userServices.checkLogin($scope.email, $scope.pwd);
	}

})