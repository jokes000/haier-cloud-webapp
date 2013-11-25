<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="span9">
	<div class="control-group">
		<div class="controls">
			<table class="table">
				<thead>
					<tr>
						<th>领域实体</th>
						<th></th>
						<th></th>
						<th>操作</th>					
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tablemeta_list}" var="tablemeta">
						<tr>

							<td><c:out value="${tablemeta.t_name}"></c:out></td>
							<td><input type="hidden" value="${tablemeta.id}" /></td>
							<td></td>
							<td>
								<button class="btn btn-primary btn-small" name="deploy_btn"
									data-target="#deploy_panel" data-toggle="modal">部署</button>
							</td>							
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>

<div class="modal hide fade" id="deploy_panel">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h3>部署</h3>
	</div>
	<form:form method="post" action="/haiercloud-webapp/metadata/deploy/add" modelAttribute="tableMeta">
	<div class="modal-body">
		<fieldset>
			<div class="control-group">
				<label class="control-label">选择服务器</label>
				<div class="controls">
					<select name="server">
						<option value="mysql">mysql:localhost</option>
						<option value="mongo">mongo:192.168.1.251</option>
					</select>
				</div>
			</div>
		<form:hidden path="t_name" id="tname"/>
		<form:hidden path="id" id="tid"/>
		</fieldset>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		<button class="btn btn-primary">部署</button>
	</div>
	</form:form>
</div>

<script type="text/javascript">
$("button[name='deploy_btn']").click(function(){
	var $td = $(this).parents("tr").children();
	var tid = $td.eq(1).children().val();
	var tname = $td.eq(0).text();
	$("#tname").val(tname);
	$("#tid").val(tid);
});
</script>