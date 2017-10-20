mainPage.service('loginUser',function($rootScope,$state){
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
					$rootScope.role = data.user.role;
					$state.go('dashboard');
				}
				else{
					alert("provided credentials are wrong!")
				}
			}else{
				alert("something went wrong - server error");
			}
		});
	}
});