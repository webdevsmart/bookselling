<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/login.css" rel='stylesheet' type='text/css' />
<title>Register</title>
</head>
<body>
	<%
		String err = "";
		if (request.getAttribute("err") != null) {
			err = (String) request.getAttribute("err");
		}
	%>
	<!--/start-login-two-->
	<div class="login-02">
		<div class="two-login  hvr-float-shadow">
			<div class="two-login-head">
				<img src="images/top-note.png" alt="" />
				<h2>Register</h2>
				<lable></lable>
			</div>
			<form action="RegisterServlet" method="post">
				<li style="color: red"><%=err%></li>
				User name
				<li><input type="text" class="text" value=""
					name="username"><a href="#" class=" icon2 user2"></a></li>
				Password	
				<li><input type="password" value=""
					name="password"><a href="#" class=" icon2 lock2"></a></li>
				Birthday
				<li><input type="date" value=""
					name="ngaysinh"><a href="#" class=" icon2 lock2"></a></li>
				Sex
				<li><input type="text" value="" list="exampleList"
					name="gioitinh"> 
					<datalist id="exampleList">
					<option value="male">
					<option value="female">
					</datalist><a href="#" class=" icon2 lock2"></a></li>
				Email
				<li><input type="text" value=""
					name="email"><a href="#" class=" icon2 lock2"></a></li>
				Phone number
				<li><input type="text" value=""				
					name="sdt"><a href="#" class=" icon2 lock2"></a></li>
				Address
				<li><input type="text" value=""
					name="diachi"><a href="#" class=" icon2 lock2"></a></li>
					
				<div class="p-container">
					<label class="checkbox two"><input type="checkbox"
						name="checkbox" checked><i></i>I agree <a href="#">Terms of service</a></label>
				</div>
				<div class="submit two">
					<input type="submit" value="Register">
				</div>
				<h5>
					<a href="login.jsp">Login</a>
				</h5>
			</form>
		</div>
	</div>
</body>
</html>