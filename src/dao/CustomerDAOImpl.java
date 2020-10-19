package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.ClientPreparedStatement;

import model.Customer;

public class CustomerDAOImpl implements CustomerDAO {
	@Override
	public void addCustomer(Customer customer) {
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into book value(?,?,?,?)";
		ClientPreparedStatement ps;
		try {
			ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, customer.getId());
			ps.setString(2, customer.getFullName());
			ps.setString(3, customer.getEmail());
			ps.setString(4, customer.getAddress());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Customer> getList() {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from customer";
		List<Customer> list = new ArrayList<Customer>();
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String full_name = rs.getString("full_name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				list.add(new Customer(id, full_name, email, address));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public Customer getCustomer(int id) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from customer where id='" + id + "'";
		Customer customer = null;
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String full_name = rs.getString("full_name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				customer = new Customer(id, full_name, email, address);
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}
}
