package dao;

import java.util.List;

import model.Book;

public interface BookDAO {

	// add Book
	public void addBook(Book book);

	// get all book list
	public List<Book> getList();

	public Book getBook(int id);

	public List<Book> searchList(String title, String author);
}
