<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>首页</title>
<!--
<link rel="stylesheet" type="text/css" href="styles.css">
-->
</head>
<body>
	<a href="<%=basePath%>manager/desktop/desktop/desktop.html">桌面</a>
	<a href="<%=basePath%>manager/desktop/desktop/desktop.html">桌面</a>
</body>
</html>
