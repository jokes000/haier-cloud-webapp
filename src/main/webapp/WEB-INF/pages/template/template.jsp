<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!-- tiles prefix -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!-- HTML5 definition -->
<!DOCTYPE html>

<html>
<head>
<tiles:insertAttribute name="meta" />
<title><tiles:insertAttribute name="title" /></title>
</head>

<body>
	<!-- header region -->
	<tiles:insertAttribute name="header" />
	<!-- end of header region -->

	<!-- body region -->
	<tiles:insertAttribute name="body" />
	<!-- end of body region -->

	<!-- footer region -->
	<tiles:insertAttribute name="footer" />
	<!-- end of footer region -->

	<!-- javascripts -->
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resource/js/jquery-2.0.3.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resource/js/bootstrap-switch.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resource/js/bootstrap-slider.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resource/js/simple-slider.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resource/js/simplePagination.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
	<!-- end of js -->
</body>
</html>