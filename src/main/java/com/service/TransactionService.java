package com.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dto.RewardResponse;
import com.entity.Transaction;

@Service
public class TransactionService {
	//new InputStreamReader(this.getClassLoader().getResourceAsStream("transactions.csv"))
	public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("transactions.csv")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                transactions.add(new Transaction(values[0], values[1], Double.parseDouble(values[2]), LocalDate.parse(values[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }

	public RewardResponse calculateRewardsResponse(String customerId) {
		List<Transaction> transactions = new ArrayList<>();
		transactions = getTransactions();

		if (customerId != null && customerId != "") {
			transactions = transactions.stream().filter(e -> e.getCustomerId().equals(customerId))
					.collect(Collectors.toList());
		}

		Map<String, Integer> rewardsByMonth = new HashMap<>();
		int totalRewards = 0;
		for (Transaction transaction : transactions) {
			double amount = transaction.getAmount();
			int rewardPoints = 0;
			if (amount > 100) {
				rewardPoints += 2 * (amount - 100);
			}
			if (amount > 50) {
				rewardPoints += 1 * Math.min(amount - 50, 50);
			}
			totalRewards += rewardPoints;
			LocalDate date = transaction.getDate();
			String month = date.getMonth().toString();
			rewardsByMonth.put(month, rewardsByMonth.getOrDefault(month, 0) + rewardPoints);
		}
		return new RewardResponse(totalRewards, rewardsByMonth);
	}
}
