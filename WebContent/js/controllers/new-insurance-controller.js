mainPage.controller('newInsuCtrl', function($scope, userServices) {
	$scope.insurance = {};
	$scope.sellInsu = function() {
		userServices.submitNewInsurance($scope.insurance);
	}
	$scope.insuranceControl = userServices.getAllInsurance();
	$scope.rmvInsu = function(id) {
		if (confirm("Delete the insurance?")) {
			if (userServices.removeInsurance(id)) {
				$scope.insuranceControl = userServices.getAllInsurance();
			} else {
				alert("Something went wrong!");
			}
		}
	}
})