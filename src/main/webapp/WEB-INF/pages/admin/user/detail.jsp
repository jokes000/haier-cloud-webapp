<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- tiles prefix -->
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<div class="container">
	<div class="row">
		<h3 class="text-left">
			<c:out value="${user.username}" />
		</h3>
		<hr>
	</div>

	<div class="row">
		<h4 class="text-left">Groups</h4>
		<ul>
			<c:forEach var="group" items="${user.groupsList}">
				<li><c:out value="${group.name}" /></li>
			</c:forEach>
		</ul>

		<a id="modal-586231" href="#modal-container-586231" role="button"
			class="btn btn-primary btn-small" data-toggle="modal">Add groups</a>

		<div id="modal-container-586231" class="modal hide fade" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3 id="myModalLabel">Add groups</h3>
			</div>
			<div class="modal-body">
				<form class="form-horizontal"
					action="<%=request.getContextPath()%>/admin/user/${user.id}/group/add"
					method="POST" onsubmit="return valid()">
					<div class="control-group">
						<label class="control-label" for="selectPermission">URL地址</label>
						<div class="controls" id="div_group">
							<select multiple="multiple" name="ids">
								<c:forEach var="group" items="${groupsList}">
									<option value="${group.id}"><c:out
											value="${group.name}" /></option>
								</c:forEach>
							</select> <span class="help-inline" id="help_group"></span>
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

	<div class="row">
		<h4 class="text-left">Permissions</h4>
		<ul>
			<c:forEach var="perm" items="${user.permsList}">
				<li><c:out value="${perm.permission}" /></li>
			</c:forEach>
		</ul>

		<a id="modal-428591" href="#modal-container-428591" role="button"
			class="btn btn-primary btn-small" data-toggle="modal">Add
			permissions</a>

		<div id="modal-container-428591" class="modal hide fade" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">×</button>
				<h3 id="myModalLabel">Add permissions</h3>
			</div>
			<div class="modal-body">
				<form class="form-horizontal"
					action="<%=request.getContextPath()%>/admin/user/${user.id}/permission/add"
					method="POST" onsubmit="return valid()">
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