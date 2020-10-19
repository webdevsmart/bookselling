package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAOImpl;
import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class UpdateUser
 */
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String user_id = request.getParameter("id");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		java.sql.Date birthday= null;
		
		try {
			birthday = new java.sql.Date((new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthday"))).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sex = request.getParameter("sex");
		String email = request.getParameter("email");
		String phone_number = request.getParameter("phone_number");
		String address = request.getParameter("address");
		
		String err = "";
		String url = "/update_user.jsp";

		if (password.equals("") || email.equals("") || phone_number.equals("") || address.equals("")) {
			err += "Enter user info!";
		} else {
			Pattern pattenObj = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher matcherObj = pattenObj.matcher(email);
			if (!matcherObj.matches()) {
				err += "Email is wrong!";
			}else{
				Pattern pattenObj2 = Pattern
						.compile("(09)\\d{8}|(01)\\d{9}");
				Matcher matcherObj2 = pattenObj2.matcher(phone_number);
				if (!matcherObj2.matches()) {
					err += "Phone Number is wrong!";}
			}
		}

		if (err.length() > 0) {
			request.setAttribute("err", err);
		}

		try {
			if (err.length() == 0) {
				User u= new User(Integer.parseInt(user_id), username, password, birthday, sex, email, phone_number, address, "2");
				userDAO.updateUser(u);
				url = "/index.jsp";
			} else {
				url = "/update_user.jsp";
			}
			RequestDispatcher rd = getServletContext()
					.getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/register.jsp");
		}
	}

}
