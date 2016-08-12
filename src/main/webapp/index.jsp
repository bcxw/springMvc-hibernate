<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName=(String)request.getSession().getAttribute("userName");
if(userName==null||userName.isEmpty())response.sendRedirect("login.jsp");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    <link href="favicon.ico" mce_href="favicon.ico" rel="icon" type="image/x-icon" />
    <link href="favicon.ico" mce_href="favicon.ico" rel="bookmark" type="image/x-icon" /> 
	<link href="favicon.ico" mce_href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
    
	<link rel="stylesheet" type="text/css" href="extjs/build/classic/theme-neptune/resources/theme-neptune-all.css">
    
	<script language="javascript" type="text/javascript" src="extjs/build/ext-all.js"></script>
	<script language="javascript" type="text/javascript" src="extjs/build/classic/locale/locale-zh_CN.js"></script>
	<script language="javascript" type="text/javascript" src="locale/zh_CN.js"></script>
	<script language="javascript" type="text/javascript" src="App.js"></script>
  </head>
  
  <body></body>
</html>
