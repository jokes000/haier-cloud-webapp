<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isELIgnored="false" %>
<div class="center">
	<h1>Welcome to Cloud Management Platform.</h1>

	<div class="container">
		<div class="row">
			<div class="span12">
				<ul class="thumbnails">
					<li class="span4">
						<div class="thumbnail">
							<a href="<%=request.getContextPath()%>/deploy"><img
								height="300" width="150"
								src="<%=request.getContextPath()%>/resource/img/cluster.jpg" /></a>
							<div class="caption">
								<h3>集群部署</h3>
								<p>目前云平台提供Hadoop、MongoDB、MySQL集群的部署与搭建，详情请点击进入。</p>
							</div>
						</div>
					</li>
					<li class="span4">
						<div class="thumbnail">
							<a href="<%=request.getContextPath()%>/vm"><img height="300"
								width="150" src="<%=request.getContextPath()%>/resource/img/cluster.jpg" /></a>
							<div class="caption">
								<h3>虚拟机管理</h3>
								<p>管理空闲虚拟机：增加新的虚拟机，删除不需要的虚拟机。</p>
							</div>
						</div>
					</li>
					<li class="span4">
						<div class="thumbnail">
							<a href="<%=request.getContextPath()%>/cluster"><img
								height="300" width="150"
								src="<%=request.getContextPath()%>/resource/img/cluster.jpg" /></a>
							<div class="caption">
								<h3>正在运行的集群</h3>
								<p>查看云平台现在正运行哪些集群，查看集群详细信息请进入。</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>