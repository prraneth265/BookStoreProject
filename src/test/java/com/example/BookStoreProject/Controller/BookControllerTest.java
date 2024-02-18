package com.example.BookStoreProject.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.example.BookStoreProject.Entity.Book;
import com.example.BookStoreProject.Exception.BookNotFoundException;
import com.example.BookStoreProject.Service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {
	
	@InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void testAddBook() {
       
        Book book = new Book();
        book.setName("Example Book");
        String result = bookController.addBook(book);
        verify(bookService, times(1)).addBook(book);
        assertEquals("Book is Added", result);
    }
	
    @Test
    public void testGetAllBooks() {
       
        List<Book> expectedBooks = new ArrayList<>();
        expectedBooks.add(new Book(0, "Book 1", null, 0, 0));
        expectedBooks.add(new Book(0, "Book 2", null, 0, 0));
        when(bookService.getAllBooks()).thenReturn(expectedBooks);
        List<Book> result = bookController.getAllBooks();
        verify(bookService, times(1)).getAllBooks();
        assertEquals(expectedBooks, result);
    }
	
    @Test
    public void testUpdateBook() throws BookNotFoundException {
        
        Book bookDetails = new Book();
        bookDetails.setName("Updated Book");
        Book expectedBook = new Book();
        expectedBook.setBookId(1);
        expectedBook.setName("Original Book");
        when(bookService.updateBook(1, bookDetails)).thenReturn(expectedBook);
        Book result = bookController.updateBook(1, bookDetails);
        verify(bookService, times(1)).updateBook(1, bookDetails);
        assertEquals(expectedBook, result);
    }

    @Test
    public void testUpdateBook_BookNotFoundException() throws BookNotFoundException {
        
        Book bookDetails = new Book();
        bookDetails.setName("Updated Book");
        when(bookService.updateBook(1, bookDetails)).thenThrow(new BookNotFoundException("Book not found"));
        assertThrows(BookNotFoundException.class, () -> bookController.updateBook(1, bookDetails));
        verify(bookService, times(1)).updateBook(1, bookDetails);
    }
	
	
    @Test
    public void testDeleteBook() throws BookNotFoundException {
       
        bookController.deleteBook(1);
        verify(bookService, times(1)).deleteBook(1);
    }

    @Test
    public void testDeleteBook_BookNotFoundException() throws BookNotFoundException {
        
        doThrow(new BookNotFoundException("Book not found")).when(bookService).deleteBook(1);
        assertThrows(BookNotFoundException.class, () -> bookController.deleteBook(1));
        verify(bookService, times(1)).deleteBook(1);
    }
	
	
    @Test
    public void testGetBookById() throws BookNotFoundException {
       
        Book expectedBook = new Book();
        expectedBook.setBookId(1);
        expectedBook.setName("Sample Book");
        when(bookService.getBookById(1)).thenReturn(expectedBook);
        Book result = bookController.getBookById(1);
        verify(bookService, times(1)).getBookById(1);
        assertEquals(expectedBook, result);
    }

    @Test
    public void testGetBookById_BookNotFoundException() throws BookNotFoundException {
   
        when(bookService.getBookById(1)).thenThrow(new BookNotFoundException("Book not found"));
        assertThrows(BookNotFoundException.class, () -> bookController.getBookById(1));
        verify(bookService, times(1)).getBookById(1);
    }
	

}
