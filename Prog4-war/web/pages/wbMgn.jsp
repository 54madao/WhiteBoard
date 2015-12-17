<%-- 
    Document   : wbview
    Created on : Dec 10, 2015, 1:59:00 AM
    Author     : wenbog
--%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String userName;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.local.service.WhiteBoardBean"%>
<!DOCTYPE html>
<html>
    <head>
        <script>
	// haven't logged in
	if(!window.localStorage.getItem("userName")){
            document.location.href = '<%=basePath%>pages/login.jsp';
	}
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
        <!-- Custom styles for this template -->
        <link href="http://getbootstrap.com/examples/dashboard/dashboard.css" rel="stylesheet">
        <title>Share with you</title>
    </head>
    <body>
        
        <nav class="navbar navbar-inverse navbar-fixed-top">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#"></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Settings</a></li>
                <li><a href="#">Profile</a></li>
                <li><a href="<%=basePath%>pages/login.jsp">Log Out</a></li>
              </ul>
              <form class="navbar-form navbar-right">
                <input type="text" class="form-control" placeholder="Search...">
              </form>
            </div>
          </div>
        </nav>
        
        <div class="container-fluid">
          <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
              <ul class="nav nav-sidebar">
                  <li><a href="<%=basePath%>pages/wbMgn.jsp" class="active">Overview</a></li>
                <li><a href="<%=basePath%>pages/home.jsp">Home Page</a></li>
                <li><a href="<%=basePath%>pages/accountMgn.jsp">Administration</a></li>
              </ul>
        
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

              <h2 class="sub-header">White Board List</h2>
              <div class="table-responsive">
                <table class="table">
                  <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Owner</th>
                        <th>Operations</th>
                    </tr>
                  </thead>
                  <tbody id="wblist">
                      <c:forEach items="${list}" var="it">
                          <tr>
                              <td>${it.name}</td>
                              <td>${it.description}</td>
                              <td><a href="<%=basePath%>pages/wbview.jsp?name=${it.name}" target="_blank"><button class="btn btn-xs btn-primary">Open</button></a></td>
                          </tr>
                      </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script>
        $(document).ready(function(){
            $('.navbar-brand').text(window.localStorage.getItem('userName'));
            $.ajax({
                url: '../WhiteBoardServlet',
                method: 'POST',
                data: {
                    op: 'get',
                    user: window.localStorage.getItem('userName'),
                    personal: 'overview'
                }
            }).done(function(msg) {
                //alert(msg);
                $('#wblist').html(msg);
                //alert( "success" );
            }).fail(function() {
                alert( "error" );
            });
        });
        
        
        
//        function Create(){
//            event.preventDefault();
//            $.ajax({
//                url: '../WhiteBoardServlet',
//                method: 'POST',
//                data: $('#wbform').serialize()
//            }).done(function(msg) {
//                //alert(msg);
//                $('#wblist').html(msg);
//                //alert( "success" );
//            }).fail(function() {
//                alert( "error" );
//            });
//            
//        }
        
        function Delete(id){
            $.ajax({
                url: '../WhiteBoardServlet',
                method: 'POST',
                data: {
                    id: id,
                    op: 'delete',
                    user: window.localStorage.getItem('userName'),
                    personal: 'overview'
                }
            }).done(function(msg) {
                //alert(msg);
                $('#wblist').html(msg);
                //alert( "success" );
            }).fail(function() {
                alert( "error" );
            });
        }
        
//        function Subscribe(id){
//            $.ajax({
//                url: '../WhiteBoardServlet',
//                method: 'POST',
//                data: {
//                    id: id,
//                    user: window.localStorage.getItem('userName'),
//                    op: 'subscribe',
//                    personal: 'overview'
//                }
//            }).done(function(msg) {
//                //alert(msg);
//                $('#wblist').html(msg);
//                //alert( "success" );
//            }).fail(function() {
//                alert( "error" );
//            });
//        }
        //function Update(id){
            //alert($(this).closest("tr").html());
            //alert(tr.length);
//            for(var i = 0; i < tr.length; i++){
//                alert(tr[i]);
//            }
//            $.ajax({
//                url: 'WhiteBoardServlet',
//                method: 'POST',
//                data: {
//                    id: id,
//                    name: name,
//                    description: des,
//                    op: 'update'
//                }
//            }).done(function(msg) {
//                //alert(msg);
//                $('#wblist').html(msg);
//                //alert( "success" );
//            }).fail(function() {
//                alert( "error" );
//            });
        //}
 
        $(document).on('click', '.update', function(){
            var tr = $(this).closest("tr").children();
            var id = tr.eq(0).text();
            var name = tr.find('.name').val();
            var des = tr.find('.des').val();
            var owner = tr.find('.owner').text();
//            alert(id);
//            alert(name);
//            alert(des);
            $.ajax({
                url: '../WhiteBoardServlet',
                method: 'POST',
                data: {
                    id: id,
                    name: name,
                    description: des,
                    user: window.localStorage.getItem('userName'),
                    op: 'update',
                    personal: 'overview'
                }
            }).done(function(msg) {
                //alert(msg);
                $('#wblist').html(msg);
                //alert( "success" );
            }).fail(function() {
                alert( "error" );
            });
        });

//    window.onbeforeunload = function () {   
//        window.localStorage.removeItem("userName");
//    };
        // Logout function
	$(document).on('click', "[href='<%=basePath%>pages/login.jsp']",function(){
            window.localStorage.removeItem("userName");
	});
    </script>
</html>
