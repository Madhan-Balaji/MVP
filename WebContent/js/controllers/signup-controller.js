mainPage.controller('signupCtrl', function($scope, userServices) {
	$scope.email;
	$scope.name;
	$scope.pwd;
	$scope.phone;
	$scope.region;
	$scope.signup = function() {
		userServices.userSignUp($scope.email, $scope.name, $scope.pwd,
				$scope.phone, $scope.region);
	}
})