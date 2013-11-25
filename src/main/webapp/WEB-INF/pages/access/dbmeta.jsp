<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="span9">
	<form:form method="post"
		action="/haiercloud-webapp/metadata/tablemeta"
		modelAttribute="databaseMeta">
		<div class="control-group">
			<div class="controls">
				<form:select path="id" >
					<form:option value="">
						---------请选择数据库---------
					</form:option>
					<c:forEach items="${dbmeta_list}" var="dbmeta">					
						<form:option value="${dbmeta.id}">					
							<c:out value="${dbmeta.db_name}"></c:out>						
						</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="controls">
				<button class="btn">查询</button>
			</div>
		</div>
	</form:form>

</div>
