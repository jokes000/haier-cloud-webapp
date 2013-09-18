<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<p id="logo">Cloud Management Platform</p>
			<ul class="nav nav-pills pull-right">
				<li><a href="<%=request.getContextPath()%>">首页</a></li>
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