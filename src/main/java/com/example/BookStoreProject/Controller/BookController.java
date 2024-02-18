package com.example.BookStoreProject.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookStoreProject.Entity.Book;
import com.example.BookStoreProject.Exception.BookNotFoundException;
import com.example.BookStoreProject.Service.BookService;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class BookController {
	
	@Autowired
	BookService bookService;
	
	
	
	@RequestMapping(value="/addBook",method=RequestMethod.POST)
	public String addBook(@RequestBody Book book) {
		bookService.addBook(book);
		return "Book is Added";	
	}
	
	
	@RequestMapping(value="/getallbooks",method=RequestMethod.GET)
	public List<Book> getAllBooks() {
		
		return bookService.getAllBooks();		
	}
	
	
	@RequestMapping(value="/books/{bookId}",method=RequestMethod.PUT)
	public Book updateBook(@PathVariable(value="bookId") int bookId, @RequestBody Book bookDetails)throws BookNotFoundException {
		
		return bookService.updateBook(bookId, bookDetails);
	}
	
	
	@RequestMapping(value="/books/{bookId}",method=RequestMethod.DELETE)
	public void deleteBook(@PathVariable(value="bookId") int bookId) throws BookNotFoundException {
		bookService.deleteBook(bookId);	
	}
	
	
	@RequestMapping(value="/books/{bookId}",method=RequestMethod.GET)
	public Book getBookById(@PathVariable int bookId) throws BookNotFoundException {
		return bookService.getBookById(bookId);	
	}
	
	
	
	
	
	
	
	
	
	
	
	//@RequestMapping(value="/books/search",method=RequestMethod.GET)
   // public ResponseEntity<List<Book>> searchBooks(@RequestParam("query") String query){
    //    return ResponseEntity.ok(bookService.searchBooks(query));
    //}
	
}
