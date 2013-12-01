<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function(){
		$("div.holder").jPages({
			containerID : "deploy_item",
			previous : "上一页",
			next : "下一页",
			perPage : 10,
			delay : 10
		});
	});
</script>
<div class="span9">
	<div class="control-group">
		<div class="controls">
			<table class="table">
				<thead>
					<tr>
						<th>实体集</th>
						<th>服务器</th>
						<th></th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="deploy_item">
					<c:forEach items="${deploy_list}" var="deploy">	
					<form:form method="post" action="/haiercloud-webapp/metadata/deploy/manage/modify"
						modelAttribute="deploy">				
						<tr>						
							<td>${deploy.databaseMeta.db_name}</td>
							<td>${deploy.server.s_name}</td>
							<td>
								<form:hidden path="id" value="${deploy.id}" />
							</td>
							<td>
								<button class="btn btn-primary btn-small">修改</button>
							</td>						
						</tr>	
						</form:form>				
					</c:forEach>
				</tbody>
			</table>
			<div class="holder" align="center"></div>
		</div>
	</div>
</div>
