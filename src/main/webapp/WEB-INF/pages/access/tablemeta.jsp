<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function() {
		$("div.holder").jPages({
			containerID : "table_item",
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
			<button class="btn btn-primary btn-small"
				data-target="#add_table_panel" data-toggle="modal">新建实体</button>
		</div>
		<div class="controls">
			<table class="table" id="column_table">
				<thead>
					<tr>
						<th>领域实体</th>
						<th></th>
						<th></th>
						<th></th>
						<th></th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="table_item">
					<c:forEach items="${tablemeta_list}" var="tablemeta">
						<tr>
							<td><c:out value="${tablemeta.t_name}"></c:out></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td><form:form
									action="${pageContext.request.contextPath}/metadata/columnmeta"
									class="pull-left" method="post" modelAttribute="tableMeta">
									<form:hidden path="id" value="${tablemeta.id }" />
									<button class="btn btn-primary btn-small">编辑</button>
								</form:form> <form:form class="pull-left" method="post"
									action="${pageContext.request.contextPath}/metadata/tablemeta/del" modelAttribute="tableMeta">
									<button class="btn btn-danger btn-small">删除</button>
									<form:hidden path="id" value="${tablemeta.id}" />
								</form:form></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="holder" align="center"></div>
		</div>
	</div>
</div>

<div class="modal hide fade" id="add_table_panel">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h3>新建实体</h3>
	</div>
	<form:form method="post" action="${pageContext.request.contextPath}/metadata/tablemeta/create"
		modelAttribute="tableMeta">
		<div class="modal-body">
			<fieldset>
				<div class="control-group">
					<label class="control-label">实体名</label>
					<div class="control-group input-append">
						<form:input path="t_name" data-required="true" />
					</div>
				</div>
			</fieldset>
		</div>
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
			<button class="btn btn-primary">保存</button>
		</div>
	</form:form>
</div>

<style type="text/css">
form {
	margin: 0px 5px 0px;
}
</style>

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