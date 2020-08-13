<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script src="/resources/js/public/BoardList.js"></script>

<div class="row">
	<div class="btn-group">어서오세요. 공개 게시판 입니다.</div>
</div>
<form id="formBoardList" name="formBoardList" method="get">
	<input type="hidden" id="bmId" name="bmId" value="${bmId }"/>
	
	<div class="row">
		<table id="tableList" class="table table-striped " style="width: 100%">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>등록일시</th>
				</tr>
			</thead>
		</table>
	</div>
</form>
<div class="row text-right">
	<button id="buttonWrite" type="button" class="btn btn-success">글쓰기</button>
</div>
