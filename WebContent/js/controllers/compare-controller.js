mainPage.controller('compareCtrl', function($state, $scope, $rootScope,
		userServices) {
	userServices.checkSession();
	$scope.compCars = [];
	for (i = 0; i < $rootScope.compare.length; i++) {
		$scope.data = userServices.getCarDetailCmp($rootScope.compare[i]);
		$scope.compCars.push($scope.data);
	}
	$scope.loadPage = function() {
		$scope.scripting = "";
		for (i = 0; i < $scope.compCars.length; i++) {
			$scope.scripting += "<div class='col-sm-"
					+ (12 / $scope.compCars.length)
					+ "'><img style='max-height: 500px;width: 100%;' src='"
					+ $scope.compCars[i].imageUrl + "'></div>";
		}
		$('#cmp-img').append($scope.scripting);
		$scope.scripting = "";
		for (i = 0; i < $scope.compCars.length; i++) {
			brand = $rootScope.initcap($scope.compCars[i].brand);
			name = $rootScope.initcap($scope.compCars[i].name);
			model = $rootScope.initcap($scope.compCars[i].model);
			year = $rootScope.initcap($scope.compCars[i].year);
			gear = $rootScope.initcap($scope.compCars[i].gear);
			seat = $rootScope.initcap($scope.compCars[i].seat);
			type = $rootScope.initcap($scope.compCars[i].type);
			color = $rootScope.initcap($scope.compCars[i].color);
			owner = $rootScope.initcap($scope.compCars[i].owner);
			fuel = $rootScope.initcap($scope.compCars[i].fuel);
			milage = $rootScope.initcap($scope.compCars[i].milage);
			cc = $rootScope.initcap($scope.compCars[i].cc);
			price = $rootScope.initcap($scope.compCars[i].price);
			$('#heading').append(
					"<th style='text-align:center;'>"
							+ $rootScope.initcap(brand) + " "
							+ $rootScope.initcap(name) + "</th>")
			$('#brand').append(
					"<td style='text-align:center;'>" + brand + "</td>");
			$('#name').append(
					"<td style='text-align:center;'>" + name + "</td>");
			$('#model').append(
					"<td style='text-align:center;'>" + model + "</td>");
			$('#year').append(
					"<td style='text-align:center;'>" + year + "</td>");
			$('#gear').append(
					"<td style='text-align:center;'>" + gear + "</td>");
			$('#seat').append(
					"<td style='text-align:center;'>" + seat + "</td>");
			$('#type').append(
					"<td style='text-align:center;'>" + type + "</td>");
			$('#color').append(
					"<td style='text-align:center;'>" + color + "</td>");
			$('#owner').append(
					"<td style='text-align:center;'>" + owner + "</td>");
			$('#fuel').append(
					"<td style='text-align:center;'>" + fuel + "</td>");
			$('#milage').append(
					"<td style='text-align:center;'>" + milage + "</td>");
			$('#cc').append("<td style='text-align:center;'>" + cc + "</td>");
			$('#price').append(
					"<td style='text-align:center;'>" + price + "</td>");
		}

	}
	$scope.loadPage();
})