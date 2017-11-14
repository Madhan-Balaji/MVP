mainPage.controller('viewNews',function($scope, userServices){
	$scope.news = {};
	$scope.loadNews = function(){
		$scope.news = userServices.getNewsData(localStorage.getItem("show-news"));
	}
	$scope.loadNews();
})