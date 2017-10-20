mainPage.controller('signinCtrl',function($scope,$state,$rootScope,userServices){
			$scope.email;
			$scope.pwd;
			$scope.login = function(){
				userServices.checkLogin($scope.email, $scope.pwd);
			}
		})
		.controller('signupCtrl',function($scope,userServices){
			$scope.email;
			$scope.name;
			$scope.pwd;
			$scope.phone;
			$scope.region;
			$scope.signup = function(){
				userServices.userSignUp($scope.email,$scope.name,$scope.pwd,$scope.phone,$scope.region);
			}
		})
		.controller('dashboardCtrl',function($scope,userServices){
			userServices.checkSession();
		})