<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
    
        <form  action = "login.sc" method = "post">
         <br />
          账	  号:
          <input name = "userName" type="text" />
          <br>
          密  码:
           <input name = "password" type="password" />
           <br><br>
          <input type="submit"  value="登录" name="login"> 
             <br>  <br>
     </form>
     <br>
     		如果想要查您的账户信息，请  <a href="/UseSC/show.html"><input type="button" value = "查询"></a> <br>
          	如果您想要注销您的账户，请  <a href="/UseSC/success_view.html"><input type="button" value = "注销"></a> <br>
               如果您还不是我们的用户，请   <a href="/UseSC/regist.jsp"><input type="button" value = "注册"></a>
     
  </body>
</html>
