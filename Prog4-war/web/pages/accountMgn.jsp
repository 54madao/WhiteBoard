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
<%@page import="com.local.service.UserBean"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
        <title>Share with you</title>
    </head>
    <body style="padding-top:70px" >
        
        <nav class="navbar navbar-inverse navbar-fixed-top">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="#">Share with you</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
              <ul class="nav navbar-nav navbar-right">
                <li><a href="#">Dashboard</a></li>
                <li><a href="#">Settings</a></li>
                <li><a href="#">Profile</a></li>
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
                <li class="active"><a href="#">Overview <span class="sr-only">(current)</span></a></li>
                <li><%out.print(basePath); %></li>
                <li class="active"><a href="<%=basePath%>pages/accountMgn.jsp">Administration<span class="sr-only">(current)</span></a></li>
              </ul>
        
            </div>
            <div class="col-sm-9  col-md-10  main">
              <h1 class="page-header">User Creation</h1>

              <div class="row">
                  <div id="wdcreate" class="form-inline">
                        <div class="form-group">
                            <label for="exampleInputName2">Name</label>
                            <input type="text" name="name" class="form-control" id="username" placeholder="Name">
                        </div>
                     <div class="form-group">
                            <label for="exampleInputEmail2">PASSWORD</label>
                            <input type="text" name="description" class="form-control" id="userpass" placeholder="Descriptions">
                    </div>
                    <button class="btn btn-default" onclick="Create()">Create</button>
                </div>
              </div>

              <h2 class="sub-header">White Board List</h2>
              <div class="table-responsive">
                <table class="table">
                  <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>PASSWORD</th>
                        <th>Operations</th>
                    </tr>
                  </thead>
                  <tbody id="acclist">
                    <!--  <c:forEach items="${list}" var="it">
                          <tr>
                              <td>${it.name}</td>
                              <td>${it.description}</td>
                              <td><a href="<%=basePath%>pages/wbview.jsp?name=${it.name}" target="_blank"><button class="btn btn-xs btn-primary">Open</button></a></td>
                          </tr>
                      </c:forEach>-->
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
                url: '../UserServlet',
                method: 'POST',
                data: {
                    op: 'get'
                }
            }).done(function(msg) {
                //alert(msg);
                $('#acclist').html(msg);
                //alert( "success" );
            }).fail(function() {
                alert( "error" );
            });
        });
        
        function Create(){           
            $.ajax({
                url: '../UserServlet',
                method: 'POST',
                data: {
                    name: $('#username').val(),
                    password: $('#userpass').val(),
                    op: 'add'
                }
            }).done(function(msg) {
                //alert(msg);
                $('#acclist').html(msg);
                //alert( "success" );
            }).fail(function() {
                alert( "error" );
            });
            
        }
        
        function Delete(id){
            $.ajax({
                url: '../UserServlet',
                method: 'POST',
                data: {
                    id: id,
                    op: 'delete'
                }
            }).done(function(msg) {
                //alert(msg);
                $('#acclist').html(msg);
                //alert( "success" );
            }).fail(function() {
                alert( "error" );
            });
        }
        
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
            var pass = tr.find('.pass').val();
//            alert(id);
//            alert(name);
//            alert(des);
            $.ajax({
                url: '../UserServlet',
                method: 'POST',
                data: {
                    id: id,
                    name: name,
                    password: pass,
                    op: 'update'
                }
            }).done(function(msg) {
                //alert(msg);
                $('#acclist').html(msg);
                //alert( "success" );
            }).fail(function() {
                alert( "error" );
            });
        });

    </script>
</html>