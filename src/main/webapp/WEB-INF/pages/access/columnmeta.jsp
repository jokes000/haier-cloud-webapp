<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	$(function(){
		$("div.holder").jPages({
			containerID : "column_item",
			previous : "上一页",
			next : "下一页",
			perPage : 10,
			delay : 10
		});
	});
</script>
<div class="span9">
	<div class="control-group">
		<form:form method="post"
			action="${pageContext.request.contextPath}/metadata/columnmeta/edittable"
			modelAttribute="tableMeta">
			<div class="controls form-inline">
				<form:hidden path="id" value="${table.id }" />
				<div class="control-group input-append">
					<form:input path="t_name" value="${table.t_name }" placeholder="元模型名" data-required="true"/>
				</div>
				<button class="btn btn-primary">更新</button>
			</div>
		</form:form>
		<div class="controls">
			<button class="btn btn-primary btn-small" data-target="#add_column_panel" 
					data-toggle="modal">添加列</button>
		</div>
		<div class="controls">
			<table class="table" id="column_table">
				<thead>
					<tr>
						<th>列名</th>
						<th>类型</th>
						<th>长度</th>
						<th>关联</th>
						<th>级联</th>
						<th>NOT NULL</th>
						<th>操作</th>
						<th></th>
					</tr>
				</thead>
				<tbody id="column_item">
					<c:forEach items="${columnmeta_list}" var="columnmeta"
						varStatus="status">					
						<tr>
							<form:form method="post"
							action="${pageContext.request.contextPath}/metadata/columnmeta/del"
							modelAttribute="columnMeta">
								<input type="hidden" name="tid" value="${table.id }" />
								<form:hidden path="c_name" value="${columnmeta.c_name}" />
								<form:hidden path="type" value="${columnmeta.type}"/>
								<form:hidden path="length" value="${columnmeta.length}" />
								<form:hidden path="cascade" value="${columnmeta.cascade }" />
								<form:hidden path="notnull" value="${columnmeta.notnull }" />
								<td>								
									${columnmeta.c_name}
								</td>
								<td>								
									${columnmeta.type}
								</td>
								<td>									
									${columnmeta.length}
								</td>
								<td><c:forEach items="${tablemeta_list}" var="tablemeta">
										<c:choose>
											<c:when test="${tablemeta.t_name==fk_list[status.index]}">
												<input name="fk" type="hidden" value="${tablemeta.id}">
												${fk_list[status.index]}
											</c:when>
											<c:otherwise>
												<input name="fk" type="hidden" value="">
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</td>
								<td>								
									${columnmeta.cascade }
								</td>
								<td>
									${columnmeta.notnull }
								</td>
								<td>
									<button name="edit_column" class="btn btn-primary btn-small"
										data-target="#edit_column_panel" data-toggle="modal">编辑</button>
									<button class="btn btn-danger btn-small">删除</button>
								</td>
								<td>
									<form:hidden path="id" value="${columnmeta.id}" />
								</td>
							</form:form>
						</tr>						
					</c:forEach>
				</tbody>
			</table>
			<div class="holder" align="center"></div>
		</div>
	</div>
</div>

<div class="modal hide fade" id="add_column_panel">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h3>添加列</h3>
	</div>
	<form:form method="post" action="${pageContext.request.contextPath}/metadata/columnmeta/new" modelAttribute="columnMeta">
	<div class="modal-body">
		<fieldset>
			<input type="hidden" name="tid" value='${table.id }' />		
			<div class="control-group">
				<label class="control-label">列名</label>
				<div class="control-group input-append">
						<form:input path="c_name" data-required="true"/>
					</div>
			</div>
			<div class="control-group">
				<label class="control-label">类型</label>
				<div class="controls">
					<form:select path="type">
						<option value="INT">INTEGER</option>
						<option value="BIGINT">BIGINT</option>
						<option value="VARCHAR">VARCHAR</option>
						<option value="NUMERIC">NUMERIC</option>
						<option value="TIMESTAMP">TIMESTAMP</option>
						<option value="DATE">DATE</option>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">长度</label>
				<div class="controls">
					<form:input path="length" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">关联</label>
				<div class="controls">
					<select name="fk">
						<option value="">
							无
						</option>
						<c:forEach items="${tablemeta_list}" var="tablemeta">
							<c:choose>
								<c:when test="${tablemeta.id==table.id}">								
								</c:when>
								<c:otherwise>
									<option value="${tablemeta.id}">
										<c:out value="${tablemeta.t_name}"></c:out>
									</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>	
			<div class="control-group">
				<label class="control-label">级联</label>
					<div class="controls">
						<form:select path="cascade">
							<option value="">无</option>
							<option value="ALL">ALL</option>
							<option value="UPDATE">UPDATE</option>
							<option value="DELETE">DELETE</option>
						</form:select>
					</div>
			</div>	
			<div class="control-group">
					<label class="control-label">NOT NULL</label>
					<div class="controls">
						<form:checkbox path="notnull"/>
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

<div class="modal hide fade" id="edit_column_panel">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h3>编辑列</h3>
	</div>
	<form:form method="post" action="${pageContext.request.contextPath}/metadata/columnmeta/edit" modelAttribute="columnMeta">
		<div class="modal-body">
			<fieldset>
				<input type="hidden" name="tid" value='${table.id }' />
				<form:hidden path="id" id="cid" />
				<div class="control-group">
					<label class="control-label">列名</label>
					<div class="control-group input-append">
						<form:input path="c_name" id="cname" data-required="true"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">类型</label>
					<div class="controls">
						<form:select path="type" id="ctype">
							<option value="INT">INT</option>
							<option value="BIGINT">BIGINT</option>
							<option value="VARCHAR">VARCHAR</option>
							<option value="NUMERIC">NUMERIC</option>
							<option value="TIMESTAMP">TIMESTAMP</option>
							<option value="DATE">DATE</option>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">长度</label>
					<div class="controls">
						<form:input path="length" id="clength" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">关联</label>
					<div class="controls">
						<select id="fk" name="fk">
							<option value="">无</option>
							<c:forEach items="${tablemeta_list}" var="tablemeta">
								<c:choose>
									<c:when test="${tablemeta.id==table.id}">
									</c:when>
									<c:otherwise>
										<option value="${tablemeta.id}">
											<c:out value="${tablemeta.t_name}"></c:out>
										</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">级联</label>
					<div class="controls">
						<form:select path="cascade" id="ccascade">
							<option value="">无</option>
							<option value="ALL">ALL</option>
							<option value="UPDATE">UPDATE</option>
							<option value="DELETE">DELETE</option>
						</form:select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">NOT NULL</label>
					<div class="controls">
						<form:checkbox path="notnull" id="cnotnull"/>
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

<script type="text/javascript">
$("button[name='edit_column']").click(function(){
	var $td = $(this).parents("tr").children('td');
	var cid = $td.eq(7).children().val().trim();
	var cname = $td.eq(0).text().trim();
	var type = $td.eq(1).text().trim();
	var length = $td.eq(2).text().trim();
	var fktid = $td.eq(3).children().val().trim();
	var cascade = $td.eq(4).text().trim();
	var notnull = $td.eq(5).text().trim();
	$("#cid").val(cid); 
	$("#cname").val(cname); 
	$("#ctype").val(type); 
	$("#clength").val(length); 
	$("#fk").val(fktid); 
	$("#ccascade").val(cascade);
	if(notnull=="true")
		$("#cnotnull").attr("checked", true);
	else
		$("#cnotnull").attr("checked", false);
});

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