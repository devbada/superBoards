/**
 * ResetLoginInfo 상세 스크립트
 * @author minam.cho
 * @since August 12, 2020
 */
var ResetLoginInfo = ResetLoginInfo  || (function(){
	var DataAction = {

		/**
		 * 사용자 로그인 정보 초기화 요청
		 */
		requestResetLoginInfo : function() {
			var divId = $('#divId');
			var divEmail = $('#divEmail');

			//모달을 전역변수로 선언
			var modalContents = $(".modal-contents");
			var modal = $("#defaultModal");

			//아이디 검사
			if($('#memId').val()==""){
				modalContents.text("아이디를 입력하여 주시기 바랍니다.");
				modal.modal('show');
				
				divId.removeClass("has-success");
				divId.addClass("has-error");
				$('#memId').focus();
				return false;
			}else{
				divId.removeClass("has-error");
				divId.addClass("has-success");
			}

			//이메일
			if($('#email').val()==""){
				modalContents.text("이메일을 입력하여 주시기 바랍니다.");
				modal.modal('show');
				
				divEmail.removeClass("has-success");
				divEmail.addClass("has-error");
				$('#email').focus();
				return false;
			}else{
				divEmail.removeClass("has-error");
				divEmail.addClass("has-success");
			}

			if ( ResetLoginInfo.FN.checkEmailValidation() ) {
				$.ajax({
					type : "post",
					contentType : "application/x-www-form-urlencoded; charset=UTF-8",
					data : $('#joinForm').serialize(),
					dataType : "json",
					url : '/join/requestResetLoginInfoAjax.do',
					success : function(data) {
						console.log('requestResetLoginInfoAjax');
						if ( data.result == "SUCCESS" ) {
							alert('로그인정보 초기화를 위한 메일 전송되었습니다.\n가입된 이메일을 확인하여 인증 후 사용하시기 바랍니다.');
							location.href="/login";
	
						} else if ( data.result == "NOT_FOUND" ) {
							alert('등록된 정보를 찾을 수 없습니다.');
							return false;
	
						}
					}
				});
			}
		}
	};

	var fn = {
			
		initEvents : function(){
			//버튼 초기화
			$('#buttonRequestResetLoginInfo').on('click', ResetLoginInfo.DataAction.requestResetLoginInfo);
			
			$('.onlyAlphabetAndNumber').keyup(function(event){
				if (!(event.keyCode >=37 && event.keyCode<=40)) {
					var inputVal = $(this).val();
					$(this).val($(this).val().replace(/[^_a-z0-9]/gi,'')); //_(underscore), 영어, 숫자만 가능
				}
			});

			$('#email').keyup(function(event){
				
				var divEmail = $('#divEmail');
				
				if($.trim($('#email').val())==""){
					divEmail.removeClass("has-success");
					divEmail.addClass("has-error");
				}else{
					divEmail.removeClass("has-error");
					divEmail.addClass("has-success");
				}
			});
		},

		/**
		 * 이메일 유효성 검사기
		 */
		checkEmailValidation: function(){
			var str = $('#email').val();
			var reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
			if(!reg_email.test(str)) {           
				alert('올바르지 않은 이메일 주소입니다.');
				return false;         
			}                            
			else {                       
				return true;         
			}                            
		} 

	};
	
	return {
		"FN" : fn, "DataAction": DataAction
	};
	
})();



$(function(){
	$(document).ready(function(){
		ResetLoginInfo.FN.initEvents();
	});
});