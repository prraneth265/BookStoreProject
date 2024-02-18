package com.example.BookStoreProject.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



import com.example.BookStoreProject.Entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer>{
 
	@Query(value="select t from Transaction t where t.book.bookId=?1")
	List<Transaction> findByBookId(int id);

	

	@Query("SELECT t FROM Transaction t WHERE t.date = :transactionDate")
	List<Transaction> findByDate(LocalDate transactionDate);
	
		
}
