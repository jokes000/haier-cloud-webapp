<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- tiles prefix -->
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<div class="container">
	<div class="center">
		<h3 class="text-left">添加群组</h3>
		<hr>

		<div class="row">
			<div class="span3">
				<form class="form-horizontal"
					action="<%=request.getContextPath()%>/admin/group/add/submit"
					method="POST" onsubmit="return valid()">
					<div class="control-group">
						<label class="control-label" for="inputEmail">群组名</label>
						<div class="controls" id="div_name">
							<input type="text" id="input_name" name="name" /> <span
								class="help-inline" id="help_name"></span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="selectPermission">URL地址</label>
						<div class="controls" id="div_perm">
							<select multiple="multiple" name="ids">
								<c:forEach var="perm" items="${permsList}">
									<option value="${perm.id}"><c:out
											value="${perm.permission}" /></option>
								</c:forEach>
							</select> <span class="help-inline" id="help_url"></span>
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
</div>

<script type="text/javascript">
	function valid() {
		return true;
	}
</script>