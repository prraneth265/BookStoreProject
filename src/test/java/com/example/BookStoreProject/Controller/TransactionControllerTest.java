package com.example.BookStoreProject.Controller;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.BookStoreProject.Entity.Transaction;
import com.example.BookStoreProject.Exception.BookNotFoundException;
import com.example.BookStoreProject.Exception.LowQuantityException;
import com.example.BookStoreProject.Service.TransactionService;

@RunWith(MockitoJUnitRunner.class)
public class TransactionControllerTest {

	
	@InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTransactions() {
        
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.add(new Transaction());
        expectedTransactions.add(new Transaction());
        when(transactionService.getAllBooks()).thenReturn(expectedTransactions);
        List<Transaction> result = transactionController.getAllBooks();
        verify(transactionService, times(1)).getAllBooks();
        assertEquals(expectedTransactions, result);
    }
    
    
    @Test
    public void testSoldBooks() throws BookNotFoundException, LowQuantityException {
        int bookId = 1;
        int quantity = 5;
        Transaction dummyTransaction = new Transaction();
        when(transactionService.SoldBooks(bookId, quantity)).thenReturn(dummyTransaction);
        ResponseEntity<String> response = transactionController.SoldBooks(bookId, quantity);
        verify(transactionService, times(1)).SoldBooks(bookId, quantity);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        
        
    }

    @Test
    public void testSoldBooks_BookNotFoundException() throws BookNotFoundException, LowQuantityException {
        int bookId = 1;
        int quantity = 5;
        doThrow(new BookNotFoundException("Book not found")).when(transactionService).SoldBooks(bookId, quantity);
        assertThrows(BookNotFoundException.class, () -> transactionController.SoldBooks(bookId, quantity));
        verify(transactionService, times(1)).SoldBooks(bookId, quantity);
    }

    @Test
    public void testSoldBooks_LowQuantityException() throws BookNotFoundException, LowQuantityException {
        int bookId = 1;
        int quantity = 5;
        doThrow(new LowQuantityException("Low quantity")).when(transactionService).SoldBooks(bookId, quantity);
        assertThrows(LowQuantityException.class, () -> transactionController.SoldBooks(bookId, quantity));
        verify(transactionService, times(1)).SoldBooks(bookId, quantity);
    }
	
	
    @Test
    public void testPurchaseBook() throws BookNotFoundException {
        int bookId = 1;
        int quantity = 5;
        ResponseEntity<String> response = transactionController.PurchaseBook(bookId, quantity);
        verify(transactionService, times(1)).purchaseBook(bookId, quantity);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

	
	
    @Test
    public void testGetTransactionByDate() {
        
        LocalDate transactionDate = LocalDate.now();
        List<Transaction> expectedTransactions = new ArrayList<>(); // Add expected transactions here
        when(transactionService.getTransactionByDate(transactionDate)).thenReturn(expectedTransactions);
        ResponseEntity<List<Transaction>> response = transactionController.getTransactionByDate(transactionDate);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedTransactions, response.getBody());
    }
	
	
	
	
}
