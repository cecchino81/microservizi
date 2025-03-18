package com.lentux.goal;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class GoalCheckHistory {

	@Id
	@SequenceGenerator (name="goal_id_sequence", sequenceName="goal_id_sequence")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "goal_id_sequence")
	private Integer id;
	private Integer calciatoreId;
	private Boolean haSegnato;
	private LocalDateTime tempoGoal;

}
