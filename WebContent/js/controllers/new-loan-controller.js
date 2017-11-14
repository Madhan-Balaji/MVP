mainPage.controller('newLoanCtrl', function($state, $scope, userServices) {
	$scope.uploadNewLoan = function() {
		var loanData = new FormData();
		loanData.append("brand", $scope.loan.brand);
		loanData.append("bank", $scope.loan.bank);
		loanData.append("iFrom", $scope.loan.iFrom);
		loanData.append("iTo", $scope.loan.iTo);
		loanData.append("fee", $scope.loan.fee);
		loanData.append("amount", $scope.loan.amt);
		loanData.append("time", $scope.loan.time);
		userServices.createNewLoan(loanData);
		$scope.loadLoans();
	}
	$scope.loadLoans = function() {
		$scope.loanControl = userServices.getAllLoans();
	}
	$scope.rmvLoan = function(id) {
		if (confirm("Delete the loan?")) {
			if (userServices.removeLoan(id)) {
				var remv;
				for (i = 0; i < $scope.loanControl.length; i++) {
					if ($scope.loanControl[i].id == id) {
						remv = i;
						break;
					}
				}
				$scope.loanControl.splice(remv, 1);
			}
		}
	}
	$scope.loadLoans();
})