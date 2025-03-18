package com.lentux.calciatore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "calciatore")
public class Calciatore {
	
	@Id
	@SequenceGenerator (name="calciatore_id_sequence", sequenceName="calciatore_id_sequence")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "calciatore_id_sequence")
	private long id;
	
	@NotEmpty(message = "Il campo Nome non può essere vuoto")
	@Column(name = "nome")
	private String nome;
	
	@NotEmpty(message = "Il campo Cognome non può essere vuoto")
	@Column(name = "cognome")
	private String cognome;
	
	@NotNull(message = "Il campo Età non può essere vuoto")
	@Column(name = "eta")
	private Integer eta;
	
	@Column(name = "nazionalita")
	private String nazionalita;
	
	@Column(name = "ruolo")
	private String ruolo;
	
	@Column(name = "haSegnato")
	private Boolean haSegnato;
	
	@ManyToOne
	@JoinColumn(name = "idSquadra")
	private Squadra squadra;
	
}
