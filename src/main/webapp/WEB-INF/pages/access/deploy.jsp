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
		<form id="deploy_form" method="post" action="${pageContext.request.contextPath}/metadata/deploy/add">
		<div class="controls form-inline">
			<div class="control-group input-append">
				<input type="text" name="database_name" placeholder="实体集名称" data-required="true"/>
			</div>
			<div class="control-group input-append">
				<select name="server_id" data-required="true" >
					<option value="">部署服务器</option>
					<c:forEach items="${server_list}" var="server">
						<option value="${server.id}">${server.s_name}</option>
					</c:forEach>
			</select>
			</div>
			<button class="btn btn-primary">部署</button>
		</div>
		<div class="controls">		
			<table class="table">
				<thead>
					<tr>
						<th>领域实体</th>
						<th></th>
						<th></th>
						<th>选择</th>					
					</tr>
				</thead>				
				<tbody id="deploy_item">				
					<c:forEach items="${tablemeta_list}" var="tablemeta">					
						<tr>
							<td><c:out value="${tablemeta.t_name}"></c:out></td>
							<td></td>
							<td></td>
							<td>								
								<input type="checkbox" name="table" value="${tablemeta.id}" data-required="true"/>								
							</td>							
						</tr>
					</c:forEach>					
				</tbody>				
			</table>
			<div class="holder" align="center"></div>
		</div>
		</form>
	</div>
</div>

<script type="text/javascript">
$('form').validate({
	onKeyup : true,
	eachValidField : function() {

		$(this).closest('div').removeClass('error').addClass('success');
	},
	eachInvalidField : function() {

		$(this).closest('div').removeClass('success').addClass('error');
	}
});
</script>