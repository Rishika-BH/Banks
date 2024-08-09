package com.example.banking.controller;

import com.example.banking.model.Transaction;
import com.example.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> makeTransaction(@RequestParam Long sourceAccountNumber,
                                                       @RequestParam Long destinationAccountNumber,
                                                       @RequestParam BigDecimal amount) {
        Transaction transaction = transactionService.makeTransaction(sourceAccountNumber, destinationAccountNumber, amount);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountId(@PathVariable Long accountId) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountId(accountId);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
