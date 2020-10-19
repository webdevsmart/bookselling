<%@page import="java.util.ArrayList"%>
<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@page import="dao.BookDAOImpl"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

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

String pageTitle = "Search";
%>

<jsp:include page="header.jsp" flush="true">
<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>

<div id="content">
	<div id="left"><jsp:include page="search_menu.jsp"></jsp:include></div>
	<div id="right">
<%
	BookDAOImpl bookDAO = new BookDAOImpl();
	String title = "";
	String author = "";
	
	if (request.getParameter("title") != null && request.getParameter("author")!= null) {
		title = request.getParameter("title");
		author = request.getParameter("author");
	}
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMinimumFractionDigits(0);
%>
<% 
	String err = "";
	if (request.getAttribute("err") != null) {
		err = (String) request.getAttribute("err");
%>
		<h3><%=err %></h3>
<% 
	} 
%>

<%
	List<Book> list = bookDAO.searchList(title, author);
	if (list == null)
		list = new ArrayList<Book>(); 

	if(list.size()==0 && err=="") {
%>
		<h3>No books found</h3>
<% 
	}
%>
		<div id="site-wrapper" style="float: left">
			<ul class="products homepage">
		
<%
	if (title != null || author != null) {
		for (Book book : list) {
%>

				<li class="preorder">
					<span class="tagimg"></span> 
					<a href="detail.jsp?id=<%=book.getId()%>"> 
						<img src="books/<%=book.getImageFilename()%>" width=" 250px" height="250px" />
						<h3><%=book.getTitle()%></h3>
						<h4>Price: <%=nf.format(book.getPrice())%> USD</h4> 
						<p class="info">
							<span>Author: <%=book.getAuthor()%>
							</span> <span>Price: <%=nf.format(book.getPrice())%> $
							</span> <span>Description: <%=book.getDescription()%>
						</p>
					</a>
				</li>

<%
		}
	} else {
%>
				<h3> Enter the search information </h3>
<%
	}
%>
			</ul>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" flush="true"></jsp:include>