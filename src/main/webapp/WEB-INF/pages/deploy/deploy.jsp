<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="center">
	<h1>部署集群</h1>

	<div class="container">
		<div class="row">
			<div class="span12">
				<ul class="thumbnails">
					<li class="span4">
						<div class="thumbnail">
							<a href="<%=request.getContextPath()%>/deploy/hadoop"><img
								alt="300x200" src="<%=request.getContextPath()%>/resource/img/hadoop.jpg" /></a>
							<div class="caption">
								<h3>Hadoop</h3>
								<p>部署存储海量数据并对其进行分析的Hadoop平台。</p>
							</div>
						</div>
					</li>
					<li class="span4">
						<div class="thumbnail">
							<a href="<%=request.getContextPath()%>/deploy/mongodb"><img
								alt="300x200"
								src="<%=request.getContextPath()%>/resource/img/mongodb.jpg" /></a>
							<div class="caption">
								<h3>MongoDB</h3>
								<p>部署一个海量数据库MongoDB。</p>
							</div>
						</div>
					</li>
					<li class="span4">
						<div class="thumbnail">
							<a href="<%=request.getContextPath()%>/deploy/mysql"><img
								alt="300x200" src="<%=request.getContextPath()%>/resource/img/mysql.jpg" /></a>
							<div class="caption">
								<h3>MySQL</h3>
								<p>部署一个关系性数据库MySQL集群。</p>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>

