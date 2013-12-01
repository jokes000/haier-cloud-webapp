<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<p id="logo">Cloud Management Platform</p>
			<ul class="nav nav-pills pull-right">
				<!-- 1st li -->
				<shiro:authenticated>
					<li class="dropdown pull-right"><a href="#"
						data-toggle="dropdown" class="dropdown-toggle">帐号管理 <strong
							class="caret"></strong></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/home">我的主页</a></li>
							<li><a href="<%=request.getContextPath()%>/account">个人资料</a></li>
							<li class="divider">
							<li><a href="<%=request.getContextPath()%>/logout">登出</a></li>
						</ul></li>
				</shiro:authenticated>

				<!-- 2nd li -->
				<shiro:hasPermission name="admin:view">
					<li class="dropdown pull-right"><a href="#"
						data-toggle="dropdown" class="dropdown-toggle">系统权限<strong
							class="caret"></strong></a>
						<ul class="dropdown-menu">
							<shiro:hasPermission name="admin:permission:view">
								<li><a
									href="<%=request.getContextPath()%>/admin/permission">权限管理</a></li>
							</shiro:hasPermission>
							<li class="divider" />
							<shiro:hasPermission name="admin:group:view">
								<li><a href="<%=request.getContextPath()%>/admin/group">查看权限组</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="admin:group:add">
								<li><a href="<%=request.getContextPath()%>/admin/group/add">添加权限组</a></li>
							</shiro:hasPermission>
							<li class="divider" />
							<shiro:hasPermission name="admin:user:view">
								<li><a href="<%=request.getContextPath()%>/admin/user">查看所有用户</a></li>
							</shiro:hasPermission>
							<shiro:hasPermission name="admin:user:add">
								<li><a href="<%=request.getContextPath()%>/admin/user/add">添加用户</a></li>
							</shiro:hasPermission>
						</ul></li>
				</shiro:hasPermission>

				<!-- 3rd li -->
				<shiro:hasPermission name="menu:metadata">
					<li><a href="${pageContext.request.contextPath}/metadata"
						data-toggle="dropdown" class="dropdown-toggle">元数据管理<strong
							class="caret"></strong></a></li>
				</shiro:hasPermission>

				<!-- 4th li -->
				<shiro:hasPermission name="menu:cluster">
					<li class="dropdown pull-right"><a href="#"
						data-toggle="dropdown" class="dropdown-toggle">集群管理<strong
							class="caret"></strong></a>
						<ul class="dropdown-menu">
							<li><a href="<%=request.getContextPath()%>/cluster">现有集群</a></li>
							<li class="divider">
							<li><a href="<%=request.getContextPath()%>/deploy">部署集群</a></li>
						</ul></li>
				</shiro:hasPermission>
			</ul>
		</div>
	</div>
</div>