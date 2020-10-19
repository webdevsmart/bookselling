<%@page import="java.util.ArrayList"%>
<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@page import="dao.BookDAOImpl"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
String pageTitle = "Homepage";
%>
<jsp:include page="header.jsp" flush="true">
<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>

<div id="content">
	<div id="left"><jsp:include page="category.jsp"></jsp:include></div>
	<div id="right">
		<h1>Book Store For All</h1>
		<%
			BookDAOImpl bookDAO = new BookDAOImpl();
			List<Book> list = new ArrayList<Book>();
			list = bookDAO.getList();
			NumberFormat nf = NumberFormat.getInstance();
			nf.setMinimumFractionDigits(0);
		%>
		<div id="site-wrapper" style="float: left">
			<ul class="products homepage">
		<%
			for (Book book : bookDAO.getList()) {
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
		%>
			</ul>
		</div>
	</div>
</div>
		
<jsp:include page="footer.jsp" flush="true"></jsp:include>
