mainPage.controller('signinCtrl',function($scope,$state,$rootScope,loginUser){
			$scope.email;
			$scope.pwd;
			$scope.login = function(){
				loginUser.checkLogin($scope.email, $scope.pwd);
			}
		})
		.controller('signupCtrl',function($scope){
			var alt = function(){
				alert("hai its signupCtrl");
			}
			alt();
		})