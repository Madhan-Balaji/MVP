mainPage.controller('insuranceCtrl', function($scope, userServices) {
	$scope.insurance = {};
	$scope.loadData = function() {
		$scope.insurance = userServices.loadInsuData();
	}
	$scope.loadData();
})