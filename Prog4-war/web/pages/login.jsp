<%-- 
    Document   : login
    Created on : Dec 15, 2015, 11:28:10 PM
    Author     : wenbog
--%>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.local.service.WhiteBoardBean"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
        <!-- Custom styles for this template -->
        <link href="http://getbootstrap.com/examples/signin/signin.css" rel="stylesheet">
        <title>Share with you</title>
    </head>
    <body>
        <div class="container form-signin">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Sign in</a></li>
                <li role="presentation"><a href="#profile" aria-controls="profile" role="tab" data-toggle="tab">Sign up</a></li>
            </ul>
            
            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade in active" id="home">
                    <form class="form-signin" action="../UserServlet" method="post" id="signin">
                        <label for="inputEmail" class="sr-only">Email address</label>
                        <input type="email" name="name" id="signinname" class="form-control" placeholder="Email address" required autofocus>
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" name="password" id="signinpsd" class="form-control" placeholder="Password" required>
                        <input type="hidden" name="op" id="signinop" class="form-control" value="check">
                        <!--<div class="checkbox">
                          <label>
                            <input type="checkbox" value="remember-me"> Remember me
                          </label>
                        </div>-->
                        <button class="btn btn-lg btn-primary btn-block" onclick="Check()">Sign in</button>
                    </form>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="profile">
                    <form class="form-signin" id="signup">
                        <label for="inputEmail" class="sr-only">Email address</label>
                        <input type="email" name="name" id="username" class="form-control" placeholder="Email address" required autofocus>
                        <label for="inputPassword" class="sr-only">Password</label>
                        <input type="password" name="password" id="userpass" class="form-control" placeholder="Password" required>
                        <input type="hidden" name="op" id="op" class="form-control" value="add">
                        <!--<div class="checkbox">
                          <label>
                            <input type="checkbox" value="remember-me"> Remember me
                          </label>
                        </div>-->
                        <button class="btn btn-lg btn-primary btn-block" onclick="Create()">Sign up</button>
                    </form>
                </div>
            </div>
        </div> <!-- /container -->
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script>
        function Create(){
            event.preventDefault();
            $.ajax({
                url: '../UserServlet',
                method: 'POST',
                data: $('#signup').serialize()
            }).done(function(msg) {
                //alert(msg);
                //$('#acclist').html(msg);
                //alert( "success" );
                alert( "success" );
            }).fail(function() {
                alert( "error" );
            });    
        }
        
        function Check(){
            event.preventDefault();
            $.ajax({
                url: '../UserServlet',
                method: 'POST',
                data: $('#signin').serialize()
            }).done(function(msg) {
                //alert(msg);
                //$('#acclist').html(msg);           
                if(msg == 'True'){
                    window.localStorage.setItem("userName", $('#signinname').val());
                    document.location.href = '<%=basePath%>pages/wbMgn.jsp'
                }
                else{
                    alert( msg );
                }
            }).fail(function() {
                alert( "error" );
            });    
        }
    </script>
</html>
