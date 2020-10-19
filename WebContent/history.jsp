<%@page import="model.History"%>
<%@page import="dao.HistoryDAOImpl"%>
<%@page import="model.Cart"%>
<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@page import="dao.BookDAOImpl"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dao.UserDAOImpl"%>
<%@page import="model.User"%>
<%@page import="dao.UserDAO"%>
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

String pageTitle = "History";
%>
<jsp:include page="header.jsp" flush="true">
<jsp:param name="pageTitle" value="<%=pageTitle%>" />
</jsp:include>

<div id="content" style="float:none;">
	
	<div class="shopping-cart">

		<div class="column-labels">
			<label class="product-image">Book</label> 
			<label class="product-details">Details</label> 
			<label class="product-price">Price</label> 
			<label class="product-quantity">Amount</label>
			<label class="product-line-price">Total money</label>
		</div>
<%
	BookDAOImpl bookDAO = new BookDAOImpl();
	HistoryDAOImpl historyDAO = new HistoryDAOImpl();
	UserDAOImpl userDAO = new UserDAOImpl();
	User u= userDAO.getUser(username);
	
	List<History> L= historyDAO.getList(u.getUser_id());
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMinimumIntegerDigits(0);
%>
<%
	if (L != null) {
		for (History h : L) {
			Book book = bookDAO.getBook(h.getBookId());
%>
		<div class="product">
			<div class="product-image">
				<img src="books/<%=book.getImageFilename()%>">
			</div>
			<div class="product-details">
				<div class="product-title">
					<%=book.getTitle()%>
				</div>
				<p class="product-description">
					<%=book.getDescription()%>
				</p>
			</div>
			<div class="product-price">
				<%=nf.format(book.getPrice())%> USD
			</div>
			<div class="product-quantity cart_quantity_button">
				<%=h.getAmount() %>
			</div>
			<div class="product-line-price" style="text-align: right">
				<%=nf.format(book.getPrice() * h.getAmount())%> USD
			</div>
		</div>
<%
		}
	}
%>
	
	</div>
</div>

<jsp:include page="footer.jsp" flush="true"></jsp:include>