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
		<h3 class="text-left">用户管理</h3>
		<hr>

		<table class="table table-hover">
			<thead>
				<tr>
					<th>用户名</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${usersList}">
					<tr>
						<td><c:out value="${user.username}" /></td>
						<td><a
							href="<%=request.getContextPath()%>/admin/user/${user.id}"
							class="
							btn btn-primary btn-small">查看用户所有权限</a></td>
						<td><a
							href="<%=request.getContextPath()%>/admin/user/delete/${user.id}"
							class="btn btn-danger btn-small">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
	
</script>
