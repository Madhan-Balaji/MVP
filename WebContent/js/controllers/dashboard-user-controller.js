mainPage.controller('userPanelCtrl', function($state, $scope, userServices) {
	userServices.checkSession();
	$scope.sellPage = function() {
		$state.go('dashboard.sell');
	}
	$scope.searchPage = function() {
		$state.go('dashboard.search');
	}
	$scope.loadNews = function() {
		$scope.dailyNews = userServices.loadDailyNews();
	}
	$scope.loadInsurance = function() {
		$scope.insurances = userServices.loadHomeInsurance();
	}
	$scope.showNews = function(id) {
		localStorage.setItem("show-news", id);
		$state.go('dashboard.news');
	}
	$scope.showInsurance = function(id) {
		localStorage.setItem("show-insu", id);
		$state.go('dashboard.showInsurance');
	}
	$scope.loadNews();
	$scope.loadInsurance();
})
