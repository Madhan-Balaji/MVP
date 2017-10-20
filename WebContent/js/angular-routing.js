var mainPage = angular.module("mainPage", ['ui.router']);
mainPage.config(function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise("/signin");
	$stateProvider
	.state('signin',{
		url:'/signin',
		controller:function($scope){
			$scope.email = "madi";
			$scope.pwd;
			$scope.login = function(){
				alert("controller works");
			};
		},
		templateUrl:'views/signin.html'
	})
	.state('signup',{
		url:'/signup',
		templateUrl:'views/signup.html'
	})
	.state('dashboard',{
		url:'/dashboard',
		templateUrl:'views/dashboard.html'
	})
});