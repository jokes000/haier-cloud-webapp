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
						<th>部署状态</th>
						<th></th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="deploy_item">
					<c:forEach items="${deploy_list}" var="deploy">
						<tr>
							<td>${deploy.databaseMeta.db_name}</td>
							<td>${deploy.status}</td>
							<td>
								<input type="hidden" value="${deploy.msg}" />
							</td>
							<td>
								<button class="btn btn-primary btn-small" name="deploy_info"
									data-target="#deploy_info_panel" data-toggle="modal">查看详情</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="holder" align="center"></div>
		</div>
	</div>
</div>

<div class="modal hide fade" id="deploy_info_panel">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h3>部署详情</h3>
	</div>
	<div class="modal-body">
		<p id="msg" />
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
	</div>
</div>

<script type="text/javascript">
$("button[name='deploy_info']").click(function(){
	var $td = $(this).parents("tr").children();
	var msg = $td.eq(2).children().val();
	$("#msg").text(msg);
});
</script>


