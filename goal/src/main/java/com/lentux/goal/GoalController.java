package com.lentux.goal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/v1/goal-check")
public class GoalController {
	
	private GoalCheckService goalCheckService;

	@GetMapping(path = "{calciatoreId}")
	public GoalCheckResponse haSegnato(@PathVariable("calciatoreId") Integer calciatoreId) {
		boolean calciatoreHaSegnato = goalCheckService.calciatoreHaSegnato(calciatoreId);
		
		log.info("Ha segnato il calciatore con ID {}", calciatoreId);
		return new GoalCheckResponse(calciatoreHaSegnato);

	}
}
