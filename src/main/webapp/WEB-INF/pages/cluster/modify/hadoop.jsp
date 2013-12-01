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
					Hadoop集群： <small><c:out value='${cluster.name}' /></small>
				</h1>
			</div>
			<div class="tabbable" id="tabs-127708">
				<ul class="nav nav-tabs">
					<li class="active"><a href="#panel-nn" data-toggle="tab">NameNode</a></li>
					<li><a href="#panel-snn" data-toggle="tab">SecondaryNameNode</a>
					</li>
					<li><a href="#panel-jt" data-toggle="tab">JobTracker</a></li>
					<li><a href="#panel-dn" data-toggle="tab">DataNode&amp;TaskTracker</a></li>
				</ul>
				<div class="tab-content">
					<div class="tab-pane active" id="panel-nn">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th width="100px">虚拟机名</th>
									<th width="100px">IP</th>
									<th width="80px">CPU</th>
									<th width="80px">内存</th>
									<th width="80px">硬盘</th>
									<th width="80px">带宽</th>
									<th width="80px">状态</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><c:out value="${nn.name}" /></td>
									<td><c:out value="${nn.ip}" /></td>
									<td><c:out value="${nn.cpu}核" /></td>
									<td><c:out value="${nn.memory}G" /></td>
									<td><c:out value="${nn.storage}G" /></td>
									<td><c:out value="${nn.boardWidth}Mbps" /></td>
									<td><c:choose>
											<c:when test="${nn.status eq 2}">
												<p class="text-warning">
													<c:out value="状态：正在部署" />
												</p>
											</c:when>
											<c:when test="${nn.status eq 1}">
												<p class="text-success">
													<c:out value="状态：正常运行" />
												</p>
											</c:when>
											<c:when test="${nn.status eq 3}">
												<p class="muted">
													<c:out value="状态：正在删除" />
												</p>
											</c:when>
											<c:when test="${nn.status eq 4}">
												<p class="muted">
													<c:out value="状态：正在添加" />
												</p>
											</c:when>
											<c:otherwise>
												<p class="text-error">
													<c:out value="状态：部署失败" />
												</p>
											</c:otherwise>
										</c:choose></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="tab-pane" id="panel-snn">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th width="100px">虚拟机名</th>
									<th width="100px">IP</th>
									<th width="80px">CPU</th>
									<th width="80px">内存</th>
									<th width="80px">硬盘</th>
									<th width="80px">带宽</th>
									<th width="80px">状态</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${snn}">
									<tr>
										<td><c:out value="${item.name}" /></td>
										<td><c:out value="${item.ip}" /></td>
										<td><c:out value="${item.cpu}核" /></td>
										<td><c:out value="${item.memory}G" /></td>
										<td><c:out value="${item.storage}G" /></td>
										<td><c:out value="${item.boardWidth}Mbps" /></td>
										<td><c:choose>
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
												<c:when test="${item.status eq 3}">
													<p class="muted">
														<c:out value="状态：正在删除" />
													</p>
												</c:when>
												<c:when test="${item.status eq 4}">
													<p class="muted">
														<c:out value="状态：正在添加" />
													</p>
												</c:when>
												<c:otherwise>
													<p class="text-error">
														<c:out value="状态：部署失败" />
													</p>
												</c:otherwise>
											</c:choose></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="tab-pane" id="panel-jt">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th width="100px">虚拟机名</th>
									<th width="100px">IP</th>
									<th width="80px">CPU</th>
									<th width="80px">内存</th>
									<th width="80px">硬盘</th>
									<th width="80px">带宽</th>
									<th width="80px">状态</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><c:out value="${jt.name}" /></td>
									<td><c:out value="${jt.ip}" /></td>
									<td><c:out value="${jt.cpu}核" /></td>
									<td><c:out value="${jt.memory}G" /></td>
									<td><c:out value="${jt.storage}G" /></td>
									<td><c:out value="${jt.boardWidth}Mbps" /></td>
									<td><c:choose>
											<c:when test="${jt.status eq 2}">
												<p class="text-warning">
													<c:out value="状态：正在部署" />
												</p>
											</c:when>
											<c:when test="${jt.status eq 1}">
												<p class="text-success">
													<c:out value="状态：正常运行" />
												</p>
											</c:when>
											<c:when test="${jt.status eq 3}">
												<p class="muted">
													<c:out value="状态：正在删除" />
												</p>
											</c:when>
											<c:when test="${jt.status eq 4}">
												<p class="muted">
													<c:out value="状态：正在添加" />
												</p>
											</c:when>
											<c:otherwise>
												<p class="text-error">
													<c:out value="状态：部署失败" />
												</p>
											</c:otherwise>
										</c:choose></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="tab-pane" id="panel-dn">
						<table class="table table-hover table-bordered">
							<thead>
								<tr>
									<th width="100px">虚拟机名</th>
									<th width="100px">IP</th>
									<th width="80px">CPU</th>
									<th width="80px">内存</th>
									<th width="80px">硬盘</th>
									<th width="80px">带宽</th>
									<th width="80px">状态</th>
									<th width="80px"><a id="modal-add-dn"
										href="#modal-container-164567" role="button"
										class="btn btn-primary btn-small" data-toggle="modal"><i
											class="icon-white icon-plus"></i>添加新节点</a></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach begin="0" end="${dn.size()-1}" varStatus="loop">
									<tr> 
										<td><c:out value="${dn.get(loop.index).name}" /></td>
										<td><c:out value="${dn.get(loop.index).ip}" /></td>
										<td><c:out value="${dn.get(loop.index).cpu}核" /></td>
										<td><c:out value="${dn.get(loop.index).memory}G" /></td>
										<td><c:out value="${dn.get(loop.index).storage}G" /></td>
										<td><c:out value="${dn.get(loop.index).boardWidth}Mbps" />
										<td><c:choose>
												<c:when test="${dn.get(loop.index).status eq 2}">
													<p class="text-warning">
														<c:out value="状态：正在部署" />
													</p>
												</c:when>
												<c:when test="${dn.get(loop.index).status eq 1}">
													<p class="text-success">
														<c:out value="状态：正常运行" />
													</p>
												</c:when>
												<c:when test="${dn.get(loop.index).status eq 3}">
													<p class="muted">
														<c:out value="状态：正在删除" />
													</p>
												</c:when>
												<c:when test="${dn.get(loop.index).status eq 4}">
													<p class="muted">
														<c:out value="状态：正在添加" />
													</p>
												</c:when>
												<c:otherwise>
													<p class="text-error">
														<c:out value="状态：部署失败" />
													</p>
												</c:otherwise>
											</c:choose></td>
										<td>
										<a
										href="#modal-container-164568" role="button"
										class="btn btn-danger btn-small modal-del-dn" data-ip="${dn.get(loop.index).ip}" data-id="${dnIds.get(loop.index)}" data-toggle="modal"><i
											class="icon-white icon-minus"></i>删除该节点</a>
										</td>
									</tr>
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
								<button class="btn btn-primary" data-dismiss="modal"  aria-hidden="true" id="modal-add-dn-submit">确定</button>
							</div>
						</div>
						
						<div id="modal-container-164568" class="modal hide fade"
							role="dialog" aria-labelledby="myModalLable" aria-hidden="true">
							<div class="modal-footer">
								<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
								<button class="btn btn-primary" data-dismiss="modal" aria-hidden="true" id="modal-del-dn-submit">确定</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	var nodeIP;
	var nodeId;
	
	/* 点击添加节点按钮后，动态查询剩余虚拟机，并显示。 */
	$('#modal-add-dn').click(function() {
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
	$('#modal-add-dn-submit').click(function() {
		var vmArray = new Array();
		$("#vm-list tbody tr").each(function() {
			if($(this).children("#td-checked").children("#checkbox").prop("checked")) {
				var vm = $(this).children("#td-checked").children("#checkbox").val();
				vmArray.push(vm);
			}
		});
		
		$.post('<%=request.getContextPath()%>' + '/deploy/hadoop/'  + '${cluster.id}' + '/add', {
			'vms[]' : vmArray,
			'namenodeIP' : '${nn.ip}'
		});
	});
	
	$('.radio1').on('switch-change', function() {
		$('.radio1').bootstrapSwitch('toggleRadioState');
	});
	
	$('.modal-del-dn').click(function(){
		nodeIP=$(this).data('ip');
		nodeId=$(this).data('id');
	});
	
	$('#modal-del-dn-submit').click(function() {
		$.post('<%=request.getContextPath()%>' + '/deploy/hadoop/'  + '${cluster.id}' + '/del',{
			'namenodeIP' : '${nn.ip}',
			'nodeId' : nodeId,
			'nodeIP' : nodeIP
		});
	});

</script>
