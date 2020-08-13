/**
 * Login 상세 스크립트
 * @author minam.cho
 * @since August 09, 2020
 */
var Login = Login  || (function(){

	var fn = {
			
		initEvents : function(){//버튼 초기화
			/** 사용자 등록 */
			$('#buttonJoinRequest').on('click', function(){
				location.href = '/join/join.do';
			});

			/** 정보찾기 */
			$('#buttonResetLoginInfo').on('click', function(){
				location.href = '/join/resetLoginInfo.do';
			});
		}
	};
	
	return {
		"FN" : fn
	};
	
})();



$(function(){
	$(document).ready(function(){
		Login.FN.initEvents();
	});
});