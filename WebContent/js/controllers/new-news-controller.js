mainPage.controller('newNewsCtrl', function($scope, userServices) {
	$scope.news = {};
	$scope.publishNews = function() {
		var files = $('#myFile').prop('files');
		$scope.news.file = files[0];
		var data = new FormData();
		data.append("head", $scope.news.heading);
		data.append("content", $scope.news.content);
		data.append("file", $scope.news.file);
		userServices.postNews(data);
	}
	$scope.loadNews = function() {
		$scope.newsControl = userServices.getAllNews();
	}
	$scope.rmvNews = function(id) {
		if (confirm("Delete the News/Offer?")) {
			if (userServices.removeNews(id)) {
				var remv;
				for (i = 0; i < $scope.newsControl.length; i++) {
					if ($scope.newsControl[i].id == id) {
						remv = i;
						break;
					}
				}
				$scope.newsControl.splice(remv, 1);
			}
		}
		$scope.loadNews();
	}
	$scope.loadNews();

})