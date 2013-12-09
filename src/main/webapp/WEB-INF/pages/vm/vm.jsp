<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<div class="row">
		<div class="span12">
			<h3 class="text-left">
				现有虚拟机 <a href="<%=request.getContextPath()%>/vm/add"
					class="btn btn-primary pull-right"><i
					class="icon-white icon-plus"></i>&nbsp;申请虚拟机</a>
				<button class="btn btn-primary pull-right"
					style="margin-right: 10px" disabled="disabled">空闲虚拟机</button>
			</h3>
			<hr>

			<!-- vm table -->
			<table class="table table-hover table-bordered">
				<thead>
					<tr>
						<th width="100px">虚拟机名</th>
						<th width="100px">IP</th>
						<th width="80px">CPU</th>
						<th width="80px">CPU空闲率</th>
						<th width="80px">内存</th>
						<th width="80px">剩余内存</th>
						<th width="80px">硬盘</th>
						<th width="80px">空磁盘空间</th>
						<th width="80px">带宽</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="vm" items="${vmList}">
						<tr>
							<td><c:out value="${vm.name}" /></td>
							<td><c:out value="${vm.ip}" /></td>
							<td><c:out value="${vm.cpu}核" /></td>
							<td>
								<c:choose>
								<c:when test="${vm.cpu_idle.status!='OK' }">
									<b id="red"><c:out value="${vm.cpu_idle.value}" /></b>
								</c:when>
								<c:otherwise>
									<b><c:out value="${vm.cpu_idle.value}" /></b>
								</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:out value="${vm.memory}G" />
							</td>
							<td>
								<c:choose>
								<c:when test="${vm.mem_free.status!='OK' }">
									<b id="red"><c:out value="${vm.mem_free.value}" /></b>
								</c:when>
								<c:otherwise>
									<b><c:out value="${vm.mem_free.value}" /></b>
								</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:out value="${vm.storage}G" />
							</td>
							<td>
								<c:choose>
								<c:when test="${vm.disk_free.status!='OK' }">
									<b id="red"><c:out value="${vm.disk_free.value}" /></b>
								</c:when>
								<c:otherwise>
									<b><c:out value="${vm.disk_free.value}" /></b>
								</c:otherwise>
								</c:choose>
							</td>
							<td><c:out value="${vm.boardWidth}Mbps" />
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- end of vm table -->

			<!-- pagination -->
			<div class="row">
				<div class="span6 center">
					<!-- pagination -->
					<div class="pagination-holder clearfix">
						<ul id="light-pagination" class="pagination" style="float: center"></ul>
					</div>
				</div>
			</div>
			<!-- end of pagination -->
		</div>
	</div>
</div>

<script type="text/javascript">
	$(function() {
		$('#light-pagination').pagination({
			items : '${total}',
			itemsOnPage : '${pageSize}',
			cssStyle : 'light-theme'
		});
	});

	$(function() {
		$('#light-pagination').pagination('selectPage', '${page}');
	});
</script>