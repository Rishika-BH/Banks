package com.example.banking.service;

import com.example.banking.model.Account;
import com.example.banking.model.Transaction;
import com.example.banking.repo.AccountRepository;
import com.example.banking.repo.TransactionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Transaction makeTransaction(Long sourceAccountNumber, Long destinationAccountNumber, BigDecimal amount) {
        // Retrieve accounts
        Account sourceAccount = accountRepository.findById(sourceAccountNumber).orElseThrow(() -> new RuntimeException("Source account not found"));
        Account destinationAccount = accountRepository.findById(destinationAccountNumber).orElseThrow(() -> new RuntimeException("Destination account not found"));

        // Check sufficient balance
        if (sourceAccount.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }

        // Update account balances
        sourceAccount.setBalance(sourceAccount.getBalance().subtract(amount));
        destinationAccount.setBalance(destinationAccount.getBalance().add(amount));
        accountRepository.saveAll(List.of(sourceAccount, destinationAccount));

        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setSourceAccount(null);
        transaction.setDestinationAccount(null);

        return transactionRepository.save(transaction);
    }

  

	public List<Transaction> getTransactionsByAccountId(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}
}
