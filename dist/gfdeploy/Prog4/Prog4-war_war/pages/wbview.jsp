<%-- 
    Document   : wbview
    Created on : Dec 10, 2015, 1:59:00 AM
    Author     : wenbog
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>White Board</title>
    </head>
    <body>
        <h1><% out.print(request.getParameter("name")); %> </h1>
    </body>
</html>
