angular.module('login',[]);
loginCtrl.$inject = ["$scope", "$rootScope", "$http"];
function loginCtrl($scope, $rootScope, $http) {
	$scope.login = function(){
		var params = {
				_locale : 'zh_CN',
				TellerId : $scope.TellerId,
				Password : $scope.Password
		};
		$http.post("/slg-sn/login.do",params).success(function(u){
			if(u.ErrorCode) {
				$scope.error(u);
			}else{
				window.location.href = 'main.html';
			}
		});
	}
	$scope.error = function(u){
		var errorMessage;
		if(u.ErrorMessage)
			errorMessage = u.ErrorMessage;
		else
			errorMessage = u.ErrorCode;
		$("#errorMessage").html(errorMessage);
	}

	$scope.freshVerifyCode = function(){
		var img = document.getElementById("vcImg");
		img.src = "/slg-sn/verifyCode.do?date=" + new Date();
	}

};
