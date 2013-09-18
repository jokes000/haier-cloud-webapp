<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- tiles prefix -->
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<div class="row">
	<aside class="span3">
		<ul class="nav nav-list" id="navlist">
			<li class="nav-header">用户管理</li>
			<li><a href="<%=request.getContextPath()%>/admin/user">用户列表</a></li>
			<li><a href="<%=request.getContextPath()%>/admin/user/add">添加用户</a></li>
			<li class="nav-header">群组管理</li>
			<li><a href="<%=request.getContextPath()%>/admin/group">群组列表</a></li>
			<li><a href="<%=request.getContextPath()%>/admin/group/add">添加群组</a></li>
			<li class="nav-header">权限管理</li>
			<li><a href="<%=request.getContextPath()%>/admin/permission">权限列表</a></li>
			<li><a href="<%=request.getContextPath()%>/admin/permission/add">添加权限</a></li>
		</ul>
	</aside>

	<div class="center">
		<h3>管理员首页</h3>
	</div>
</div>

<script type="text/javascript">
	$('#navlist li').click(function() {
		$('#navlist li').removeClass('active');
		$(this).addClass('active');
	});
</script>