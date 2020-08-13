/**
 * 게시글 상세 스크립트
 * @author minam.cho
 * @since August 09, 2020
 */
var BoardDetail = BoardDetail  || (function(){
	
	var DataAction = {
		loadContentComments: function(success, error) {
			// TODO 게시글의 댓글 데이터 가져오기
			$.ajax({
				type : "get",
				contentType : "application/json",
				dataType : "json",
				data: $('#formBoardDetail').serialize(),
				url : '/public/selectBoardCommentsListAjax.do',
				success : function(data) {
					console.log('selectBoardCommentsListAjax');
					if ( data ) {
						success(data);
					}
				}
			});
		}
	};
	
	var fn = {
		initEvents: function(){
			
			// 글쓰기 취소 버튼 기능 추가
			$("#buttonWriteCancel").on("click", function(evt) {
				history.back(-1);
			});
			
			// 목록 버튼 기능 추가
			$("#buttonList").on("click", function(evt) {
				location.href="/public/boardList.do?bmId=" + $('#bmId').val();
			});
			
			// 답글 버튼 기능 추가
			$("#buttonReply").on("click", function(evt) {
				location.href="/public/boardWrite.do?action=REPLY&bmId=" + $('#bmId').val() + '&bdId=' + $("#bdId").val();
			});
			
			// 수정 버튼 기능 추가
			$("#buttonModify").on("click", function(){
				location.href="/public/boardWrite.do?action=MODIFY&bmId=" + $('#bmId').val() + '&bdId=' + $("#bdId").val();
			});
			
		},
		initComments: function() {
			$('#comments').comments({
				profilePictureURL : '',
				getComments : function(success, error) {
					// var commentsArray = [ {
					// 	id : 1,
					// 	created : '2015-10-01',
					// 	content : 'Lorem ipsum dolort sit amet',
					// 	fullname : 'Simon Powell',
					// 	upvote_count : 2,
					// 	user_has_upvoted : false
					// } ];

					BoardDetail.DataAction.loadContentComments(success, error);
				},

				/**
				 * 댓글 등록
				 * @param {} commentJSON 
				 * @param {*} success 
				 * @param {*} error 
				 */
				postComment: function(commentJSON, success, error) {
					$.ajax({
					  type: 'post',
					  url: '/public/insertBoardComment.do',
					  data: commentJSON,
					  success: function(comment) {
						success(comment)
					  },
					  error: error
					});
				},
				/**
				 * 댓글 업데이트
				 * @param {} commentJSON 
				 * @param {*} success 
				 * @param {*} error 
				 */
				putComment: function(commentJSON, success, error) {
					$.ajax({
					  type: 'put',
					  url: '/api/comments/' + commentJSON.id,
					  data: commentJSON,
					  success: function(comment) {
						success(comment)
					  },
					  error: error
					});
				},
				/**
				 * 댓글 삭제
				 * @param {*} commentJSON 
				 * @param {*} success 
				 * @param {*} error 
				 */
				deleteComment: function(commentJSON, success, error) {
					$.ajax({
					  type: 'delete',
					  url: '/api/comments/' + commentJSON.id,
					  success: success,
					  error: error
					});
				}
			});
		}
	};
	
	return {
		"FN" : fn, "DataAction": DataAction
	};
	
})();

$(function(){
	$(document).ready(function(){
		BoardDetail.FN.initEvents();
		BoardDetail.FN.initComments();
	});
});