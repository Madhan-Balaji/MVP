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
		templateUrl:'views/dashboard.html',
		controller:'dashboardCtrl'
	})
	.state('dashboard.user',{
		url:'/user-dashboard',
		templateUrl:'views/user.html',
		controller:'userPanelCtrl'
	})
	.state('dashboard.insurance',{
		url:'/insurance-dashboard',
		templateUrl:'views/insurance.html'
	})
	.state('dashboard.admin',{
		url:'/admin-dashboard',
		templateUrl:'views/admin.html'
	})
	.state('dashboard.sell',{
		url:'/sell-a-car',
		templateUrl:'views/sell.html',
		controller:'sellCtrl'
	})
	.state('dashboard.search',{
		url:'/search',
		templateUrl:'views/search.html',
		controller:'searchCtrl'
	})
	.state('dashboard.car',{
		url:'/car',
		templateUrl:'views/carDetails.html',
		controller:'carDetailCtrl'
	})
	.state('dashboard.compare',{
		url:'/compare',
		templateUrl:'views/compare.html',
		controller:'compareCtrl'
	})
	.state('dashboard.sellNew',{
		url:'/post-a-car',
		templateUrl:'views/newCar.html',
		controller:'newSellCtrl'
	})
	.state('dashboard.newInsurance',{
	url:'/post-an-insurance',
	templateUrl:'views/newInsurance.html',
	controller:'newInsuCtrl'
	})
	.state('dashboard.newNews',{
		url: '/post-a-news',
		templateUrl: 'views/newNews.html',
		controller: 'newNewsCtrl'
	})
	.state('dashboard.news',{
		url: '/news',
		templateUrl: 'views/news.html',
		controller: 'viewNews'
	})
	.state('dashboard.showInsurance',{
		url:'/insurance',
		templateUrl:'views/showInsurance.html',
		controller: 'insuranceCtrl'
	})
	.state('dashboard.yours',{
		url:'/yourCars',
		templateUrl:'views/newCarList.html',
		controller: 'carListCtrl'
	})
	.state('dashboard.newLoan', {
		url:'/post-a-loan',
		templateUrl:'views/newLoan.html',
		controller: 'newLoanCtrl'
	})
	.state('dashboard.loans',{
		url:'/loans',
		templateUrl: 'views/viewLoans.html',
		controller: 'viewLoans'
	})
	.state('dashboard.changePassword', {
		url:'/change-password',
		templateUrl: 'views/changePassword.html',
		controller: 'chngPwd'
	})
});