<%@page import="java.util.ArrayList"%>
<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="dao.CategoryDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	CategoryDAOImpl categoryDAO = new CategoryDAOImpl();
	List<Category> list = new ArrayList<Category>();
	list = categoryDAO.getList();
	String err="";
%>
<div class="container">
	<nav>
		<ul class="mcd-menu">
			<li>
				<form accept-charset="utf-8" method="post" action="SearchServlet" name="SearchServlet">
	                <p>
		                <label for="title">Title</label>
		                <br>
		                <input accept-charset="utf-8" type="text"  name="title" style="width:97%">
	                </p>
	                <p>
	                	<label for="author">Author</label>
	                	<br>
	                    <input accept-charset="utf-8" type="text"  name="author" style="width:97%">
                    </p>
                    <input type="submit" value="Search" name="search">
               	</form>
             </li>
             <li style="color: red"><%=err%></li>
         </ul>
	</nav>
</div>