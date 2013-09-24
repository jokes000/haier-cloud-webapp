<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- tiles prefix -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- HTML5 definition -->
<!DOCTYPE html>

<html>
<head>
<!-- styles -->
<link href="<%=request.getContextPath()%>/resource/css/bootstrap.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/resource/css/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/resource/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/resource/css/bootstrap-switch.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/resource/css/simple-slider.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/resource/css/simple-slider-volume.css"
	rel="stylesheet" type="text/css" />
<link
	href="<%=request.getContextPath()%>/resource/css/simplePagination.css"
	rel="stylesheet" type="text/css" />
<!-- self defined -->
<link href="<%=request.getContextPath()%>/resource/css/custom.css"
	rel="stylesheet" type="text/css" />
<!-- end of styles -->

<!-- javascripts -->
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/jquery-2.0.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/bootstrap-switch.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/bootstrap-slider.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/simple-slider.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/simplePagination.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
</head>

<body>
	<form class="form-horizontal"
		action="<%=request.getContextPath()%>/register/submit"
		method="POST">
		<div class="control-group">
			<label class="control-label" for="inputUsername">用户名</label>
			<div class="controls">
				<input type="text" name="username" id="inputUsername" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputPassword">密码</label>
			<div class="controls">
				<input type="password" name="password" id="inputPassword" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputPassword">确认密码</label>
			<div class="controls">
				<input type="password" id="confirmPassword" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputEmail">email</label>
			<div class="controls">
				<input type="text" name="email" id="inputEmail" />
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<input type="submit" class="btn btn-primary" value="注册" />
			</div>
		</div>
	</form>
</body>
</html>