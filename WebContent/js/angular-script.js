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
		
		.controller('dashboardCtrl',function($scope,$state,$rootScope,userServices){
			userServices.checkSession();
			$scope.signOut = function(){userServices.signingOut();}
			$rootScope.compare=[];
			$rootScope.compareName=[];
			$rootScope.setCompare = function(id,name){
				if($rootScope.compare.length < 3){
					exist =0;
					for(i=0; i<$rootScope.compare.length;i++){
						if($rootScope.compare[i] == id){
							exist++;
							break;
						}
					}
					if(exist == 0){
						$rootScope.compare.push(id);
						$rootScope.compareName.push(name);
					}
					else{
						alert("Data already available for compare");
					}
				}
				else{
					alert("Maximum comparison limit reached");
				}
			}
			$rootScope.removeCompare = function(name){
				for(i=0;i<$rootScope.compareName.length;i++){
					if($rootScope.compareName[i] == name){
						$rootScope.compare.splice(i,1);
						$rootScope.compareName.splice(i,1);
					}
				}
				
			}
			$scope.goCompare = function(){
				$state.go('dashboard.compare');
			}
		})
		
		.controller('sellCtrl',function($scope,userServices){
			userServices.checkSession();
			$scope.data={};
			$scope.sell = function(){
				var files = $('#myFile').prop('files');
				// alert(files[0].name);
				var fd = new FormData();
				fd.append('price',$scope.data.price)
				fd.append('brand',$scope.data.brand);
				fd.append('type',$scope.data.type);
				fd.append('name',$scope.data.carName);
				fd.append('model',$scope.data.carModel);
				fd.append('year',$scope.data.carYear);
				fd.append('gear',$scope.data.carGear);
				fd.append('seat',$scope.data.seat);
				fd.append('color',$scope.data.color);
				fd.append('owner',$scope.data.owner);
				fd.append('fuelType',$scope.data.fuelType);
				fd.append('milage',$scope.data.milage);
				fd.append('cc',$scope.data.cc);
				fd.append('address',$scope.data.address);
				fd.append('file',files[0]);
				userServices.sellCar(fd);
			}
		})
		
		.controller('userPanelCtrl',function($state,$scope,userServices){
			userServices.checkSession();
			$scope.sellPage=function(){
				$state.go('dashboard.sell');
			}
			$scope.searchPage=function(){
				$state.go('dashboard.search');
			}
			$scope.loadNews = function(){
				$scope.dailyNews = userServices.loadDailyNews();
			}
			$scope.loadInsurance = function(){
				$scope.insurances = userServices.loadHomeInsurance();
			}
			$scope.showNews = function(id){
				localStorage.setItem("show-news",id);
				$state.go('dashboard.news');
			}
			$scope.showInsurance = function(id){
				localStorage.setItem("show-insu",id);
				$state.go('dashboard.showInsurance');
			}
			$scope.loadNews();
			$scope.loadInsurance();
		})
		
		.controller('searchCtrl', function($state,$scope, $rootScope, userServices){
			$scope.searchTerm;
			$scope.searchTheTerm = function(){
				$scope.cars = userServices.searchCarByTerm($scope.searchTerm);
			}
			userServices.checkSession();
			$scope.carNames=[];
			$scope.carBrand=[];
			$( function() {
				$( "#slider-range" ).slider({
				  range: true,
				  min: 0,
				  max: 50000000,
				  values: [ 40000, 10000000 ],
				  slide: function( event, ui ) {
					$( "#amount" ).val( "RS." + ui.values[ 0 ] + " - RS." + ui.values[ 1 ] );
				  }
				});
				$( "#amount" ).val( "RS." + $( "#slider-range" ).slider( "values", 0 ) +
				  " - &RS." + $( "#slider-range" ).slider( "values", 1 ) );
				  
			  } );
			  $scope.temp = 0;
			  $rootScope.initcap = function(string) {
					return string.charAt(0).toUpperCase() + string.slice(1);
				}
				 $scope.selectCar = function(carId){
					userServices.setForCarDetail(carId);
				}
			  
			  $scope.printCars = function(cars, noOfCars){
				  
				  var i=0;
				  var t=1;
				  $scope.scripting ="";
				  while(i<noOfCars){
					  $scope.scripting += "<div class='row'>";
					  while((t%3 != 0) && (i<noOfCars)){
						  $scope.scripting += '<div class="col-sm-4"><div class="thumbnail"><img src="http://localhost:8080/carshop/Jserv/control/media/'+cars[i].id+'" alt="Nature" style="width:100%"><div class="caption"><button onclick="selectCar(\''+cars[i].id+'\')">'+$scope.initcap(cars[i].brand)+' '+$scope.initcap(cars[i].name)+'</button><p>'+cars[i].year+'</p><h5 style="text-align: right;"><span style="background-color: #afffd7; cursor:pointer;">Compare</span> <span style="background: #eaeaea;">&#8377 '+cars[i].price+'</span></h5></div></a></div></div>';
						  i++;t++;
					  }
					  if(i<noOfCars){
						  $scope.scripting += '<div class="col-sm-4"><div class="thumbnail"><img src="http://localhost:8080/carshop/Jserv/control/media/'+cars[i].id+'" alt="Nature" style="width:100%"><div class="caption"><button onclick="selectCar(\''+cars[i].id+'\')">'+$scope.initcap(cars[i].brand)+' '+$scope.initcap(cars[i].name)+'</button></span><p>'+cars[i].year+'</p><h5 style="text-align: right;"><span style="background-color: #afffd7; cursor:pointer;">Compare</span> <span style="background: #eaeaea;">&#8377 '+cars[i].price+'</span></h5></div></div></div>';
						  i++;
					  }
					  $scope.scripting += "</div>";
					  t++;
				  }
				  $('#search-area').append($scope.scripting);
			  }
			  
			  $scope.firstLoad = function(){
				  $scope.data = userServices.fetchAllCars();
				  //$scope.printCars($scope.data.cars,$scope.data.rows);
				  $scope.cars = $scope.data.cars;
				 
			  }
			  $scope.firstLoad();
			  
			  
		})
		
		.controller('carDetailCtrl', function($state, $scope, userServices){
			userServices.checkSession();
			$scope.data = userServices.getCarDetails();
		})
		
		.controller('compareCtrl', function($state,$scope,$rootScope,userServices){
			userServices.checkSession();
			$scope.compCars = [];
			for(i=0;i<$rootScope.compare.length;i++){
				$scope.data = userServices.getCarDetailCmp($rootScope.compare[i]);
				$scope.compCars.push($scope.data);
			}
			$scope.loadPage = function(){
				$scope.scripting = "";
				for(i=0;i<$scope.compCars.length;i++){
					$scope.scripting += "<div class='col-sm-"+(12/$scope.compCars.length)+"'><img style='max-height: 500px;width: 100%;' src='"+$scope.compCars[i].imageUrl+"'></div>";
				}
				$('#cmp-img').append($scope.scripting);
				$scope.scripting = "";
				for(i=0;i<$scope.compCars.length;i++){
					brand = $rootScope.initcap($scope.compCars[i].brand);
					name = $rootScope.initcap($scope.compCars[i].name);
					model =$rootScope.initcap($scope.compCars[i].model);
					year = $rootScope.initcap($scope.compCars[i].year);
					gear = $rootScope.initcap($scope.compCars[i].gear);
					seat = $rootScope.initcap($scope.compCars[i].seat);
					type = $rootScope.initcap($scope.compCars[i].type);
					color = $rootScope.initcap($scope.compCars[i].color);
					owner =$rootScope.initcap($scope.compCars[i].owner);
					fuel =$rootScope.initcap($scope.compCars[i].fuel);
					milage =$rootScope.initcap($scope.compCars[i].milage);
					cc =$rootScope.initcap($scope.compCars[i].cc);
					price = $rootScope.initcap($scope.compCars[i].price);
					$('#heading').append("<th style='text-align:center;'>"+$rootScope.initcap(brand)+" "+$rootScope.initcap(name)+"</th>")
					$('#brand').append("<td style='text-align:center;'>"+brand+"</td>");
					$('#name').append("<td style='text-align:center;'>"+name+"</td>");
					$('#model').append("<td style='text-align:center;'>"+model+"</td>");
					$('#year').append("<td style='text-align:center;'>"+year+"</td>");
					$('#gear').append("<td style='text-align:center;'>"+gear+"</td>");
					$('#seat').append("<td style='text-align:center;'>"+seat+"</td>");
					$('#type').append("<td style='text-align:center;'>"+type+"</td>");
					$('#color').append("<td style='text-align:center;'>"+color+"</td>");
					$('#owner').append("<td style='text-align:center;'>"+owner+"</td>");
					$('#fuel').append("<td style='text-align:center;'>"+fuel+"</td>");
					$('#milage').append("<td style='text-align:center;'>"+milage+"</td>");
					$('#cc').append("<td style='text-align:center;'>"+cc+"</td>");
					$('#price').append("<td style='text-align:center;'>"+price+"</td>");
				}
				
			}
			$scope.loadPage();
		})
		.controller('newSellCtrl',function($scope,userServices){
			userServices.checkSession();
			$scope.data={};
			$scope.sellNew = function(){
				var files = $('#myFile').prop('files');
				// alert(files[0].name);
				var fds = new FormData();
				fds.append('price',$scope.data.price)
				fds.append('brand',$scope.data.brand);
				fds.append('type',$scope.data.type);
				fds.append('name',$scope.data.carName);
				fds.append('model',$scope.data.carModel);
				fds.append('year',$scope.data.carYear);
				fds.append('gear',$scope.data.carGear);
				fds.append('seat',$scope.data.seat);
				fds.append('color',$scope.data.color);
				fds.append('owner',$scope.data.owner);
				fds.append('fuelType',$scope.data.fuelType);
				fds.append('milage',$scope.data.milage);
				fds.append('cc',$scope.data.cc);
				fds.append('address',$scope.data.address);
				fds.append('file',files[0]);
				userServices.sellNewCar(fd);
			}
			
		})
		.controller('newInsuCtrl',function($scope, userServices){
			$scope.insurance = {};
			$scope.sellInsu = function(){
				userServices.submitNewInsurance($scope.insurance);
			}
		})
		.controller('newNewsCtrl',function($scope, userServices){
			$scope.news = {};
			$scope.publishNews = function(){
				var files = $('#myFile').prop('files');
				$scope.news.file = files[0];
				var data = new FormData();
				data.append("head",$scope.news.heading);
				data.append("content", $scope.news.content);
				data.append("file", $scope.news.file);
				 userServices.postNews(data);
			}
			
		})
		.controller('viewNews',function($scope, userServices){
			$scope.news = {};
			$scope.loadNews = function(){
				$scope.news = userServices.getNewsData(localStorage.getItem("show-news"));
			}
			$scope.loadNews();
		})
		.controller('insuranceCtrl',function($scope, userServices){
			$scope.insurance = {};
			$scope.loadData = function(){
				$scope.insurance = userServices.loadInsuData();
			}
			$scope.loadData();
		})