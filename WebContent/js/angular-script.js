mainPage.controller('signinCtrl',function($scope,$state,$rootScope,userServices){
			$scope.email;
			$scope.pwd;
			$scope.login = function(){
				userServices.checkLogin($scope.email, $scope.pwd);
			}
		})
		.directive('ngFiles', ['$parse', function ($parse) {

            function fn_link(scope, element, attrs) {
                var onChange = $parse(attrs.ngFiles);
                element.on('change', function (event) {
                    onChange(scope, { $files: event.target.files });
                });
            };

            return {
                link: fn_link
            }
        } ])
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
		.controller('sellCtrl',function($scope,userServices){
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
		.controller('userPanelCtrl',function($state,$scope){
			$scope.sellPage=function(){
				$state.go('dashboard.sell')
			}
			$scope.searchPage=function(){
				$state.go('dashboard.search')
			}
		})
		.controller('searchCtrl', function($state,$scope, userServices){
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
			  $scope.initcap = function(string) {
					return string.charAt(0).toUpperCase() + string.slice(1);
				}
				 $scope.selectCar = function (carId){
					userServices.setForCarDetail(carId);
				}
			  
			  $scope.printCars = function(cars, noOfCars){
				  
				  var i=0;
				  var t=1;
				  $scope.scripting ="";
				  while(i<noOfCars){
					  $scope.scripting += "<div class='row'>";
					  while((t%3 != 0) && (i<noOfCars)){
						  $scope.scripting += '<div class="col-sm-4"><div class="thumbnail"><span style="cursor:pointer;" onclick="selectCar(\''+cars[i].id+'\')"><img src="http://localhost:8080/carshop/Jserv/control/media/'+cars[i].id+'" alt="Nature" style="width:100%"><div class="caption"><h4>'+$scope.initcap(cars[i].brand)+' '+$scope.initcap(cars[i].name)+'</h4></span><p>'+cars[i].year+'</p><h5 style="text-align: right;"><span style="background-color: #afffd7; cursor:pointer;">Compare</span> <span style="background: #eaeaea;">&#8377 '+cars[i].price+'</span></h5></div></a></div></div>';
						  i++;t++;
					  }
					  if(i<noOfCars){
						  $scope.scripting += '<div class="col-sm-4"><div class="thumbnail"><span style="cursor:pointer;" onclick="selectCar(\''+cars[i].id+'\')"><img src="http://localhost:8080/carshop/Jserv/control/media/'+cars[i].id+'" alt="Nature" style="width:100%"><div class="caption"><h4>'+$scope.initcap(cars[i].brand)+' '+$scope.initcap(cars[i].name)+'</h4></span><p>'+cars[i].year+'</p><h5 style="text-align: right;"><span style="background-color: #afffd7; cursor:pointer;">Compare</span> <span style="background: #eaeaea;">&#8377 '+cars[i].price+'</span></h5></div></div></div>';
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
			$scope.data = userServices.getCarDetails();
		})
		