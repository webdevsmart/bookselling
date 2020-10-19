<%@page import="dao.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.UserDAOImpl"%>
<%@page import="model.User"%>
<%@page import="dao.UserDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/login.css" rel='stylesheet' type='text/css' />
<title>Update User</title>
</head>
<body>
	<%
		String err = "";
		if (request.getAttribute("err") != null) {
			err = (String) request.getAttribute("err");
		}
		String username= request.getParameter("username");
		UserDAOImpl userDAO = new UserDAOImpl();
		User u= userDAO.getUser(username);
	%>

	<!--/start-login-two-->
	<div class="login-02">
		<div class="two-login  hvr-float-shadow">
			<div class="two-login-head">
				<img src="images/top-note.png" alt="" />
				<h2>Update Information</h2>
				<lable></lable>
			</div>
			<form action="UpdateUser" method="post">
				<li style="color: red"><%=err%></li>
				User name
				<li><input type="text" class="text"
					value="<%=u.getUsername()%>" readonly name="username"><a
					href="#" class=" icon2 user2"></a></li>
				Password
				<li><input type="password" value="" 
					name="password"><a href="#" class=" icon2 lock2"></a></li>
				Birthday
				<li><input type="date" value="<%=u.getBirthday().toString()%>"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '<%=u.getBirthday() %>';}"
					name="birthday"><a href="#" class=" icon2 lock2"></a></li>
				Sex
				<li><input type="text" value="<%=u.getSex() %>"
					list="exampleList" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '<%=u.getSex() %>';}"
					name="sex"> <datalist id="exampleList">
					<option value="male">
					<option value="female">
					</datalist><a href="#" class=" icon2 lock2"></a></li>
				Email
				<li><input type="text" value="<%= u.getEmail() %>" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '<%=u.getEmail() %>';}" name="email"><a
					href="#" class=" icon2 lock2"></a></li>
				Phone number
				<li><input type="text" value="<%=u.getPhoneNumber() %>"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '<%=u.getPhoneNumber() %>';}" name="phone_number"><a
					href="#" class=" icon2 lock2"></a></li>
				Address
				<li><input type="text" value="<%=u.getAddress() %>"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = '<%=u.getAddress() %>';}"
					name="address"><a href="#" class=" icon2 lock2"></a></li>
				
				<div class="submit two">
					<input type="submit" value="Update">
					<input type="hidden" value="<%=u.getUser_id()%>" name="id">
				</div>
				<h5>
					<a href="index.jsp">Return</a>
				</h5>
			</form>
		</div>
	</div>
</body>
</html>