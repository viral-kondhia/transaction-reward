package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.RewardResponse;
import com.entity.Transaction;
import com.service.TransactionService;

@RestController
@RequestMapping("/api")
public class RewardsController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/rewards")
    public RewardResponse getRewards() {
        
        return transactionService.calculateRewardsResponse(null);
    }

    @GetMapping("/rewards/{customerId}")
    public RewardResponse getRewardsByCustomer(@PathVariable String customerId) {
        return transactionService.calculateRewardsResponse(customerId);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }
}
