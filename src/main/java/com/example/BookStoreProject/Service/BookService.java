package com.example.BookStoreProject.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookStoreProject.Controller.BookController;
import com.example.BookStoreProject.Entity.Book;
import com.example.BookStoreProject.Exception.BookNotFoundException;
import com.example.BookStoreProject.Repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	@Autowired
	private TransactionService transactionService;
	
	Logger logger=LoggerFactory.getLogger(BookController.class);
	
	
	//Adding books
	public Book addBook(Book book) {
		logger.info("Adding the book {}",book);
		book=bookRepo.save(book);
		return book;
	}
	
	
	//Retrieves all the books
	public List<Book> getAllBooks(){
		logger.info("Retrieving all the books");
		return bookRepo.findAll();
	}
	
	
	//Getting book by Id
	public Book getBookById(int bookId)throws BookNotFoundException{
		logger.info("Retrieving book with id {} ",bookId);
		Book book=bookRepo.findById(bookId).orElse(null);
		if(book!=null) 
	        return book;
		else
			throw new BookNotFoundException("Book id not Found :" +bookId);
	}
	
	
	//Deletes book by Id
	public String deleteBook(int bookId) throws BookNotFoundException {
		logger.info("Deletes book with id {} ",bookId);
		Book book=bookRepo.findById(bookId).get();
		if(book !=null) {
		
		transactionService.deleteTransactions(bookId);
		bookRepo.deleteById(bookId);
		}
		return "Book deleted Succesfully";
		}
	
	
	//Updates book by Id
	public Book updateBook(int bookId, Book bookDetails) throws BookNotFoundException{
		logger.info("Updates book with id {} ",bookId);
		Book b=bookRepo.findById(bookId).orElse(null);
		if(b==null)
			throw new BookNotFoundException("Book id not Found :"+bookId);
		else {
			b.setName(bookDetails.getName());
			b.setAuthor(bookDetails.getAuthor());
			b.setPrice(bookDetails.getPrice());
			b.setQuantity(bookDetails.getQuantity());			
		}
		return bookRepo.save(b);
		}
	
	

	

	
	
	
}

