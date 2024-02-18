package com.example.BookStoreProject.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transaction {

	  @Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	  private int id;
	  private LocalDate date;
	  
	  private int quantity;
	  
	  
	  private transactionType type;
	  
	  
	  
	  @ManyToOne
	  @JoinColumn(name = "bookid")
	  private Book book;
	  
	  public enum transactionType{
		  purchase,sale;
	  }
	  
	  
	  public Transaction(LocalDate date, int quantity, transactionType type,Book book) {
		  
		  this.date=date;
		  this.quantity=quantity;
		  this.type=type;
		  this.book=book;
		   
	  }
	  
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public transactionType getType() {
		return type;
	}


	public void setType(transactionType type) {
		this.type = type;
	}
	
	  
	
	  

	
	
	
	
	
	
	
	
	
	
	
	
}
