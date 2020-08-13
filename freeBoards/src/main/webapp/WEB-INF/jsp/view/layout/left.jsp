<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="/resources/js/layout/Left.js"></script>

<nav id="sidebar">
	<div class="custom-menu">
		<button type="button" id="sidebarCollapse" class="btn btn-primary">
		</button>
	</div>
	<div class="img bg-wrap text-center py-4" style="background-image: url(images/bg_1.jpg);">
		<div class="user-logo">
			<div class="img" style="background-image: url(images/logo.jpg);"></div>
			<h3>WELCOME NICK NAME?</h3>
		</div>
	</div>
	<ul class="list-unstyled components mb-5">
		<li class="active">
			<a href="/public/boardList.do?bmId=NOTICE">공지 사항</a>
		</li>
		<li>
			<a href="/public/boardList.do?bmId=POST_OFFICE">칭찬 우체국</a>
		</li>
		<li>
			<a href="/public/boardList.do?bmId=GREEN_FAMILY">초록 Family</a>
		</li>
		<li>
			<a href="/public/boardList.do?bmId=CURTURAL_PROPOSAL">재단 문화 제안</a>
		</li>
		<li>
			<a href="/public/boardList.do?bmId=HOT_ISSUE">사내 핫 이슈</a>
		</li>
		<li>
			<a href="/public/boardList.do?bmId=COMMENT_COUNSELING">댓글 상담실</a>
		</li>
		<li>
			<a href="/public/boardList.do?bmId=Q_A">Q&A</a>
		</li>
	</ul>

</nav>
	