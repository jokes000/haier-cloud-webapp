<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>	
	<div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">元数据管理</li>
              <li><a href="<%=request.getContextPath()%>/metadata/tablemeta">领域实体管理</a></li>
              <li class="nav-header">数据存储部署</li>
              <li><a href="<%=request.getContextPath()%>/metadata/deploy">领域实体部署</a></li>
              <li><a href="<%=request.getContextPath()%>/metadata/deploy/info">部署状态查看</a></li>
              <li><a href="<%=request.getContextPath()%>/metadata/deploy/manage">实体集管理</a></li>
              <li class="nav-header">数据存储访问</li>
              <li><a href="#">MySQL</a></li>
              <li><a href="#">MongoDB</a></li>
              <li><a href="#">Hadoop</a></li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->