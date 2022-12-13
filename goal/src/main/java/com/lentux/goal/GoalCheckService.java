package com.lentux.goal;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GoalCheckService {
	
	private final GoalCheckHistoryRepository goalCheckHistoryRepository;
	
	public boolean calciatoreHaSegnato(Integer calciatoreId) {
		goalCheckHistoryRepository.save(
				GoalCheckHistory.builder()
				.calciatoreId(calciatoreId)
				.haSegnato(false)
				.tempoGoal(LocalDateTime.now())
				.build()
		);
		
		return false;
	}
}
