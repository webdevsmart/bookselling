<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=request.getParameter("pageTitle")%></title>
<link rel="stylesheet" href="css/category.css" />
<link rel="stylesheet" href="css/cart.css" />
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/menu.css" />
<link rel="stylesheet" href="css/product.css" />
<link rel="stylesheet" href="css/detail.css" />
</head>

<body>
<div class="wrapper">	
	<header>
		<div id="head">
			<img src="images/banner.png" width="1057px" height="200px" />
		</div>

<%
	String username = null;
	Cookie[] cookies = request.getCookies();
	if(cookies !=null)
	{
		for(Cookie cookie : cookies)
		{
		    if(cookie.getName().equals("username")) 
		    	username = cookie.getValue();
		}
	}
		
	if (username != null) {
%>
		<div id="head-link">
			<div id='main-menu'>
				<ul>
					<li class='logo'><a href="index.jsp"><img src="images/logo.jpg"/></a></li>
					<li class='last'><a href="cart.jsp"><span>Cart</span></a></li>
					<li class='last'><a href="history.jsp"><span>History</span></a></li>
					<li class='last'><a href="search_page.jsp"><span>Search</span></a></li>
					<li class='last' style="float: right;"><a href="LogoutServlet"><span>Logout</span></a></li>
					<li class='last' style="float: right;"><a href="update_user.jsp?username=<%=username %>"><span><%=username%></span></a></li>
				</ul>
			</div>
		</div>
<%
	} else {
%>
		<div id="head-link">
			<div id='main-menu'>
				<ul>
					<li class='logo'><a href="index.jsp"><img src="images/logo.jpg"/></a></li>
					<li class='last'><a href="search_page.jsp"><span>Search</span></a></li>
					<li class='last' style="float: right;"><a href="register.jsp"><span>Register</span></a></li>
					<li class='last' style="float: right;"><a href="login.jsp"><span>Login</span></a></li>
				</ul>
			</div>
		</div>
<%
	}
%>
	</header>