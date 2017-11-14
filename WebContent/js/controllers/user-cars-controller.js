mainPage.controller('carListCtrl', function($scope, userServices) {
	$scope.cars = {};
	$scope.loadYourCars = function() {
		$scope.cars = userServices.loadUserCars();
	}
	$scope.loadYourCars();
	$scope.removeCar = function(id) {
		if (confirm("Delete the car?")) {
			if (userServices.removeACar(id)) {
				var remv;
				for (i = 0; i < $scope.cars.length; i++) {
					if ($scope.cars[i].id == id) {
						remv = i;
						break;
					}
				}
				$scope.cars.splice(remv, 1);
			}
		}
	}
})