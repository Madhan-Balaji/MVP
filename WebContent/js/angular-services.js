mainPage.service('userServices',function($rootScope,$state){
	this.checkLogin = function(email, pwd){
		$.post("http://localhost:8080/carshop/Jserv/control/userLogin",
		{
		  mail: email,
		  pwd: pwd
		},
		function(data,status){
			if(status == "success"){
				if(data.status == "success"){
					$rootScope.sessionHolder = data.user.id;
					$rootScope.user = data.user;
					if($rootScope.user.role == "user"){
						$state.go('dashboard.user');
					}
					else if($rootScope.user.role == "insurance"){
						$state.go('dashboard.insurance');
					}
					else if($rootScope.user.role == "admin"){
						$state.go('dashboard.admin');
					}
				}
				else{
					alert("provided credentials are wrong!")
				}
			}else{
				alert("something went wrong - server error");
			}
		});
	}
	this.checkSession = function(){
		$.post("http://localhost:8080/carshop/Jserv/control/checkSession",
		{
		  session: $rootScope.sessionHolder
		},
		function(data,status){
				if(data != "success")
				{
					$state.go('signin');
				}
			}
		);
	}
	this.userSignUp = function(email,name,pwd,phone,region){
		$.post("http://localhost:8080/carshop/Jserv/control/newUser",
		{
		  name: name,
		  email: email,
		  pwd: pwd,
		  region: region,
		  phone: phone
		},
		function(data,status){
			if(status == "success"){
				if(data.status == "success"){
					$rootScope.sessionHolder = data.user.id;
					$rootScope.user = data.user;
					$state.go('dashboard');
				}
				else{
					alert("Could not signup!")
				}
			}else{
				alert("something went wrong - server error");
			}
		});
	}
})
