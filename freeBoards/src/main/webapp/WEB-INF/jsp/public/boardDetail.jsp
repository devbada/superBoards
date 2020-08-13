<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="/resources/js/public/BoardDetail.js"></script>

<div class="btn-group">
	게시판 이름
</div>

<div class="row">
	<form id="formBoardDetail" name="formBoardDetail" method="get">
		<input type="hidden" id="bmId" name="bmId" value="${boardDetail.bmId }" />
		<input type="hidden" id="bdId" name="bdId" value="${boardDetail.bdId }" />
	
		<div class="row">
			제목: ${boardDetail.title }
		</div>
		<div class="row">
			작성자: ${boardDetail.regId } 조회수: ${boardDetail.readCnt } 
		</div>
		
		<div class="row">
			작성일: ${boardDetail.regDt } 작성자 IP: ${boardDetail.regIp }
		</div>
		
		<div class="row">
			내용:
			<div id="mainContent" style="height: 50%; width:100%;">
				${boardDetail.content }
			</div>
		</div>
		<div class="row">
			게시글에 대한 댓글
			<div id="comments" class="jquery-comments"><%-- auto generatedCode Via javaScript --%></div>
		</div>
	
	</form>
</div>
<div class="row text-right">
	<button id="buttonModify" type="button" class="btn btn-info">수정</button>
	<button id="buttonReply" type="button" class="btn btn-primary">답글</button>
	<button id="buttonList" type="button" class="btn btn-success">목록</button>
</div>