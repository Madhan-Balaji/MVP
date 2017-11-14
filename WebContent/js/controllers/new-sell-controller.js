mainPage.controller('newSellCtrl', function($scope, userServices) {
	userServices.checkSession();
	$scope.data = {};
	$scope.sellNew = function() {
		var files = $('#myFile').prop('files');
		var videos = $('#videoFile').prop('files');
		// alert(files[0].name);
		var fds = new FormData();
		fds.append('price', $scope.data.price)
		fds.append('brand', $scope.data.brand);
		fds.append('type', $scope.data.type);
		fds.append('name', $scope.data.carName);
		fds.append('model', $scope.data.carModel);
		fds.append('year', $scope.data.carYear);
		fds.append('gear', $scope.data.carGear);
		fds.append('seat', $scope.data.seat);
		fds.append('color', $scope.data.color);
		fds.append('owner', $scope.data.owner);
		fds.append('fuelType', $scope.data.fuelType);
		fds.append('milage', $scope.data.milage);
		fds.append('cc', $scope.data.cc);
		fds.append('address', $scope.data.address);
		fds.append('file', files[0]);
		fds.append('video', videos[0])
		userServices.sellNewCar(fds);
	}

})