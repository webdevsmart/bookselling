package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.ClientPreparedStatement;

import model.Book;

public class BookDAOImpl implements BookDAO {
	@Override
	public void addBook(Book book) {
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into book value(?,?,?,?,?,?.?)";
		ClientPreparedStatement ps;
		try {
			ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, book.getId());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getAuthor());
			ps.setString(4, book.getDescription());
			ps.setString(5, book.getISBN());
			ps.setDouble(6, book.getPrice());
			ps.setString(7, book.getImageFilename());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Book> getList() {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from book";
		List<Book> list = new ArrayList<Book>();
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author= rs.getString("author");
				String description = rs.getString("description");
				String isbn = rs.getString("isbn");
				Double price = rs.getDouble("price");
				String image_filename = rs.getString("image_filename");
				list.add(new Book(id, title, author, description, isbn, price, image_filename));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Book getBook(int id) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from book where id='" + id + "'";
		Book book = null;
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String title = rs.getString("title");
				String author= rs.getString("author");
				String description = rs.getString("description");
				String isbn = rs.getString("isbn");
				Double price = rs.getDouble("price");
				String image_filename = rs.getString("image_filename");
				book = new Book(id, title, author, description, isbn, price, image_filename);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
	
	@Override
	public List<Book> searchList(String search_title, String search_author) {
		Connection con = DBConnect.getConnecttion();
		
		if (search_title == null || search_author == null)
			return null;
		
		String sql = "SELECT * FROM book WHERE title like N'%"+ search_title +"%' and author like N'%"+ search_author + "%'";
		
		List<Book> list = new ArrayList<Book>();
		
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String author= rs.getString("author");
				String description = rs.getString("description");
				String isbn = rs.getString("isbn");
				Double price = rs.getDouble("price");
				String image_filename = rs.getString("image_filename");
				list.add(new Book(id, title, author, description, isbn, price, image_filename));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
