/**
 * Authenticate 상세 스크립트
 * @author minam.cho
 * @since August 09, 2020
 */
var Authenticate = Authenticate  || (function(){
	var DataAction = {

		/**
		 * 인증 사용자 비밀번호 등록(처음 사용자)
		 */
		updateMemberPasswordAjax : function() {
			if ( Authenticate.FN.validatePassword() ) {
				$.ajax({
					type : "post",
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					data : $('#formAuthenticate').serialize(),
					dataType : "json",
					url : '/join/updateMemberPasswordAjax.do',
					success : function(data) {
						console.log('updateMemberPasswordAjax');
						if ( data.result == "SUCCESS" ) {
							alert('비밀번호가 등록되었습니다.');
							location.href="/login";
	
						} else {
							alert('에러가 발생하였습니다.');
						}
					}
				});
			}
		}
	};

	var fn = {
			
		initEvents : function(){
			//버튼 초기화
			$('#buttonPasswordUpdate').on('click', Authenticate.DataAction.updateMemberPasswordAjax);

		},

		/**
		 * 비밀번호 유효성 검사
		 */
		validatePassword: function() {
			var password = $('#passwordFirst').val();
			var checkPassword = $('#passwordSecond').val();

			if ( password === "" || checkPassword === "") {
				alert('비밀번호를 입력하세요.');
				return false;

			} else if ( password != checkPassword ) {
				alert('입력하신 비밀번호가 다릅니다.');
				return false;

			} else {
				var paswd=  /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;
				if(password.match(paswd))  {
					// 정상
					return true;
	
				} else { 
					alert('비밀번호는 [1 개 이상의 숫자와 특수 문자가 포함 된 7 ~ 15 자] 여야 합니다.');
					return false;
				}
			}
		}
	};
	
	return {
		"FN" : fn, "DataAction": DataAction
	};
	
})();



$(function(){
	$(document).ready(function(){
		Authenticate.FN.initEvents();
	});
});