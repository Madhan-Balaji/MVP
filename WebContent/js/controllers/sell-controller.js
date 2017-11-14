mainPage.controller('sellCtrl', function($scope, userServices) {
	userServices.checkSession();
	$scope.data = {};
	$scope.sell = function() {
		var files = $('#myFile').prop('files');
		// alert(files[0].name);
		var fd = new FormData();
		fd.append('price', $scope.data.price)
		fd.append('brand', $scope.data.brand);
		fd.append('type', $scope.data.type);
		fd.append('name', $scope.data.carName);
		fd.append('model', $scope.data.carModel);
		fd.append('year', $scope.data.carYear);
		fd.append('gear', $scope.data.carGear);
		fd.append('seat', $scope.data.seat);
		fd.append('color', $scope.data.color);
		fd.append('owner', $scope.data.owner);
		fd.append('fuelType', $scope.data.fuelType);
		fd.append('milage', $scope.data.milage);
		fd.append('cc', $scope.data.cc);
		fd.append('address', $scope.data.address);
		fd.append('file', files[0]);
		userServices.sellCar(fd);
	}
})