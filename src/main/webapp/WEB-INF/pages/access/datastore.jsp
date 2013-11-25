<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="span9">
	<form:form method="post" action="/haiercloud-webapp/metadata//datastore/create"
		modelAttribute="tableMeta">
		<input type="hidden" name="column_list" value="[" />
		<input type="hidden" name="fk_table" value="" />
		<input type="hidden" name="fk_table_id" value="" />
		<input type="hidden" name="fk_column" value="" />
		<div class="control-group">
			<div class="controls">
				<form:input path="t_name" placeholder="领域实体名" />
			</div>
			<div class="controls">
				<button class="btn btn-primary" data-target="#add_column_panel" 
					data-toggle="modal">添加列</button>
				<button type="submit" class="btn btn-primary">保存</button>
				<p />
			</div>
			<div class="controls">
				<table class="table" id="column_table">
					<thead>
						<tr>
							<th>列名</th>
							<th>类型</th>
							<th>长度</th>
							<th>关联</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>			
		</div>
	</form:form>
</div>

<div class="modal hide fade" id="add_column_panel">
	<div class="modal-header">
		<button type="button" class="close" data-dismiss="modal"
			aria-hidden="true">&times;</button>
		<h3>添加列</h3>
	</div>
	<div class="modal-body">
		<fieldset>
			<div class="control-group">
				<label class="control-label">列名</label>
				<div class="controls">
					<input type="text" name="c_name">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">类型</label>
				<div class="controls">
					<select name="type">
						<option value="INT">INTEGER</option>
						<option value="BIGINT">BIGINT</option>
						<option value="VARCHAR">VARCHAR</option>
						<option value="NUMBER">NUMBER</option>
						<option value="TIMESTAMP">TIMESTAMP</option>
						<option value="DATE">DATE</option>
					</select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">长度</label>
				<div class="controls">
					<input type="text" name="length">
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
							<option value="${tablemeta.id}">
								<c:out value="${tablemeta.t_name}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</fieldset>
	</div>
	<div class="modal-footer">
		<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
		<button class="btn btn-primary" id="add_column">保存</button>
	</div>
</div>

<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/addColumn.js"
	charset="gbk"></script>