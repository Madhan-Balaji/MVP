mainPage.controller('dashboardCtrl', function($scope, $state, $rootScope,
		userServices) {
	userServices.checkSession();
	$scope.signOut = function() {
		userServices.signingOut();
	}
	$rootScope.compare = [];
	$rootScope.compareName = [];
	$rootScope.setCompare = function(id, name) {
		if ($rootScope.compare.length < 3) {
			exist = 0;
			for (i = 0; i < $rootScope.compare.length; i++) {
				if ($rootScope.compare[i] == id) {
					exist++;
					break;
				}
			}
			if (exist == 0) {
				$rootScope.compare.push(id);
				$rootScope.compareName.push(name);
			} else {
				alert("Data already available for compare");
			}
		} else {
			alert("Maximum comparison limit reached");
		}
	}
	$rootScope.removeCompare = function(name) {
		for (i = 0; i < $rootScope.compareName.length; i++) {
			if ($rootScope.compareName[i] == name) {
				$rootScope.compare.splice(i, 1);
				$rootScope.compareName.splice(i, 1);
			}
		}

	}
	$scope.goCompare = function() {
		$state.go('dashboard.compare');
	}
})