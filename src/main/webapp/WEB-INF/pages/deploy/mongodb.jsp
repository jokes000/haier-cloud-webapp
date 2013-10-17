<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<h1>MongoDB集群部署</h1>
	<hr>

	<p class="text-left">一: 建议初始设置两个shard，方便集群自动扩展</p>
	<p class="text-left">二: 每个shard拥有3个mongod进程</p>
	<p class="text-left">三: 有且仅能有3台config server，可与mongod置于一台vm。</p>
	<p class="text-left">四: mongos为路由请求功能，可与mongod置于一台vm。</p>
	<br>

	<div class="row">
		<form action="<%=request.getContextPath()%>/deploy/mongodb/submit"
			method="POST" onsubmit="return valid()">
			<!-- table start -->
			<table class="table table-hover table-bordered">
				<thead>
					<tr id="tb-header">
						<th width="80px">虚拟机名</th>
						<th width="80px">IP</th>
						<th width="80px">CPU</th>
						<th width="80px">内存</th>
						<th width="80px">硬盘</th>
						<th width="80px">带宽</th>
						<th width="80px">config&nbsp;server</th>
						<th width="80px">mongos</th>
						<th width="80px">shard-1</th>
						<th width="80px">shard-2</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vm" items="${vmList}">
						<tr class="tb-body">
							<td><c:out value="${vm.name}" /></td>
							<td><c:out value="${vm.ip}" /></td>
							<td><c:out value="${vm.cpu}核" /></td>
							<td><c:out value="${vm.memory}G" /></td>
							<td><c:out value="${vm.storage}G" /></td>
							<td><c:out value="${vm.boardWidth}Mbps" /></td>
							<td><div class="make-switch switch-small">
									<input type="checkbox" name="configserver" value="${vm.id}">
								</div></td>
							<td><div class="make-switch switch-small">
									<input type="checkbox" name="mongos" value="${vm.id}" />
								</div></td>
							<td><div class="make-switch switch-small">
									<input type="checkbox" name="shard1" value="${vm.id}" />
								</div></td>
							<td><div class="make-switch switch-small">
									<input type="checkbox" name="shard2" value="${vm.id}" />
								</div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- end of select table -->

			<!-- confirm button -->
			<button class="btn btn-warning pull-right">重置</button>
			<input type="submit" class="btn btn-primary pull-right"
				style="margin-right: 10px" value="确定" />
			<!--  
			<button class="btn btn-info pull-right" style="margin-right: 10px"
				onclick="addShard()">添加Shard</button>
			-->
			<input type="text" class="pull-right" style="margin-right: 10px"
				placeholder="集群名称" name="clusterName" />
		</form>
	</div>
</div>*

<script type="text/javascript">
	var index = 2;
	var vms = ${vmList};
	
	function addShard() {
		$('#tb-header').append("<th width='80px'>shard-" + index + "</th>");
		
		//$('.tb-body')
		//.append("<td><input type='checkbox' name='shard-" + index + "' /></td>");	
		
		$('.tb-body')
				.append("<td><div class='shard-" + index + " make-switch switch-small'>" + 
						"<input type='checkbox' name='shard-" + index + "' />" +
						"</div></td>");		
		$(".shard-" + index + ".make-switch")['bootstrapSwitch']();
		index++;
	}
	
	function valid() {
		return false;
	}
	
</script>