var mainPage = angular.module("mainPage", ['ui.router']);
mainPage.config(function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise("/signin");
	$stateProvider
	.state('signin',{
		url:'/signin',
		templateUrl:'views/signin.html'
	})
	.state('signup',{
		url:'/signup',
		templateUrl:'views/signup.html'
	})
});