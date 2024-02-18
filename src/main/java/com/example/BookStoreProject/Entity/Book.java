package com.example.BookStoreProject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id
	private int bookId;
	
	private String name;
	
	private String author;
	
	private int quantity;
	
	private int price;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Book(int bookId, String name, String author, int quantity, int price) {
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.quantity = quantity;
		this.price = price;
	}

	
	
	
}
