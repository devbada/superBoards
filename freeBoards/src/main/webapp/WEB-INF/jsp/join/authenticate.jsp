<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core"%>

<%@ include file="/WEB-INF/jsp/view/layout/includeLibraries.jsp"%>

<script src="/resources/js/join/Authenticate.js"></script>

<html>
	<head>
	    <title>인증결과</title>
	
	</head>
	<body class="bg-dark">
		<div class="container">
            <div class="row">
                <c:choose>
                    <c:when test="${result eq 'SUCCESS'}">
                        <p class="bg-success">인증되었습니다. 비밀번호를 등록 후 로그인 하세요.</p>
                        <p class="bg-danger">페이지를 새로고침하면 인증을 다시 받아야 합니다.</p>

                        <form id="formAuthenticate">
                            <input type="hidden" name="joinCertifiedKey" value="${joinCertifiedKey}" />
                            
                            <div class="row">
                                비밀번호: <input type="password" id="passwordFirst" name="password" />
                            </div>
                            <div class="row">
                                비밀번호 확인: <input type="password" id="passwordSecond" />
                            </div>

                            <button type="button" id="buttonPasswordUpdate">비밀번호 등록</button>
                        </form>

                    </c:when>
                    <c:otherwise>
                        <p class="bg-success">인증처리가 실패하였습니다. 재인증 요청을 하거나 시스템관리자에게 문의 바랍니다.</p>
                        
                    </c:otherwise>
                </c:choose>
            </div>
		</div>
	</body>
</html>
