<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/view/layout/includeLibraries.jsp"%>

<script src="/resources/js/join/ResetLoginInfo.js"></script>

<!DOCTYPE html>
<html lang="ko">

<body>
    <div class="container">
        <!-- 좌우측의 공간 확보 -->
        <!-- 헤더 들어가는 부분 -->

        <div class="row">
            <p></p>
            <div class="col-md-12">
                <small>
                    <a href="/login">로그인</a>
                </small>
            </div>
        </div>
        <!--// 헤더 들어가는 부분 -->
        <!-- 모달창 -->
        <div class="modal fade" id="defaultModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title">알림</h4>
                    </div>
                    <div class="modal-body">
                        <p class="modal-contents"></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <!--// 모달창 -->
        <hr />
        <!-- 본문 들어가는 부분 -->

        <form id="joinForm" class="form-horizontal" role="form" method="post">
            <div class="form-group" id="divId">
                <label for="inputName" class="col-lg-2 control-label">ID</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control onlyAlphabetAndNumber" id="memId" name="memId" data-rule-required="true" tabIndex="0"
                        placeholder="영문과 숫자만 입력할 수 있습니다." maxlength="20">
                </div>
            </div>

            <div class="form-group" id="divEmail">
                <label for="inputEmail" class="col-lg-2 control-label">이메일</label>
                <div class="col-lg-10">
                    <input type="email" class="form-control" id="email" name="email" data-rule-required="true" placeholder="이메일"
                        maxlength="60" pattern="/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/"
                        <%-- pattern=".+@abc.or.kr" --%> required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <button id="buttonRequestResetLoginInfo" type="button" class="btn btn-primary">인증메일 전송</button>
                </div>
            </div>
        </form>

        <!--// 본문 들어가는 부분 -->
    </div>
</body>

</html>