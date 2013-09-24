<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- tiles prefix -->
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<div class="center">
	<h3 class="text-left">添加权限</h3>
	<hr>

	<div class="row">
		<div class="span4">
			<form class="form-horizontal">
				<div class="control-group">
					<label class="control-label" for="inputEmail">权限名</label>
					<div class="controls">
						<input type="text" id="inputEmail" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="inputPassword">URL地址</label>
					<div class="controls">
						<input type="password" id="inputPassword" />
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<input type="submit" class="btn btn-primary" />
					</div>
				</div>
			</form>
		</div>
	</div>

</div>