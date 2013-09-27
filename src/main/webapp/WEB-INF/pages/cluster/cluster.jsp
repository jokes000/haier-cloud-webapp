<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- tiles prefix -->
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<div class="container">
	<h3 class="text-left">正在运行的集群</h3>
	<hr>

	<div class="row">
		<div class="span12">
			<ul class="thumbnails">
				<c:forEach var="item" items="${clusterList}">
					<li class="span4">
						<div class="thumbnail">
							<a href="<%=request.getContextPath()%>/${item.name}_status"><img
								width="300" height="180"
								src="<%=request.getContextPath()%>/img/${item.meta.name}.jpg" /></a>
							<div class="caption">
								<h3>${item.name}</h3>
								<p>${item.meta.name }集群，目前有${fn:length(item.vms) }台机器。</p>
								<c:choose>
									<c:when test="${item.status eq 2}">
										<p class="text-warning">
											<c:out value="状态：正在部署" />
										</p>
									</c:when>
									<c:when test="${item.status eq 1}">
										<p class="text-success">
											<c:out value="状态：正常运行" />
										</p>
									</c:when>
									<c:otherwise>
										<p class="text-error">
											<c:out value="状态：部署失败" />
										</p>
									</c:otherwise>
								</c:choose>
								<a href="<%=request.getContextPath()%>/mock_monitoring"><button
										class="btn btn-primary" disabled="disabled">监控</button></a> <a
									href="<%=request.getContextPath()%>/cluster/modify/${item.id}"><button
										class="btn btn-primary">修改</button></a>
							</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="row">
		<div class="span6 center">
			<!-- pagination -->
			<div class="pagination-holder clearfix">
				<ul id="light-pagination" class="pagination" style="float: center"></ul>
			</div>
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
