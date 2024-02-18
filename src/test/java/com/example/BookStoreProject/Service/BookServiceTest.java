package com.example.BookStoreProject.Service;


import static org.mockito.ArgumentMatchers.eq;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


import java.util.List;
import java.util.Optional;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.BookStoreProject.Entity.Book;
import com.example.BookStoreProject.Exception.BookNotFoundException;
import com.example.BookStoreProject.Repository.BookRepository;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	
     @Mock
     private BookRepository bookRepo;
     
     @Mock
     private TransactionService transactionService;
     
     @InjectMocks
     private BookService bookService;
    
     
     @SuppressWarnings("deprecation")
     @Before
 	public void setUp() {
 		MockitoAnnotations.initMocks(this);
 	}
     
     
     
     
     
     
     
     @Test
     
     public void testAddBook() {
    	 
         Book book = new Book();
         when(bookRepo.save(book)).thenReturn(book);

         Book savedBook = bookService.addBook(book);

         assertEquals(book, savedBook);
        
         verify(bookRepo, times(1)).save(book);
     }
     

     @Test
     public void testGetAllBooks() {
    	
    	List<Book> books = new ArrayList<>();
 		books.add(new Book(1, "Book 1", "Author 1", 10, 100));
 		books.add(new Book(2, "Book 2", "Author 2", 20, 200));
 		when(bookRepo.findAll()).thenReturn(books);
 		
 		
 		List<Book> result = bookService.getAllBooks();
 		
 		
 		assertEquals(2, result.size());
 		assertEquals("Book 1", result.get(0).getName());
 		assertEquals("Author 1", result.get(0).getAuthor());
 		assertEquals(10, result.get(0).getQuantity());
 		assertEquals(100, result.get(0).getPrice());
 		assertEquals("Book 2", result.get(1).getName());
 		assertEquals("Author 2", result.get(1).getAuthor());
 		assertEquals(20, result.get(1).getQuantity());
 		assertEquals(200, result.get(1).getPrice());
     }
     
     

     @Test
     public void testGetBookById() throws BookNotFoundException {
         Book book = new Book();
         when(bookRepo.findById(1)).thenReturn(Optional.of(book));

         Book retrievedBook = bookService.getBookById(1);

         assertEquals(book, retrievedBook);
         verify(bookRepo, times(1)).findById(1);
     }

     
     @Test(expected = BookNotFoundException.class)
     public void testGetBookById_BookNotFound() throws BookNotFoundException {
         when(bookRepo.findById(1)).thenReturn(Optional.empty());

         bookService.getBookById(1);
     }
     

     

     @Test
     public void testEditBook() throws BookNotFoundException {
         Book existingBook = new Book();
         existingBook.setBookId(1);
         existingBook.setName("Old Book");

         Book updatedBook = new Book();
         updatedBook.setBookId(1);
         updatedBook.setName("New Book");

         when(bookRepo.findById(1)).thenReturn(Optional.of(existingBook));
         when(bookRepo.save(existingBook)).thenReturn(updatedBook);

         Book editedBook = bookService.updateBook(1, updatedBook);

         assertEquals(updatedBook, editedBook);
         assertEquals(updatedBook.getName(), editedBook.getName());
         verify(bookRepo, times(1)).findById(1);
         verify(bookRepo, times(1)).save(existingBook);
     }
     
     
     
     
     @Test
     public void testDeleteBook() throws BookNotFoundException {
    	   
    	    Book book = new Book();
    	    book.setBookId(1);

    	   
    	    when(bookRepo.findById(eq(1))).thenReturn(Optional.of(book));
    	    String result = bookService.deleteBook(1); 
    	    verify(transactionService).deleteTransactions(eq(1));
    	    verify(bookRepo).deleteById(eq(1));
            assertEquals("Book deleted Succesfully", result);
    	    verify(bookRepo, times(1)).findById(eq(1));
            verifyNoMoreInteractions(bookRepo);
    	    verify(bookRepo, times(1)).deleteById(eq(1));
    	    verify(transactionService, times(1)).deleteTransactions(eq(1));
    	}
     
}    
   
     

     
     
     
     

