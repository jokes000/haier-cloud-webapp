<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- meta info -->
<meta name="author" content="smile">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- end of meta info -->


<!-- styles -->
<link href="<%=request.getContextPath()%>/resource/css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resource/css/bootstrap-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="<%=request.getContextPath()%>/resource/css/bootstrap-switch.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resource/css/simple-slider.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resource/css/simple-slider-volume.css" rel="stylesheet"
	type="text/css" />
<link href="<%=request.getContextPath()%>/resource/css/simplePagination.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath()%>/resource/css/jPages.css" rel="stylesheet" type="text/css" />
<!-- self defined -->
<link href="<%=request.getContextPath()%>/resource/css/custom.css" rel="stylesheet" type="text/css" />
<!-- Le styles -->
<style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) { /* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
<style type="text/css">
object,embed {
	-webkit-animation-duration: .001s;
	-webkit-animation-name: playerInserted;
	-ms-animation-duration: .001s;
	-ms-animation-name: playerInserted;
	-o-animation-duration: .001s;
	-o-animation-name: playerInserted;
	animation-duration: .001s;
	animation-name: playerInserted;
}

@
-webkit-keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}

}
@
-ms-keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}

}
@
-o-keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}

}
@
keyframes playerInserted {
	from {opacity: 0.99;
}

to {
	opacity: 1;
}
}
</style>
<!-- end of styles -->

<!-- javascripts -->
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/bootstrap-switch.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/bootstrap-slider.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/simple-slider.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/simplePagination.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery-validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resource/js/jquery.meta.js"></script>
<script src="<%=request.getContextPath()%>/resource/js/jPages.js"></script>
<!-- end of js -->


<!-- Add IE html5 support -->
<!--[if it IE 9]>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->