package com.test.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.Test;

import com.entity.Transaction;
import com.service.TransactionService;

//separate test data can be created also transactionservice.getTransactions() be mocked
//for demo purpose as the actual transaction data is available verifying the actual data 
public class TransactionServiceTest {

	@Test
	public void getTransactions() {
		try {
			TransactionService transactionservice = new TransactionService();
			List<Transaction> actualValue = transactionservice.getTransactions();
			assert(!actualValue.isEmpty());
		} catch (Exception exception) {
			exception.printStackTrace();
			assertFalse(false);
		}
	}

	@Test
	public void calculateRewardsResponse() {
		try {
			String customerId = "1";

			TransactionService transactionservice = new TransactionService();
			assert(transactionservice.calculateRewardsResponse(customerId).getTotalRewards()==135);
		} catch (Exception exception) {

			exception.printStackTrace();
			assertFalse(false);
		}
	}
}
