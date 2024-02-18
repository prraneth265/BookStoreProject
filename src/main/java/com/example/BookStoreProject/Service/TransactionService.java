package com.example.BookStoreProject.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookStoreProject.Controller.BookController;
import com.example.BookStoreProject.Entity.Book;
import com.example.BookStoreProject.Entity.Transaction;
import com.example.BookStoreProject.Entity.Transaction.transactionType;
import com.example.BookStoreProject.Exception.BookNotFoundException;
import com.example.BookStoreProject.Exception.LowQuantityException;
import com.example.BookStoreProject.Repository.BookRepository;
import com.example.BookStoreProject.Repository.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {
	
	  @Autowired
	  private BookRepository bookRepository;

	  @Autowired
	  private TransactionRepository transactionRepository;
	  
	  
	  Logger logger=LoggerFactory.getLogger(BookController.class);

	  //Retrieves all the books
	  public List<Transaction> getAllBooks(){
		    logger.info("retrieves all the transactions");
			return transactionRepository.findAll();
		}

	  
	  
	  //Purchasing book by id,quantity
	  @Transactional
	  public void purchaseBook(int id,int quantity) throws BookNotFoundException {
		  
		  logger.info("Purchased book with quantity {}",quantity);
		  Optional<Book> OptionalBook=bookRepository.findById(id);
		  if(OptionalBook.isPresent()) {
				Book book=OptionalBook.get();
				int newQuantity=book.getQuantity()+quantity;
				book.setQuantity(newQuantity);
				bookRepository.save(book);
		        Transaction transaction=new Transaction();
		        transaction.setBook(book);
		        transaction.setQuantity(quantity);
		        transaction.setDate(LocalDate.now());
		        transaction.setType(transactionType.purchase);
		        
		        transactionRepository.save(transaction);
			}
			else {
				throw new BookNotFoundException("Book not Found ");
			}
	}
		  
	  
	  //Selling book by id,quantity
	  @Transactional
	  public Transaction SoldBooks(int id,int quantity) throws BookNotFoundException, LowQuantityException {
		  
		  logger.info("Sold book with quantity {}",quantity);
		  Optional<Book> OptionalBook=bookRepository.findById(id);
			
			if(OptionalBook.isPresent()) {
				Book book=OptionalBook.get();
				int newQuantity=book.getQuantity()-quantity;
				if(newQuantity<0) {
					throw new LowQuantityException("Less quantity available");
				}
				book.setQuantity(newQuantity);
				bookRepository.save(book);
		        Transaction transaction=new Transaction();
		        transaction.setBook(book);
		        transaction.setQuantity(quantity);
		        transaction.setDate(LocalDate.now());
		        transaction.setType(transactionType.sale);
		        
		        return transactionRepository.save(transaction);
			}
			else {
				throw new BookNotFoundException("Book not Found ");
			  }
	
	  }
	  
  
	  //deleting the transaction by id
	  public void deleteTransactions(int id) {
		  
		  //transactionRepository.deleteTransactions(id);
		  
		  List<Transaction> list=transactionRepository.findByBookId(id);
		  transactionRepository.deleteAll(list);
		  
		  
	  }
	  
	  
	  
	 



      //Retrieves book by Date
	  public List<Transaction> getTransactionByDate(LocalDate transactionDate) {
		  
		    logger.info("Retrieving the transactions by Date:{}",transactionDate);
	        return transactionRepository.findByDate(transactionDate);
	    }
	    
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  


}
