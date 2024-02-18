package com.example.BookStoreProject.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.example.BookStoreProject.Entity.Transaction;
import com.example.BookStoreProject.Exception.BookNotFoundException;
import com.example.BookStoreProject.Exception.LowQuantityException;
import com.example.BookStoreProject.Service.TransactionService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class TransactionController {
	
	
	@Autowired
	  private TransactionService transactionService;

	

	
	
	@RequestMapping(value="/getalltransactions",method=RequestMethod.GET)
	public List<Transaction> getAllBooks() {
		return transactionService.getAllBooks();
	}

	
	@RequestMapping(value="/soldbooks/{id}/{quantity}",method=RequestMethod.POST)
	  public ResponseEntity<String> SoldBooks(@PathVariable int id,@PathVariable int quantity) throws BookNotFoundException, LowQuantityException{
		
		transactionService.SoldBooks(id,quantity);
		return ResponseEntity.ok("Book Sold Succesfully");  
	  }
	
	
	
	
	@RequestMapping(value="/purchasebooks/{id}/{quantity}",method=RequestMethod.POST)
	  public ResponseEntity<String> PurchaseBook(@PathVariable int id,@PathVariable int quantity) throws BookNotFoundException{
		transactionService.purchaseBook(id,quantity);
		return ResponseEntity.ok("Book Purchased Succesfully");  
	  }
	
	
	
	
	@RequestMapping(value="/transaction/{date}",method=RequestMethod.GET)
    public ResponseEntity<List<Transaction>> getTransactionByDate(@PathVariable LocalDate date) {
        List<Transaction> transactions = transactionService.getTransactionByDate(date);
        return ResponseEntity.ok(transactions);
    }

}















