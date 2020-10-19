package model;

public class Book {
	
	private int id;
	private String title;
	private String author;
	private String description;
	private String isbn;
	private double price;
	private String image_filename;

	public Book() {
	}

	public Book(int id, String title, String author, String description, String isbn, double price, String image_filename) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.price = price;
		this.image_filename = image_filename;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getISBN() {
		return isbn;
	}
	
	public void setISBN(String isbn) {
		this.isbn = isbn;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getImageFilename() {
		return image_filename;
	}
	
	public void setImageFilename(String image_filename) {
		this.image_filename = image_filename;
	}

}
