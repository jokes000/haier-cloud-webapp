<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- tiles prefix -->
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<div class="row">
	<div class="center">
		<h3 class="text-left">群组管理</h3>
		<hr>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>群组名</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="group" items="${groupsList}">
					<tr>
						<td><c:out value="${group.name}" /></td>
						<td><a class="btn btn-info">查看群组所有权限</a></td>
						<td><a class="btn btn-danger btn-small">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<a class="btn btn-primary pull-left" style="margin-top: 50px"
			href="<%=request.getContextPath()%>/admin/group/add">添加群组</a>
	</div>
</div>

<script type="text/javascript">
	
</script>
