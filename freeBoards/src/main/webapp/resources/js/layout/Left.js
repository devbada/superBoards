/**
 * Left 상세 스크립트
 * @author minam.cho
 * @since August 09, 2020
 */
var Left = Left  || (function(){
	var DataAction = {

		/**
		 * 권한 별 메뉴 목록 검색
		 */
		selectRoleByMenuList : function() {
			$.ajax({
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : $('#formBoardWrite').serialize(),
				dataType : "json",
				url : '/common/selectRoleByMenuListAjax.do',
				success : function(data) {
					console.log('selectRoleByMenuList');
					if ( data ) {
						var menuStr = "";
						for (var listIdx=0; listIdx < data.length; listIdx++) {
							menuStr += '<a href="/public/boardList.do?bmId='+ data[listIdx].bmId +'">'+ data[listIdx].title +'</a>'
						}
					} else {
						alert('메뉴호출: 에러가 발생하였습니다.');
					}
				}
			});
		}
	};

	var fn = {
			
		initEvents : function(){//버튼 초기화
			
		}

	};
	
	return {
		"FN" : fn, "DataAction": DataAction
	};
	
})();



$(function(){
	$(document).ready(function(){
		Left.FN.initEvents();
	});
});