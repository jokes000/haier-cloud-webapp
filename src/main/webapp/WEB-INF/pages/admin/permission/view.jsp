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

		<div class="center">
			<h3 class="text-left">权限管理</h3>
			<hr>

			<table class="table table-hover">
				<thead>
					<tr>
						<th>权限名</th>
						<th>过滤URL</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="perm" items="${permsList}">
						<tr>
							<td><c:out value="${perm.permission}" /></td>
							<td><c:out value="${perm.value}" /></td>
							<td><a href="/admin/permission/delete/${perm.id}"
								class="btn btn-danger btn-small" onClick="return deletePerm()">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<a id="modal-486991" href="#modal-container-486991" role="button"
				class="btn btn-primary pull-left" data-toggle="modal">添加权限</a>

			<div id="modal-container-486991" class="modal hide fade"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">×</button>
					<h3 id="myModalLabel">添加权限</h3>
				</div>
				<div class="modal-body">
					<form class="form-horizontal"
						action="<%=request.getContextPath()%>/admin/permission/add"
						method="POST" onsubmit="return valid()">
						<div class="control-group">
							<label class="control-label" for="inputEmail">权限名</label>
							<div class="controls" id="div_perm">
								<input type="text" id="input_perm" name="permission" /> <span
									class="help-inline" id="help_perm"></span>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="inputEmail">URL</label>
							<div class="controls" id="div_url">
								<input type="text" id="input_url" name="url" /> <span
									class="help-inline" id="help_url"></span>
							</div>
						</div>
						<div class="control-group">
							<div class="controls">
								<input type="submit" class="btn btn-primary" />
							</div>
						</div>
					</form>
				</div>
				<!--  
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
		<button class="btn btn-primary">Save changes</button>
	</div>
	-->
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function valid() {
		var flag = true;

		var perm = $('#input_perm').val().trim();
		if (perm == "") {
			$('#div_perm').removeClass('controls');
			$('#div_perm').addClass('controls error');
			document.getElementById('help_perm').innerHTML = "请输入权限名称";
			flag = false;
		}

		var url = $('#input_url').val().trim();
		if (url == "") {
			$('#div_url').removeClass('controls');
			$('#div_url').addClass('controls error');
			document.getElementById('help_url').innerHTML = "请输入URL地址";
			flag = false;
		}

		return flag;
	}

	function deletePerm() {
		return confirm('确定删除？');
	}
</script>
