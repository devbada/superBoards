<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/resources/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">

<link rel="stylesheet" href="/resources/lib/datatables/datatables.min.css">
<link rel="stylesheet" href="/resources/lib/datatables/Buttons-1.6.3/css/buttons.dataTables.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="/resources/lib/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">

<!-- jquery comments -->
<link rel="stylesheet" href="/resources/lib/Viima-jquery-comments-1.5.0-1/css/jquery-comments.css">

<!-- Latest compiled and minified JavaScript -->

<script src="/resources/lib/jquery/jquery-3.5.1.min.js" ></script>
<script src="/resources/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js" ></script>
<script src="/resources/lib/moment/moment.min.js"></script>

<!--  data tables -->
<script src="/resources/lib/datatables/datatables.min.js"></script>

<script src="/resources/lib/datatables/Buttons-1.6.3/js/dataTables.buttons.min.js"></script>
<script src="/resources/lib/datatables/Buttons-1.6.3/js/buttons.flash.min.js"></script>
<script src="/resources/lib/datatables/Buttons-1.6.3/js/buttons.html5.min.js"></script>
<script src="/resources/lib/datatables/Buttons-1.6.3/js/buttons.print.min.js"></script>
<script src="/resources/lib/datatables/datetime/datetime.js"></script>
<script src="/resources/lib/datatables/paginations/simple_numbers_no_ellipses.js"></script>

<script src="/resources/lib/ckEditor/4.14.1_full/ckeditor.js"></script>

<script src="/resources/lib/Viima-jquery-comments-1.5.0-1/js/jquery-comments.min.js"></script>

<script>
    $( document ).ajaxError(function( event, jqxhr, settings, thrownError ) {
        if ( jqxhr.status == 401 ) {
            alert('세션이 만료되어 유효한 인증 자격 증명이 없습니다.\n로그인을 다시 하십시오.');
            location.href = "/login";
        }
        console.log(thrownError);
    });
</script>