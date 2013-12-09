<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="container">
	<div class="row">
		<div class="span12">
			<div class="page-header">
				<h1>
					MongoDB集群： <small><c:out value='${cluster.name}' /></small>
				</h1>
			</div>
			<div class="tabbable" id="tabs-127708">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#panel-configserver"
						data-toggle="tab">configserver</a></li>
					<li><a href="#panel-mongos" data-toggle="tab">mongos</a></li>
					<li><a href="#panel-shard" data-toggle="tab">shard</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-configserver">
						<table class="table table-bordered" contenteditable="true">
							<thead>
								<tr>
									<th width="100px">虚拟机名</th>
									<th width="100px">IP</th>
									<th width="80px">CPU</th>
									<th width="80px">CPU空闲率</th>
									<th width="80px">内存</th>
									<th width="80px">剩余内存</th>
									<th width="80px">硬盘</th>
									<th width="100px">剩余磁盘空间</th>
									<th width="80px">带宽</th>
									<th width="80px">状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${configserver}">
									<tr>
										<td><c:out value="${item.name}" /></td>
										<td><c:out value="${item.ip}" /></td>
										<td><c:out value="${item.cpu}核" /></td>
										<td>
											<c:choose>
												<c:when test="${item.cpu_idle.status!='OK' }">
													<b id="red"><c:out value="${item.cpu_idle.value}" /></b>
												</c:when>
												<c:otherwise>
													<b><c:out value="${item.cpu_idle.value}" /></b>
												</c:otherwise>
											</c:choose>
										</td>
										<td><c:out value="${item.memory}G" /></td>
										<td>
											<c:choose>
												<c:when test="${item.mem_free.status!='OK' }">
													<b id="red"><c:out value="${item.mem_free.value}" /></b>
												</c:when>
												<c:otherwise>
													<b><c:out value="${item.mem_free.value}" /></b>
												</c:otherwise>
											</c:choose>
										</td>
										<td><c:out value="${item.storage}G" /></td>
										<td>
											<c:choose>
												<c:when test="${item.disk_free.status!='OK' }">
													<b id="red"><c:out value="${item.disk_free.value}" /></b>
												</c:when>
												<c:otherwise>
													<b><c:out value="${item.disk_free.value}" /></b>
												</c:otherwise>
											</c:choose>
										</td>
										<td><c:out value="${item.boardWidth}Mbps" /></td>
										<td>
											<c:choose>
												<c:when test="${item.status!='OK'}">
													<b id="red"><c:out value="${jt.status}" /></b>
												</c:when>
												<c:otherwise>
													<b>OK</b>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="tab-pane" id="panel-mongos">
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
									<th width="100px">剩余磁盘空间</th>
									<th width="80px">带宽</th>
									<th width="80px">状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${mongos}">
									<tr>
										<td><c:out value="${item.name}" /></td>
										<td><c:out value="${item.ip}" /></td>
										<td><c:out value="${item.cpu}核" /></td>
										<td>
											<c:choose>
												<c:when test="${item.cpu_idle.status!='OK' }">
													<b id="red"><c:out value="${item.cpu_idle.value}" /></b>
												</c:when>
												<c:otherwise>
													<b><c:out value="${item.cpu_idle.value}" /></b>
												</c:otherwise>
											</c:choose>
										</td>
										<td><c:out value="${item.memory}G" /></td>
										<td>
											<c:choose>
												<c:when test="${item.mem_free.status!='OK' }">
													<b id="red"><c:out value="${item.mem_free.value}" /></b>
												</c:when>
												<c:otherwise>
													<b><c:out value="${item.mem_free.value}" /></b>
												</c:otherwise>
											</c:choose>
										</td>
										<td><c:out value="${item.storage}G" /></td>
										<td>
											<c:choose>
												<c:when test="${item.disk_free.status!='OK' }">
													<b id="red"><c:out value="${item.disk_free.value}" /></b>
												</c:when>
												<c:otherwise>
													<b><c:out value="${item.disk_free.value}" /></b>
												</c:otherwise>
											</c:choose>
										</td>
										<td><c:out value="${item.boardWidth}Mbps" /></td>
										<td>
											<c:choose>
												<c:when test="${item.status!='OK'}">
													<b id="red"><c:out value="${item.status}" /></b>
												</c:when>
												<c:otherwise>
													<b>OK</b>
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="tab-pane" id="panel-shard">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th width="100px">ShardName</th>
									<th width="100px">虚拟机名</th>
									<th width="100px">IP</th>
									<th width="60px">CPU</th>
									<th width="80px">CPU空闲率</th>
									<th width="60px">内存</th>
									<th width="80px">剩余内存</th>
									<th width="60px">硬盘</th>
									<th width="100px">剩余磁盘空间</th>
									<th width="60px">带宽</th>
									<th width="80px">状态</th>
									<th width="100px">
										<a id="modal-add-shard"
											href="#modal-container-164567" role="button"
											class="btn btn-primary btn-small" data-toggle="modal"><i
												class="icon-white icon-plus"></i>添加shard</a>
									</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${shards}">
									<tr>
										<td colspan="1" rowspan="${item.getValue().size()}"><c:out value="${item.getKey()}" /></td>
										<td><c:out value="${item.getValue().get(0).name}" /></td>
										<td><c:out value="${item.getValue().get(0).ip}" /></td>
										<td><c:out value="${item.getValue().get(0).cpu}" /></td>
										<td>
											<c:choose>
												<c:when test="${item.getValue().get(0).cpu_idle.status!='OK' }">
													<b id="red"><c:out value="${item.getValue().get(0).cpu_idle.value}" /></b>
												</c:when>
												<c:otherwise>
													<b><c:out value="${item.getValue().get(0).cpu_idle.value}" /></b>
												</c:otherwise>
											</c:choose>
										</td>
										<td><c:out value="${item.getValue().get(0).memory}" /></td>
										<td>
											<c:choose>
												<c:when test="${item.getValue().get(0).mem_free.status!='OK' }">
													<b id="red"><c:out value="${item.getValue().get(0).mem_free.value}" /></b>
												</c:when>
												<c:otherwise>
													<b><c:out value="${item.getValue().get(0).mem_free.value}" /></b>
												</c:otherwise>
											</c:choose>
										</td>
										<td><c:out value="${item.getValue().get(0).storage}" /></td>
										<td>
											<c:choose>
												<c:when test="${item.getValue().get(0).disk_free.status!='OK' }">
													<b id="red"><c:out value="${item.getValue().get(0).disk_free.value}" /></b>
												</c:when>
												<c:otherwise>
													<b><c:out value="${item.getValue().get(0).disk_free.value}" /></b>
												</c:otherwise>
											</c:choose>
										</td>
										<td><c:out value="${item.getValue().get(0).boardWidth}" /></td>
										<td>
											<c:choose>
												<c:when test="${item.getValue().get(0).status!='OK'}">
													<b id="red"><c:out value="${item.getValue().get(0).status}" /></b>
												</c:when>
												<c:otherwise>
													<b>OK</b>
												</c:otherwise>
											</c:choose>
										</td>
										<td colspan="1" rowspan="${item.getValue().size()}">
											<a href="#modal-container-164568" role="button"
											class="btn btn-danger btn-small modal-del-shard" data-name="${item.getKey()}" data-toggle="modal"><i
												class="icon-white icon-minus"></i>删除shard</a>
										</td>
									
									</tr>
									<c:forEach begin="1" end="${item.getValue().size()-1}" varStatus="loop">
										<tr>
											<td><c:out value="${item.getValue().get(loop.index).name}" /></td>
											<td><c:out value="${item.getValue().get(loop.index).ip}" /></td>
											<td><c:out value="${item.getValue().get(loop.index).cpu}" /></td>
											<td>
												<c:choose>
													<c:when test="${item.getValue().get(loop.index).cpu_idle.status!='OK' }">
														<b id="red"><c:out value="${item.getValue().get(loop.index).cpu_idle.value}" /></b>
													</c:when>
													<c:otherwise>
														<b><c:out value="${item.getValue().get(loop.index).cpu_idle.value}" /></b>
													</c:otherwise>
												</c:choose>
											</td>
											<td><c:out value="${item.getValue().get(loop.index).memory}" /></td>
											<td>
												<c:choose>
													<c:when test="${item.getValue().get(loop.index).mem_free.status!='OK' }">
														<b id="red"><c:out value="${item.getValue().get(loop.index).mem_free.value}" /></b>
													</c:when>
													<c:otherwise>
														<b><c:out value="${item.getValue().get(loop.index).mem_free.value}" /></b>
													</c:otherwise>
												</c:choose>
											</td>
											<td><c:out value="${item.getValue().get(loop.index).storage}" /></td>
											<td>
												<c:choose>
													<c:when test="${item.getValue().get(loop.index).disk_free.status!='OK' }">
														<b id="red"><c:out value="${item.getValue().get(loop.index).disk_free.value}" /></b>
													</c:when>
													<c:otherwise>
														<b><c:out value="${item.getValue().get(loop.index).disk_free.value}" /></b>
													</c:otherwise>
												</c:choose>
											</td>
											<td><c:out value="${item.getValue().get(loop.index).boardWidth}" /></td>
											<td>
												<c:choose>
													<c:when test="${item.getValue().get(loop.index).status!='OK'}">
														<b id="red"><c:out value="${item.getValue().get(loop.index).status}" /></b>
													</c:when>
													<c:otherwise>
														<b>OK</b>
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</c:forEach>
								</c:forEach>
							</tbody>
						</table>
						
						<div id="modal-container-164567" class="modal hide fade"
							role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">×</button>
								<h3 id="myModalLabel">选择虚拟机</h3>
							</div>
							<div class="modal-body">
								<table class="table table-hover table-bordered" id="vm-list">
									<thead                                                                                                             >
										<tr>
											<th width="100px">虚拟机名</th>
											<th width="100px">IP</th>
											<th width="80px">CPU</th>
											<th width="80px">内存</th>
											<th width="80px">硬盘</th>
											<th width="80px">带宽</th>
											<th width="80px"></th>
										</tr>
									</thead>
									<tbody>
										
									</tbody>
								</table>
							</div>
							<div class="modal-footer">
								<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
								<button class="btn btn-primary" data-dismiss="modal"  aria-hidden="true" id="modal-add-shard-submit">确定</button>
							</div>
						</div>
						
						<div id="modal-container-164568" class="modal hide fade"
							role="dialog" aria-labelledby="myModalLable" aria-hidden="true">
							<div class="modal-footer">
								<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
								<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true" id="modal-del-shard-submit">确定</button>
							</div>
						</div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
  	
	/* 点击添加节点按钮后，动态查询剩余虚拟机，并显示。 */
	$('#modal-add-shard').click(function() {
		jQuery.ajax({
			type : 'GET',
			contentType : 'application/json',
			url : '<%=request.getContextPath()%>/vm/list',
			dataType : 'json',
			success : function(data) {
				if (data && data.success == "true") {
					$('#vm-list tbody').html('');
					var rows = $('<tbody></tbody>');
					$.each(data.data, function(n, value) {
							var tr = $('<tr></tr>');
							var name = $('<td></td>').append(value.name);
							var ip = $('<td></td>').append(value.ip);
							var cpu = $('<td></td>').append(value.cpu + '核');
							var memory = $('<td></td>').append(value.memory + 'G');
							var storage = $('<td></td>').append(value.storage + 'G');
							var boardWidth = $('<td></td>').append(value.boardWidth + 'Mbps');
							var checked = $("<td id='td-checked'></td>").append("<input id='checkbox' type='checkbox' name='checked' value='" + value.id + "' />");
							tr.append(name).append(ip).append(cpu).append(memory).append(storage).append(boardWidth).append(checked).appendTo(rows);								
					});
					$('#vm-list tbody').html(rows.html());
				}
			},
			error : function() {
				alert("error");
			}
		});
	});
	
	/* 选择虚拟机后，点击确定，调用后台代码开始部署。 */
	$('#modal-add-shard-submit').click(function() {
		var vmArray = new Array();
		$("#vm-list tbody tr").each(function() {
			if($(this).children("#td-checked").children("#checkbox").prop("checked")) {
				var vm = $(this).children("#td-checked").children("#checkbox").val();
				vmArray.push(vm);
			}
		});
		
		$.post('<%=request.getContextPath()%>' + '/deploy/mongodb/'  + '${cluster.id}' + '/add', {
			'vms' : vmArray,
			'mongosIds' : $.parseJSON('${mongosIds}'),
			'shardNum' : '${fn:length(shards)}'
		});
	});
	
	$('.radio1').on('switch-change', function() {
		$('.radio1').bootstrapSwitch('toggleRadioState');
	});
	
	 var shardName;

	$('.modal-del-shard').click(function(){
		shardName=$(this).data('name');
		
	});
	
	$('#modal-del-shard-submit').click(function() {
		$.post('<%=request.getContextPath()%>' + '/deploy/mongodb/'  + '${cluster.id}' + '/del',{
			'mongosIds' : $.parseJSON('${mongosIds}'),
			'shardName' : shardName
		});
	});
	

</script>
