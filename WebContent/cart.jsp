<%@page import="model.Cart"%>
<%@page import="model.Book"%>
<%@page import="java.util.List"%>
<%@page import="dao.BookDAOImpl"%>
<%@page import="java.util.ArrayList"%>
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

String pageTitle = "Cart";
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
	NumberFormat nf = NumberFormat.getInstance();
	nf.setMinimumIntegerDigits(0);
	double total = 0;
	ArrayList<Cart> cart=null;
	
	if (session.getAttribute("cart")!=null) {
		cart = (ArrayList<Cart>) session.getAttribute("cart");
	}
%>
<%
	if (cart != null) {
		for (Cart c : cart) {
			Book book = bookDAO.getBook(c.getBook().getId());
			total = total + (c.getQuantity() * book.getPrice());
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
				<a class="cart_quantity_up" href="GioHangServlet?command=deleteCart&id=<%=book.getId()%>"> - </a>
				<input class="cart_quantity_input" type="number" value="<%=c.getQuantity()%>" disabled="disabled">
				<a class="cart_quantity_up" href="GioHangServlet?command=addCart&id=<%=book.getId()%>"> + </a>
			</div>
			<div class="product-line-price" style="text-align: right; font-size: 20px">
				<%=nf.format(book.getPrice()* c.getQuantity())%> USD
				<a href="GioHangServlet?command=removeCart&id=<%=book.getId()%>">
					<img style="margin-left: 10px"src="images/delete.png">
				</a>
			</div>
			
		</div>
	<%
			}
		}
	%>
		<div class="totals">
			<div class="totals-item">
				<label>Total Bill</label>
				<div class="totals-value" id="cart-subtotal">
					<%=nf.format(total)%> $
				</div>
			</div>
		</div>
		<%if(cart.size()>0){ %>
		<a class="checkout" href="history.jsp" style="text-decoration: none;">History</a>
		<a class="checkout" href="ConfirmServlet?username=<%=username %>" style="text-decoration: none;">Pay</a>
		<%}else{ %>
		<a class="checkout" href="history.jsp" style="text-decoration: none;">History</a>
		<a class="checkout" href="product.jsp" style="text-decoration: none;">Pay</a>
		<%} %>
	</div>

</div>

<jsp:include page="footer.jsp" flush="true"></jsp:include>