<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="row col-sm-12">
	<div class="row col-sm-8">
		<h2>
			저는 헤더입니다.
			타이틀이 필요합니다.
		</h2>
	</div>
	
	<sec:authorize access="isAuthenticated()">
		<div class="row col-sm-4">
			<sec:authentication property="principal.username" />님 안녕하세요 :D || <a href="/logout">로그아웃</a>
		</div>
	</sec:authorize>
</div>
