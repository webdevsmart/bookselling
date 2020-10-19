package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.cj.jdbc.ClientPreparedStatement;

import model.Category;
import model.History;

public class HistoryDAOImpl implements HistoryDAO {

	@Override
	public void addHistory(History h) {
		Connection con = DBConnect.getConnecttion();
		String sql = "insert into history value(?,?,?,?,?,?)";
		ClientPreparedStatement ps;

		try {
			ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, h.getId());
			ps.setInt(2, h.getUserId());
			ps.setInt(3, h.getBookId());
			ps.setTimestamp(4, h.getPurchaseDate());
			ps.setInt(5, h.getAmount());
			ps.setDouble(6, h.getTotal());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<History> getList(int id) {
		Connection con = DBConnect.getConnecttion();
		String sql = "select * from history where user_id='"+ id +"'";
		List<History> list = new ArrayList<History>();
		try {
			ClientPreparedStatement ps = (ClientPreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id_history = rs.getInt("id_history");
				int user_id = rs.getInt("user_id");
				int book_id = rs.getInt("book_id");
				Timestamp purchase_date = rs.getTimestamp("purchase_date");
				int amount = rs.getInt("amount");
				double total = rs.getDouble("total");
				list.add(new History(id_history, user_id, book_id, purchase_date, amount, total));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

}
