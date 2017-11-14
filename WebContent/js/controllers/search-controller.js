mainPage
		.controller(
				'searchCtrl',
				function($state, $scope, $rootScope, userServices) {
					$scope.filterFuel = [];
					$scope.filterUsage = [];
					$scope.checkUsageAlreadyExist = function(usage) {
						result = true;
						for (i = 0; i < $scope.filterUsage.length; i++) {
							if ($scope.filterUsage[i] == usage) {
								result = false;
								break;
							}
						}
						return result;
					}
					$scope.checkFuelAlreadyExist = function(fuel) {
						result = true;
						for (i = 0; i < $scope.filterFuel.length; i++) {
							if ($scope.filterFuel[i] == fuel) {
								result = false;
								break;
							}
						}
						return result;
					}
					$scope.getIndexUsageFilter = function(usage) {
						var index;
						for (i = 0; i < $scope.filterUsage.length; i++) {
							if ($scope.filterUsage[i] == usage) {
								index = i;
								break;
							}
						}
						return index;
					}

					$scope.getIndexFuelFilter = function(fuel) {
						var index;
						for (i = 0; i < $scope.filterFuel.length; i++) {
							if ($scope.filterFuel[i] == fuel) {
								index = i;
								break;
							}
						}
						return index;
					}
					$scope.updateFilter = function() {
						// Adding and removing usage from filter
						if ($scope.used == true) {
							if ($scope.checkUsageAlreadyExist("used")) {
								$scope.filterUsage.push("used")
							}
						} else {
							if (!$scope.checkUsageAlreadyExist("used")) {
								$scope.filterUsage.splice($scope
										.getIndexUsageFilter("used"), 1);
							}
						}
						if ($scope.newv == true) {
							if ($scope.checkUsageAlreadyExist("new")) {
								$scope.filterUsage.push("new")
							}
						} else {
							if (!$scope.checkUsageAlreadyExist("new")) {
								$scope.filterUsage.splice($scope
										.getIndexUsageFilter("new"), 1);
							}
						}

						// Adding and Removing Fuel from filter
						if ($scope.petrol == true) {
							if ($scope.checkFuelAlreadyExist("pertrol")) {
								$scope.filterFuel.push("pertrol")
							}
						} else {
							if (!$scope.checkFuelAlreadyExist("pertrol")) {
								$scope.filterFuel.splice($scope
										.getIndexFuelFilter("pertrol"), 1);
							}
						}
						if ($scope.diesel == true) {
							if ($scope.checkFuelAlreadyExist("diesel")) {
								$scope.filterFuel.push("diesel")
							}
						} else {
							if (!$scope.checkFuelAlreadyExist("diesel")) {
								$scope.filterFuel.splice($scope
										.getIndexFuelFilter("diesel"), 1);
							}
						}
						if ($scope.cng == true) {
							if ($scope.checkFuelAlreadyExist("cng")) {
								$scope.filterFuel.push("cng")
							}
						} else {
							if (!$scope.checkFuelAlreadyExist("cng")) {
								$scope.filterFuel.splice($scope
										.getIndexFuelFilter("cng"), 1);
							}
						}
						if ($scope.electric == true) {
							if ($scope.checkFuelAlreadyExist("electric")) {
								$scope.filterFuel.push("electric")
							}
						} else {
							if (!$scope.checkFuelAlreadyExist("electric")) {
								$scope.filterFuel.splice($scope
										.getIndexFuelFilter("electric"), 1);
							}
						}
						$scope.applyFilter();
					}
					$scope.applyFilter = function() {
						var usageFilter = [];
						var finalFilter = [];
						if ($scope.filterUsage.length > 0) {
							for (i = 0; i < $scope.holder.length; i++) {
								for (j = 0; j < $scope.filterUsage.length; j++) {
									if ($scope.holder[i].usage == $scope.filterUsage[j]) {
										usageFilter.push($scope.holder[i]);
									}
								}
							}
						} else {
							usageFilter = $scope.holder;
						}
						if ($scope.filterFuel.length > 0) {
							for (i = 0; i < usageFilter.length; i++) {
								for (j = 0; j < $scope.filterFuel.length; j++) {
									if (usageFilter[i].fuel == $scope.filterFuel[j]) {
										finalFilter.push(usageFilter[i])
									}
								}
							}
						} else {
							finalFilter = usageFilter;
						}
						$scope.cars = finalFilter;

					}
					$scope.searchTerm;
					$scope.searchTheTerm = function() {
						$scope.holder = userServices
								.searchCarByTerm($scope.searchTerm);
						$scope.applyFilter();
					}
					userServices.checkSession();
					$scope.carNames = [];
					$scope.carBrand = [];

					$scope.temp = 0;
					$rootScope.initcap = function(string) {
						return string.charAt(0).toUpperCase() + string.slice(1);
					}
					$scope.selectCar = function(carId) {
						userServices.setForCarDetail(carId);
					}

					$scope.printCars = function(cars, noOfCars) {

						var i = 0;
						var t = 1;
						$scope.scripting = "";
						while (i < noOfCars) {
							$scope.scripting += "<div class='row'>";
							while ((t % 3 != 0) && (i < noOfCars)) {
								$scope.scripting += '<div class="col-sm-4"><div class="thumbnail"><img src="http://localhost:8080/carshop/Jserv/control/media/'
										+ cars[i].id
										+ '" alt="Nature" style="width:100%"><div class="caption"><button onclick="selectCar(\''
										+ cars[i].id
										+ '\')">'
										+ $scope.initcap(cars[i].brand)
										+ ' '
										+ $scope.initcap(cars[i].name)
										+ '</button><p>'
										+ cars[i].year
										+ '</p><h5 style="text-align: right;"><span style="background-color: #afffd7; cursor:pointer;">Compare</span> <span style="background: #eaeaea;">&#8377 '
										+ cars[i].price
										+ '</span></h5></div></a></div></div>';
								i++;
								t++;
							}
							if (i < noOfCars) {
								$scope.scripting += '<div class="col-sm-4"><div class="thumbnail"><img src="http://localhost:8080/carshop/Jserv/control/media/'
										+ cars[i].id
										+ '" alt="Nature" style="width:100%"><div class="caption"><button onclick="selectCar(\''
										+ cars[i].id
										+ '\')">'
										+ $scope.initcap(cars[i].brand)
										+ ' '
										+ $scope.initcap(cars[i].name)
										+ '</button></span><p>'
										+ cars[i].year
										+ '</p><h5 style="text-align: right;"><span style="background-color: #afffd7; cursor:pointer;">Compare</span> <span style="background: #eaeaea;">&#8377 '
										+ cars[i].price
										+ '</span></h5></div></div></div>';
								i++;
							}
							$scope.scripting += "</div>";
							t++;
						}
						$('#search-area').append($scope.scripting);
					}

					$scope.firstLoad = function() {
						$scope.data = userServices.fetchAllCars();
						$scope.holder = $scope.data.cars;
						$scope.cars = $scope.holder;
					}
					$scope.firstLoad();

				})