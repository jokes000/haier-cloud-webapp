<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<p id="logo">Cloud Management Platform</p>
			<ul class="nav nav-pills pull-right">
				<li><a href="<%=request.getContextPath()%>/logout">退出</a></li>
				<li><a href="<%=request.getContextPath()%>/home">首页</a></li>
				<li><a href="<%=request.getContextPath()%>/account">个人信息管理</a></li>
				<shiro:hasPermission name="admin:view">
					<li class="dropdown pull-right"><a href="#"
						data-toggle="dropdown" class="dropdown-toggle">系统安全<strong
							class="caret"></strong></a>
						<ul class="dropdown-menu">
							<shiro:hasPermission name="admin:permission:view">
								<li><a
									href="<%=request.getContextPath()%>/admin/permission">权限管理</a></li>
							</shiro:hasPermission>
							<li class="divider" /><shiro:hasPermission
									name="admin:group:view">
									<li><a href="<%=request.getContextPath()%>/admin/group">查看权限组</a></li>
									</shiro:hasPermission>
				<shiro:hasPermission name="admin:group:add">
					<li><a href="<%=request.getContextPath()%>/admin/group/add">添加权限组</a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="admin:user:view">
					<li><a href="<%=request.getContextPath()%>/admin/user">查看所有用户</a></li>
				</shiro:hasPermission>
				<shiro:hasPermission name="admin:user:add">
					<li><a href="<%=request.getContextPath()%>/admin/user/add">添加用户</a></li>
				</shiro:hasPermission>
				<li><a href="<%=request.getContextPath()%>/cluster">现有集群管理</a></li>
						
						</ul></li>
				</shiro:hasPermission>
				<li><a href="<%=request.getContextPath()%>/vm">虚拟机管理</a></li>
				<li class="dropdown pull-right"><a href="#"
					data-toggle="dropdown" class="dropdown-toggle">集群管理<strong
						class="caret"></strong></a>
					<ul class="dropdown-menu">
						<li><a href="<%=request.getContextPath()%>/cluster">现有集群管理</a></li>
						<li class="divider">
						
						<li><a href="<%=request.getContextPath()%>/deploy">部署新集群</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</div>