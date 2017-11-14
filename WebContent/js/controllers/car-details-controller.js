mainPage.controller('carDetailCtrl', function($state, $scope, userServices) {
	userServices.checkSession();
	$scope.data = userServices.getCarDetails();
	$scope.seeLoans = function(brand) {
		localStorage.setItem("brand", brand);
		$state.go("dashboard.loans")
	}
	$scope.userReview = {};
	$scope.review = userServices.getReviews();
	$scope.uploadReview = function() {
		$scope.review = userServices.setReview(localStorage.getItem("showCar"),
				$scope.userReview.review, $scope.userReview.rating);
	}

})