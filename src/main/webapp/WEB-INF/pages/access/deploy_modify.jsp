<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function(){
		$("#holder_deployed").jPages({
			containerID : "deployed_item",
			previous : "上一页",
			next : "下一页",
			perPage : 3,
			delay : 10
		});
		$("#holder_undeployed").jPages({
			containerID : "undeployed_item",
			previous : "上一页",
			next : "下一页",
			perPage : 3,
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
						<th>已部署实体</th>			
					</tr>
				</thead>
				<tbody id="deployed_item">
					<c:forEach items="${deployed_tablemeta}" var="Dtablemeta">
						<tr>
							<td><c:out value="${Dtablemeta.t_name}"></c:out></td>						
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div id="holder_deployed" class="holder" align="center"></div>
		</div>
		<form method="post" action="${pageContext.request.contextPath}/metadata/deploy/change">
		<div class="controls  form-inline">
			<input type="hidden" name="database_name" value="${dbname }" />
			<input type="hidden" name="server" value="${server.id }" />
			<input type="hidden" name="deploy" value="${deploy.id }" />
			<button class="btn btn-primary btn-small">添加实体</button>
		</div>
		<div class="controls">		
			<table class="table">
				<thead>
					<tr>
						<th>未部署实体</th>
						<th></th>
						<th></th>
						<th>选择</th>					
					</tr>
				</thead>
				<tbody id="undeployed_item">
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
			<div id="holder_undeployed" class="holder" align="center"></div>
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