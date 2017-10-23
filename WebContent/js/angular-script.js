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
		.controller('searchCtrl', function($state,$scope,carServices){
			$( function() {
				$( "#slider-range" ).slider({
				  range: true,
				  min: 0,
				  max: 50000000,
				  values: [ 40000, 10000000 ],
				  slide: function( event, ui ) {
					$( "#amount" ).val( "&#8377." + ui.values[ 0 ] + " - &#8377." + ui.values[ 1 ] );
				  }
				});
				$( "#amount" ).val( "&#8377." + $( "#slider-range" ).slider( "values", 0 ) +
				  " - &#8377." + $( "#slider-range" ).slider( "values", 1 ) );
			  } );
			  $scope.cars ={};
			  $scope.firstSearchLoad = function(){
				  $scope.cars = userServices.fetchAllCars();
			  }
		})
		