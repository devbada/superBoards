<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>

<script src="/resources/js/public/BoardWrite.js"></script>

<div class="btn-group">
	게시판 이름
</div>

<form id="formBoardWrite" name="formBoardWrite" method="post">
	<input type="hidden" id="bmId" name="bmId" value="${bmId }"/>
	<input type="hidden" id="replyTargetBdId" name="replyTargetBdId" value="${param.bdId }"/>
	<input type="hidden" id="content" name="content" />
	
	<c:choose>
		<c:when test="${action eq 'MODIFY'}">
			<div class="row">
				<input type="hidden" id="bdId" name="bdId" value="${boardDetail.bdId }"/>
				<input type="text" id="title" name="title" placeholder="제목을 입력하세요." class="form-control" value="${boardDetail.title}" />
			</div>
			<div class="row">
				<textarea id="textareaContent" rows="1" cols="1">
					${boardDetail.content}
				</textarea> 
			</div>
		</c:when>
		<c:otherwise>
			<div class="row">
				<input type="text" id="title" name="title" placeholder="제목을 입력하세요." class="form-control"/>
			</div>
			<div class="row">
				<textarea id="textareaContent" rows="1" cols="1"></textarea> 
			</div>
		</c:otherwise>
	</c:choose>
</form>

<div class="row text-right">
	<button id="buttonWriteCancel" type="button" class="btn btn-warning">취소</button>
	
	<c:choose>
		<c:when test="${action eq 'REPLY' }">
			<button id="buttonWriteAction" type="button" class="btn btn-primary">답글등록</button>
		</c:when>
		<c:when test="${action eq 'MODIFY' }">
			<button id="buttonModifyAction" type="button" class="btn btn-primary">수정</button>
		</c:when>
		<c:otherwise>
			<button id="buttonWriteAction" type="button" class="btn btn-primary">등록</button>
		</c:otherwise>
	</c:choose>
</div>
