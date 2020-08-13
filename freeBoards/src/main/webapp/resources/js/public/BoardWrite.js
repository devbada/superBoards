/**
 * 게시글 쓰기 스크립트
 * @author minam.cho
 * @since August 09, 2020
 */
var BoardWrite = BoardWrite  || (function(){
	var DataAction = {

		/**
		 * 글쓰기
		 */
		insertBoardDetail: function() {
			$('#content').val(CKEDITOR.instances.textareaContent.getData());

			if ( $('#title').val() == "" || $('#title').val() == null ) {
				alert('[제목]을 입력하세요.');
				return false;
			}

			if ( $('#content').val() == "" || $('#content').val() == null ) {
				alert('[내용]을 입력하세요.');
				return false;
			}

			$.ajax({
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : $('#formBoardWrite').serialize(),
				dataType : "json",
				url : '/public/insertBoardDetailAjax.do',
				success : function(data) {
					console.log('insertBoardDetail');
					if ( data ) {
						if (data.result == "SUCCESS" && data.writeType == "INSERT") {
							alert('글쓰기가 완료되었습니다.');
							location.href="/public/boardList.do?bmId=" + $('#bmId').val();

						} else if (data.result == "SUCCESS" && data.writeType == "REPLY") {
							alert('답글을 작성하였습니다.');
							location.href="/public/boardDetail.do?bmId=" + $('#bmId').val() + '&bdId=' + $('#replyTargetBdId').val();
						}
					} else {
						alert('에러가 발생하였습니다.');
						
					}
				}
			});
		},
		
		/**
		 * 글수정
		 */
		updateBoardDetail: function() {
			$('#content').val(CKEDITOR.instances.textareaContent.getData());

			if ( $('#title').val() == "" || $('#title').val() == null ) {
				alert('[제목]을 입력하세요.');
				return false;
			}

			if ( $('#content').val() == "" || $('#content').val() == null ) {
				alert('[내용]을 입력하세요.');
				return false;
			}

			$.ajax({
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : $('#formBoardWrite').serialize(),
				dataType : "json",
				url : '/public/updateBoardDetailAjax.do',
				success : function(data) {
					console.log('updateBoardDetail');
					if ( data ) {
						if (data.result == "SUCCESS" ) {
							alert('수정이 완료되었습니다.');
							location.href="/public/boardDetail.do?bmId=" + $('#bmId').val() + '&bdId=' + $('#replyTargetBdId').val();

						} else if (data.result == "WRONG_WRITER") {
							alert('작성자만 수정할 수 있습니다.');
							return false;

						} else {
							alert('에러가 발생하였습니다.');

						}
					} else {
						alert('에러가 발생하였습니다.');
						
					}
				}
			});
		}
	};
	
	var fn = {
		initEvents: function(){
			
			// 글쓰기 버튼 기능 추가
			$("#buttonWriteAction").on("click", DataAction.insertBoardDetail);

			// 글쓰기 버튼 기능 추가
			$("#buttonModifyAction").on("click", DataAction.updateBoardDetail);
			
			// 글쓰기 취소 버튼 기능 추가
			$("#buttonWriteCancel").on("click", function(evt) {
				history.back(-1);
			});
			
		},

		initEditor: function() {
			
			/*
				CKEditor Install
			*/
			CKEDITOR.replace( 'textareaContent' );
			
		}
	};
	
	
	
	return {
		"FN" : fn, "DataAction": DataAction
	};
	
})();

$(function(){
	$(document).ready(function(){
		BoardWrite.FN.initEvents();
		BoardWrite.FN.initEditor();
	});
});