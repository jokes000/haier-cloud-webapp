<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false" %>
<!-- JSTL prefix -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- tiles prefix -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!-- HTML5 definition -->
<!DOCTYPE html>

<html>
<head>
    <tiles:insertAttribute name="meta" />
</head>
<body>
    <tiles:insertAttribute name="navi" />

    <div class="container-fluid">
      <div class="row-fluid">
      	<tiles:insertAttribute name="side" />
        <tiles:insertAttribute name="body" /> 	
      </div>
    </div>
    <tiles:insertAttribute name="footer" />
</body>
</html>