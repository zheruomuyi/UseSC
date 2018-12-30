<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regist.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
        
     <form  action = "register.sc" method = "post">
          <br />
          账	  号:
          <input name = "userName" type="text" />
          <br>
          密  码:
           <input name = "password" type="password" />
           <br>
          邮  箱:
           <input name = "userEmail" type="text" />
           <br>
          地  址:
           <input name = "userAddress" type="text" />
           <br>
           年  龄:
           <input name = "userAge" type="text" />
           <br>
           <br>
          <input type="submit"  value="注册" name="register"/> 
             <br>  <br>
     </form>
  
          <br>
          
 <br>
  </body>
</html>
