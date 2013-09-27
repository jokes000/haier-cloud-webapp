<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<div class="center">
		<h3 class="text-left">Hadoop集群部署</h3>
		<hr>

		<p class="text-left">一: 只能拥有1台NameNode并与JobTracker单独占用一台虚拟机。</p>
		<p class="text-left">二:
			SecondaryNameNode为NameNode的备份节点，消耗少，可与其他进程共享一台虚拟机。</p>
		<p class="text-left">三: DataNode为数据存储节点，必须与TaskTracker共享一台虚拟机。</p>

		<div class="row">
			<div class="span12">
				<form action="<%=request.getContextPath()%>/deploy/hadoop/submit"
					method="POST" onsubmit="return valid()">
					<!-- table start -->
					<table class="table table-hover table-bordered">
						<thead>
							<tr>
								<th width="100px">虚拟机名</th>
								<th width="100px">IP</th>
								<th width="80px">CPU</th>
								<th width="80px">内存</th>
								<th width="80px">硬盘</th>
								<th width="80px">带宽</th>
								<th width="80px">NameNode</th>
								<th width="80px">SecondaryNameNode</th>
								<th width="80px">DataNode</th>
								<th width="80px">JobTracker</th>
								<th width="80px">TaskTracker</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="vm" items="${vmList}">
								<tr>
									<td><c:out value="${vm.name}" /></td>
									<td><c:out value="${vm.ip}" /></td>
									<td><c:out value="${vm.cpu}核" /></td>
									<td><c:out value="${vm.memory}G" /></td>
									<td><c:out value="${vm.storage}G" /></td>
									<td><c:out value="${vm.boardWidth}Mbps" /></td>
									<td><div
											class="make-switch switch-small radio1 radio-no-uncheck">
											<input type="radio" name="namenode" value="${vm.id}">
										</div></td>
									<td><div class="make-switch switch-small">
											<input type="checkbox" name="snnList" value="${vm.id}" />
										</div></td>
									<td><div class="make-switch switch-small">
											<input type="checkbox" name="dnList" value="${vm.id}" />
										</div></td>
									<td><div
											class="make-switch switch-small radio1 radio-no-uncheck">
											<input type="radio" name="jobtracker" value="${vm.id}" />
										</div></td>
									<td><div class="make-switch switch-small">
											<input type="checkbox" name="ttList" value="${vm.id}" />
										</div></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- end of select table -->

					<!-- confirm button -->
					<button class="btn btn-warning pull-right">重置</button>
					<input type="submit" class="btn btn-primary pull-right"
						style="margin-right: 10px" value="确定" /> <input type="text"
						class="pull-right" style="margin-right: 10px" placeholder="集群名称"
						name="clusterName" />
				</form>
			</div>
		</div>
	</div>
</div>

<script>
	$('.radio1').on('switch-change', function() {
		$('.radio1').bootstrapSwitch('toggleRadioState');
	});
</script>