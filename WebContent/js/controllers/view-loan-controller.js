mainPage.controller('viewLoans', function($state, $scope, userServices) {
	$scope.firstLoad = function() {
		$scope.data = userServices.getLoans();
	}
	$scope.firstLoad();
})