package com.example.BookStoreProject.Service;



import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;



import com.example.BookStoreProject.Entity.Book;
import com.example.BookStoreProject.Exception.BookNotFoundException;
import com.example.BookStoreProject.Exception.LowQuantityException;
import com.example.BookStoreProject.Repository.BookRepository;
import com.example.BookStoreProject.Repository.TransactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {
	
	

	@Mock
    private BookRepository bookRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionService transactionService;
    
    
    @Mock
    private TransactionService transactionController;
    
    @SuppressWarnings("deprecation")
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    
    
    @Test
    public void testPurchaseBook() throws BookNotFoundException {
        int id = 1;
        int quantity = 5;
        Book book = new Book();
        book.setBookId(id);
        book.setQuantity(10);

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        transactionService.purchaseBook(id, quantity);

        assertEquals(15, book.getQuantity());
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).save(book);
       
    }

    @Test(expected = BookNotFoundException.class)
    public void testPurchaseProduct_ProductNotFound() throws BookNotFoundException {
        int id = 1;
        int quantity = 5;

        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        transactionService.purchaseBook(id, quantity);
    }

    @Test
    public void testSellProduct() throws LowQuantityException, BookNotFoundException {
        int id = 1;
        int quantity = 5;
        Book book = new Book();
        book.setBookId(id);
        book.setQuantity(10);

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        transactionService.SoldBooks(id, quantity);

        assertEquals(5, book.getQuantity());
        verify(bookRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).save(book);
       
    }

    @Test(expected = BookNotFoundException.class)
    public void testSellProduct_ProductNotFound() throws LowQuantityException, BookNotFoundException {
        int id = 1;
        int quantity = 5;

        when(bookRepository.findById(id)).thenReturn(Optional.empty());

        transactionService.SoldBooks(id, quantity);
    }

    @Test(expected = LowQuantityException.class)
    public void testSellProduct_NotEnoughStock() throws LowQuantityException, BookNotFoundException {
        int id = 1;
        int quantity = 10;
        Book book = new Book();
        book.setBookId(id);
        book.setQuantity(5);

        when(bookRepository.findById(id)).thenReturn(Optional.of(book));

        transactionService.SoldBooks(id, quantity);
    }


    @Test
    public void testDeleteTransactions() {
        int id = 1;

        transactionService.deleteTransactions(id);

        verify(transactionRepository, times(1)).findByBookId(id);
        verify(transactionRepository, times(1)).deleteAll(any());
    }

    
	
    @Test
    public void testGetTransactionByDate() {
       LocalDate tDate = LocalDate.now();

       transactionService.getTransactionByDate(tDate);

       verify(transactionRepository, times(1)).findByDate(tDate);
    }
    
    
    
    
	

}
