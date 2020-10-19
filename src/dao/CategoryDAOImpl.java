package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mysql.cj.jdbc.ClientPreparedStatement;

import model.Category;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public void addCategory(Category c) {
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into category value(?,?,?)";
		ClientPreparedStatement ps;
		try {
			ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, c.getId());
			ps.setString(2, c.getName());
			ps.setString(3, c.getDescription());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Category> getList() {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from category";
		List<Category> list = new ArrayList<Category>();
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				list.add(new Category(id, name, description));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static void main(String[] args) {
		CategoryDAOImpl dao = new CategoryDAOImpl();
		Category c = new Category(8, "Samsung", "DT");
		// dao.addCategory(c);
		// System.out.println(dao.getList());
		// dao.delCategory(10);
		dao.updateCategory(c);
	}

	@Override
	public void delCategory(int id) {
		Connection con = DBConnect.getConnecttion();
		String sql = "delete from category where id='" + id
				+ "'";
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Category getCategory(int id) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from category where id='" + id + "'";
		Category c = new Category();
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("name");
				String description = rs.getString("description");
				c = new Category(id, name, description);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public void updateCategory(Category c) {
		Connection con = DBConnect.getConnecttion();
		String sql = "update category set name=?, description=? where id=?";
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getDescription());
			ps.setInt(3, c.getId());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
