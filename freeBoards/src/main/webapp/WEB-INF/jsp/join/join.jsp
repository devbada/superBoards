<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/view/layout/includeLibraries.jsp"%>

<script src="/resources/js/join/Join.js"></script>

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
            <div class="form-group">
                <label for="provision" class="col-lg-2 control-label">회원가입약관</label>
                <div class="col-lg-10" id="provision">
                    <textarea class="form-control" rows="8" style="resize:none">약관동의
                        </textarea>
                    <div class="radio">
                        <label>
                            <input type="radio" id="provisionYn" name="provisionYn" value="Y" autofocus="autofocus"
                                checked>
                            동의합니다.
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" id="provisionYn" name="provisionYn" value="N">
                            동의하지 않습니다.
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="memberInfo" class="col-lg-2 control-label">개인정보취급방침</label>
                <div class="col-lg-10" id="memberInfo">
                    <textarea class="form-control" rows="8" style="resize:none">개인정보의 항목 및 수집방법
                        </textarea>
                    <div class="radio">
                        <label>
                            <input type="radio" id="memberInfoYn" name="memberInfoYn" value="Y" checked>
                            동의합니다.
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" id="memberInfoYn" name="memberInfoYn" value="N">
                            동의하지 않습니다.
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group" id="divId">
                <label for="inputName" class="col-lg-2 control-label">ID</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control onlyAlphabetAndNumber" id="memId" name="memId" data-rule-required="true"
                        placeholder="영문과 숫자만 입력할 수 있습니다." maxlength="20">
                </div>
            </div>

            <div class="form-group" id="divName">
                <label for="inputName" class="col-lg-2 control-label">이름</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control onlyHangul" id="name" name="name" data-rule-required="true"
                        placeholder="한글만 입력 가능합니다." maxlength="15">
                </div>
            </div>

            <div class="form-group" id="divNickname">
                <label for="inputNickname" class="col-lg-2 control-label">별명</label>
                <div class="col-lg-10">
                    <input type="text" class="form-control" id="nickname" name="nickName" data-rule-required="true" placeholder="별명"
                        maxlength="15">
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
                    <button id="buttonJoinAction" type="button" class="btn btn-primary">등록</button>
                </div>
            </div>
        </form>

        <!--// 본문 들어가는 부분 -->
    </div>
</body>

</html>