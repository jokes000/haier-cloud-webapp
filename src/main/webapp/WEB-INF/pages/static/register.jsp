<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- tiles prefix -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<style type="text/css">
/* Override some defaults */
body {
	padding-top: 40px;
}

/* The white background content wrapper */
.container .content {
	background-color: #F0ECF9;
	padding: 20px;
	margin: 0 -20px;
	-webkit-border-radius: 10px 10px 10px 10px;
	-moz-border-radius: 10px 10px 10px 10px;
	border-radius: 10px 10px 10px 10px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .15);
}

.login-form {
	margin-left: 65px;
}

legend {
	margin-right: -50px;
	font-weight: bold;
	color: #404040;
}
</style>

<div class="container" style="width: 300px">
	<div class="content" style="margin-top: 100px">
		<div class="row">
			<div class="login-form">
				<form action="<%=request.getContextPath()%>/register/submit"
					method="POST">
					<legend>注册新用户</legend>
					<fieldset>
						<div class="control-group">
							<input type="text" placeholder="用户名" name="username" />
						</div>
						<div class="control-group">
							<input type="password" placeholder="密码" name="password" />
						</div>
						<div class="control-group">
							<input type="password" placeholder="确认密码" />
						</div>
						<div class="control-group">
							<input type="text" placeholder="email" name="email" />
						</div>
						
						<div class="control-group">
							<input class="btn btn-primary" type="submit" />
							<br>
							<p style="margin-top:20px">已有帐户？<a href="<%=request.getContextPath()%>/login">登陆</a></p>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- /container -->