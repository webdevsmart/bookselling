<%@page import="dao.BookDAOImpl"%>
<%@page import="model.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.NumberFormat"%>
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
	
	String book_id = "";
	if (request.getParameter("id") != null) {
		book_id = request.getParameter("id");
	}

	BookDAOImpl bookDAO = new BookDAOImpl();
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMinimumFractionDigits(0);

	Book book = bookDAO.getBook(Integer.parseInt(book_id));
	
	String pageTitle = book.getTitle();
%>
<jsp:include page="header.jsp" flush="true">
<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>
	
	
<div id="content" style="float:none;">
	<div class="left-1">
		<img src="books/<%=book.getImageFilename()%>" width="200px" height="200px" />
	</div>
	<div class="left-2">
		<table>
			<tr class="row1">
				<td class="col2" colspan="2" style="padding: 10px; color: blue; font-size: 15px; text-transform: uppercase; text-align: center; font-weight: bold">
<%=book.getTitle()%>
				</td>
			</tr>
			<tr class="row2">
				<td class="col1">Author:</td>
				<td class="col2"><%=book.getAuthor()%></td>
			</tr>

			<tr class="row2">
				<td class="col1">Price:</td>
				<td class="col2"><%=nf.format(book.getPrice()) %> USD</td>
			</tr>
			
			<tr class="row2">
				<td class="col1">Description:</td>
				<td class="col2"><%=book.getDescription() %></td>
			</tr>

		</table>
	</div>
	
<% if(username != null) { %>
	<div class="cart-action">
		<a href="#"><img src="images/giohang.png" /></a>
		<form action="GioHangServlet" method="post">
			<input type="number" min="1" value="1" name="amount"/>
			<input type="hidden" value="setCart" name="command"/>
			<input type="hidden" value="<%=book.getId() %>" name="id"/>
			<input type="submit" value="add cart">
		</form>	
	</div>
<%} else { %>
	<div class="cart-action">
		<a href="login.jsp"><img src="images/giohang.png" /></a>
	</div>
<%} %>
<!-- 
	<div class="left-3">
		<article> 
			<input type="checkbox" id="read_more" role="button"> 
			<label for="read_more" onclick="" style="width: 770px; margin-left: 150px; margin-right: auto;">
				<span>View details</span> 
				<span>Closed</span>
			</label> 
			<section>
				<table>
					<tr rowspan="2">
						<td class="detail-1">Detailed Information</td>
						<td class="detail-2">Updating</td>
					</tr>
			
				</table>
			</section> 
		</article>
	</div>
-->

</div>

<jsp:include page="footer.jsp" flush="true"></jsp:include>