mainPage.controller('chngPwd', function($state, $scope, userServices) {
	$scope.pass = {};
	$scope.changePwd = function() {
		if (userServices.changePassword($scope.pass.newP, $scope.pass.oldP)) {
			alert("password changed");
			userServices.signingOut();
			$state.go('signin');
		} else {
			alert("Old password wrong");
		}
	}
})