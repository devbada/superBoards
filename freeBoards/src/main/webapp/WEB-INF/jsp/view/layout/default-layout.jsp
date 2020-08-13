<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles"  uri="http://tiles.apache.org/tags-tiles"%>
<%@ include file="/WEB-INF/jsp/view/layout/includeLibraries.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<tiles:insertAttribute name="header" />
	<style>
		.container {
			min-width: 90%;
		}
	</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-4 col-md-2">
				<tiles:insertAttribute name="left" />
			</div>
			<div class="col-xs-8 col-sm-8 col-md-10">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
	</div>
</body>
</html>