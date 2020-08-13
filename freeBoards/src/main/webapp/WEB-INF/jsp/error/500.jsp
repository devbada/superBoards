<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>에러가 발생하였습니다.</title>
</head>
<body>
	<div class="errorPage">
		<span class="errorHead">에러가 발생하였습니다!!</span><br />
		<p>
			<c:out value="${requestScope['javax.servlet.error.request_uri']}" />
		</p>
		
	</div>
</body>
</html>
