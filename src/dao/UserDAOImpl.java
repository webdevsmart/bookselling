package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.ClientPreparedStatement;

import model.Category;
import model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public void addUser(User u) {
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into user value(?,?,?,?,?,?,?,?,?)";
		ClientPreparedStatement ps;
		try {
			ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, u.getUser_id());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.setDate(4, u.getBirthday());
			ps.setString(5, u.getSex());
			ps.setString(6, u.getEmail());
			ps.setString(7, u.getPhoneNumber());
			ps.setString(8, u.getAddress());
			ps.setString(9, u.getRole());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean checkUser(String username) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from user where username='" + username + "'";
		ClientPreparedStatement ps;
		try {
			ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) {
		UserDAOImpl dao = new UserDAOImpl();
		// dao.addUser(new User(0, "admin", "12345", "admin", "1"));
//		System.out.println(dao.checkUser("admin"));
		System.out.println(dao.login("admin", "12345"));
	}

	@Override
	public boolean login(String username, String password) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from user where username='" + username
				+ "' and password='" + password + "'";
		ClientPreparedStatement ps;
		try {
			ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				con.close();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void updateUser(User u) {
		Connection con = DBConnect.getConnecttion();
		String sql = "update user set user_id=?, password=?, birthday=?, sex=?, email=?, phone_number=?, address=?, role=? where username=?";
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, u.getUser_id());
			ps.setString(2, u.getPassword());
			ps.setDate(3, u.getBirthday());
			ps.setString(4, u.getSex());
			ps.setString(5, u.getEmail());
			ps.setString(6, u.getPhoneNumber());
			ps.setString(7, u.getAddress());
			ps.setString(8, u.getRole());
			ps.setString(9, u.getUsername());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public User getUser(String name) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from user where username='" + name + "'";
		User u = new User();
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int user_id= rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Date birthday = rs.getDate("birthday");
				String sex = rs.getString("sex");
				String email = rs.getString("email");
				String phone_number = rs.getString("phone_number");
				String address = rs.getString("address");
				String role = rs.getString("role");
				u = new User(user_id, username, password, birthday, sex, email, phone_number, address, role);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}


}
