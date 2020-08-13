<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/WEB-INF/jsp/view/layout/includeLibraries.jsp"%>

<script src="/resources/js/login/Login.js"></script>

<html>
	<head>
	    <title>사용자 로그인</title>
	
	</head>
	<body class="bg-dark">
		<div class="container">
			<div class="row text-center">
				<p> <font size="96pt">초록</font></p>
				<p> <font size="96pt">광장</font></p>
			</div>
		    <div class="card card-login mx-auto mt-5">
		        <div class="card-header">로그인</div>
		        <div class="card-body">
		            <form action="/login/connect" method="post">
		                <div class="form-group">
		                    <div class="form-label-group">
		                        <input value="devbadaAdmin" type="text" name="memId" id="memId" class="form-control input-xs" placeholder="User ID" required="required" autofocus="autofocus">
		                        <label for="memId">아이디</label>
		                    </div>
		                </div>
		                <div class="form-group">
		                    <div class="form-label-group">
		                        <input value="@woeks*W5142" type="password" name="memPwd" id="memPwd" class="form-control" placeholder="Password" required="required">
		                        <label for="memPwd">비밀번호</label>
		                    </div>
						</div>
						<div class="form-label-group">
							<!-- 로그인 실패 시 출력할 메세지 -->
							<label class="label-info">
								${requestScope.loginFailMsg}
							</label>
						</div>
						<div class="row">
							<div class="col-xs-4">
								<button id="buttonJoinRequest" type="button" class="btn btn-success btn-block">사용자등록</button>
							</div>
							<div class="col-xs-4">
								<button type="submit" class="btn btn-primary btn-block">로그인</button>
							</div>
							<div class="col-xs-4">
								<button id="buttonResetLoginInfo" type="button" class="btn btn-default btn-block">정보찾기</button>
							</div>
						</div>
		            </form>
		        </div>
		    </div>
		</div>
	</body>
</html>
