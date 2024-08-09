package com.example.banking.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	    

	}

