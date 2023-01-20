package com.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RewardResponse {
    private int totalRewards;
    private Map<String, Integer> rewardsByMonth;
}
