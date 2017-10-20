var mainPage = angular.module("mainPage", ['ui.router']);
mainPage.config(function($stateProvider, $urlRouterProvider){
	$urlRouterProvider.otherwise("/signin");
	$stateProvider
	.state('signin',{
		url:'/signin',
		controller:'signinCtrl',
		templateUrl:'views/signin.html'
	})
	.state('signup',{
		url:'/signup',
		templateUrl:'views/signup.html',
		controller:'signupCtrl'
	})
	.state('dashboard',{
		url:'/dashboard',
		templateUrl:'views/dashboard.html'
	})
});