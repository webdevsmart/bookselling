package model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class History {
	private int id;
	private int user_id;
	private int book_id;
	private Timestamp purchase_date;
	private int amount;
	private double total;
	
	public History() {
	}
	
	public History(int id_history, int user_id, int book_id, Timestamp purchase_date,
			int amount, double total) {
		
		this.id = id;
		this.user_id = user_id;
		this.book_id = book_id;
		this.purchase_date = purchase_date;
		this.amount = amount;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return user_id;
	}

	public void setUserId(int user_id) {
		this.user_id = user_id;
	}

	public int getBookId() {
		return book_id;
	}

	public void setBookId(int book_id) {
		this.book_id = book_id;
	}

	public Timestamp getPurchaseDate() {
		return purchase_date;
	}

	public void setPurchaseDate(Timestamp purchase_date) {
		this.purchase_date = purchase_date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	

}
