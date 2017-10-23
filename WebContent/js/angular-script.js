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
			  $scope.cars ={};
			  $scope.printCars = function(cars, noOfCars){
				  var i=0;
				  while(i<noOfCars){
					  $('#search-area').append("<div class='row'>");
					  while((i%3 != 0) && (i<noOfCars)){
						  $('#search-area').append('<div class="thumbnail"><a href="http://localhost:8080/carshop/Jserv/control/media/'+cars[i].id+'"><img src="http://localhost:8080/carshop/Jserv/control/media/59ed8a57c6fa13133ae21870" alt="Nature" style="width:100%"><div class="caption"><h4>Honda City</h4><p>2014</p><h5 style="text-align: right;"><span style="background: #eaeaea;">&#8377 650000</span></h5></div></div>');
						  i++;
					  }
					  $('#search-area').append("</div>");
					  i++;
				  }
			  }
			  $scope.firstLoad = function(){
				  $scope.data = userServices.fetchAllCars();
				  alert($scope.data.cars);
				  $scope.printCars($scope.data.cars,$scope.rows);
			  }
			  $scope.firstLoad();
			  
		})
		