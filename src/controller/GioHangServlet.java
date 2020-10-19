package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Book;

/**
 * Servlet implementation class GioHangServlet
 */
public class GioHangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Cart> cart = new ArrayList<Cart>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GioHangServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		String book_id = request.getParameter("id");
		if (command.equals("addCart")) {
			Book book = new Book(Integer.parseInt(book_id), "", "", "", "", 0.0 , "");
			addToCart(book);
			// After adding to the cart, we will move to the shopping cart page
			// You need to create a session to store values
			HttpSession session = request.getSession();

			System.out.println(cart.size());
			session.setAttribute("cart", cart);
			response.sendRedirect("/shopping/cart.jsp");
		} else{
			if(command.equals("deleteCart")){
				Book book = new Book(Integer.parseInt(book_id), "", "", "", "", 0.0 , "");
				deleteFromCart(book);
				HttpSession session = request.getSession();

				System.out.println(cart.size());
				session.setAttribute("cart", cart);
				response.sendRedirect("/shopping/cart.jsp");
			} else{
				if(command.equals("removeCart")){
					Book book = new Book(Integer.parseInt(book_id), "", "", "", "", 0.0 , "");
					removeFromCart(book);
					HttpSession session = request.getSession();

					// Save cart info to session
					session.setAttribute("cart", cart);
					response.sendRedirect("/shopping/cart.jsp");
				}else{
					if(command.equals("setCart")){
						Book book = new Book(Integer.parseInt(book_id), "", "", "", "", 0.0 , "");
						setCart(book, Integer.parseInt((String) request.getParameter("amount")));
						HttpSession session = request.getSession();

						session.setAttribute("cart", cart);
						response.sendRedirect("/shopping/cart.jsp");
					}
				}
			}
		}
	}

	private String removeFromCart(Book book) {
		for (Cart item : cart) {
			if (item.getBook().getId() == book.getId()) {
				cart.remove(item);
				return "cart";
			}
		}
		return "cart";
	}
	
	private String setCart(Book book, int s) {
		for (Cart item : cart) {
			if (item.getBook().getId() == book.getId()) {
				item.setQuantity(s);
				return "cart";
			}
		}
		Cart c = new Cart();
		c.setBook(book);
		c.setQuantity(s);
		cart.add(c);
		return "cart";
	}

	// add new products into the store
	public String addToCart(Book book) {
		for (Cart item : cart) {
			if (item.getBook().getId() == book.getId()) {
				item.setQuantity(item.getQuantity() + 1);
				return "cart";
			}
		}
		Cart c = new Cart();
		c.setBook(book);
		c.setQuantity(1);
		cart.add(c);
		return "cart";
	}
	
	public String deleteFromCart(Book book) {
		for (Cart item : cart) {
			if (item.getBook().getId() == book.getId() && item.getQuantity()>1) {
				item.setQuantity(item.getQuantity() - 1);
				return "cart";
			}
		}
		return "cart";
	}

}
