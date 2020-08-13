/**
 * Join 상세 스크립트
 * @author minam.cho
 * @since August 09, 2020
 */
var Join = Join  || (function(){
	var DataAction = {

		/**
		 * 권한 별 메뉴 목록 검색
		 */
		insertNewMember : function() {
			
			//------- validation 검사
			var provision = $('#provision');
			var memberInfo = $('#memberInfo');
			var divId = $('#divId');
			var divName = $('#divName');
			var divNickname = $('#divNickname');
			var divEmail = $('#divEmail');
			
			//모달을 전역변수로 선언
			var modalContents = $(".modal-contents");
			var modal = $("#defaultModal");

			//회원가입약관
			if($('#provisionYn:checked').val()=="N"){
				modalContents.text("회원가입약관에 동의하여 주시기 바랍니다."); //모달 메시지 입력
				modal.modal('show'); //모달 띄우기
				
				provision.removeClass("has-success");
				provision.addClass("has-error");
				$('#provisionYn').focus();
				return false;
			}else{
				provision.removeClass("has-error");
				provision.addClass("has-success");
			}
			
			//개인정보취급방침
			if($('#memberInfoYn:checked').val()=="N"){
				modalContents.text("개인정보취급방침에 동의하여 주시기 바랍니다.");
				modal.modal('show');
				
				memberInfo.removeClass("has-success");
				memberInfo.addClass("has-error");
				$('#memberInfoYn').focus();
				return false;
			}else{
				memberInfo.removeClass("has-error");
				memberInfo.addClass("has-success");
			}
			
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
			
			//이름
			if($('#name').val()==""){
				modalContents.text("이름을 입력하여 주시기 바랍니다.");
				modal.modal('show');
				
				divName.removeClass("has-success");
				divName.addClass("has-error");
				$('#name').focus();
				return false;
			}else{
				divName.removeClass("has-error");
				divName.addClass("has-success");
			}
			
			//별명
			if($('#nickname').val()==""){
				modalContents.text("별명을 입력하여 주시기 바랍니다.");
				modal.modal('show');
				
				divNickname.removeClass("has-success");
				divNickname.addClass("has-error");
				$('#nickname').focus();
				return false;
			}else{
				divNickname.removeClass("has-error");
				divNickname.addClass("has-success");
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
			
			$.ajax({
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : $('#joinForm').serialize(),
				dataType : "json",
				url : '/join/insertNewMemberAjax.do',
				success : function(data) {
					console.log('insertNewMember');
					if ( data.result == "SUCCESS" ) {
						alert('사용신청이 완료되었습니다.가입된 이메일을 확인하여 인증 후 사용하시기 바랍니다.');
						location.href="/join/joinComplete.do";
					} else if ( data.result == "DUPLICATE" || data.result == "WAIT_AUTHENTICATION" ) {
						alert('사용할 수 없는 이메일입니다.\n이미 사용중이거나 인증 처리 중입니다.');
						return false;

					} else if ( data.result == "CANNOT_ALLOWED" ) {
						alert('사용할 수 없는 이메일입니다.\n허용되지 않는 메일주소입니다.');
						return false;

					} else if ( data.result == "NOT_ALLOWED_ID" ) {
						alert('사용할 수 없는 ID 입니다.');
						return false;

					}
				}
			});
		}
	};

	var fn = {
			
		initEvents : function(){
			//버튼 초기화
			$('#buttonJoinAction').on('click', Join.DataAction.insertNewMember);
			
			$('.onlyAlphabetAndNumber').keyup(function(event){
				if (!(event.keyCode >=37 && event.keyCode<=40)) {
					var inputVal = $(this).val();
					$(this).val($(this).val().replace(/[^_a-z0-9]/gi,'')); //_(underscore), 영어, 숫자만 가능
				}
			});
			
			$(".onlyHangul").keyup(function(event){
				if (!(event.keyCode >=37 && event.keyCode<=40)) {
					var inputVal = $(this).val();
					$(this).val(inputVal.replace(/[a-z0-9]/gi,''));
				}
			});

			$(".onlyNumber").keyup(function(event){
				if (!(event.keyCode >=37 && event.keyCode<=40)) {
					var inputVal = $(this).val();
					$(this).val(inputVal.replace(/[^0-9]/gi,''));
				}
			});
			
			//------- 검사하여 상태를 class에 적용
			$('#name').keyup(function(event){
				var divName = $('#divName');
				
				if($.trim($('#name').val())==""){
					divName.removeClass("has-success");
					divName.addClass("has-error");
				}else{
					divName.removeClass("has-error");
					divName.addClass("has-success");
				}
			});
			
			$('#nickname').keyup(function(event){
				var divNickname = $('#divNickname');
				
				if($.trim($('#nickname').val())==""){
					divNickname.removeClass("has-success");
					divNickname.addClass("has-error");
				}else{
					divNickname.removeClass("has-error");
					divNickname.addClass("has-success");
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
		Join.FN.initEvents();
	});
});