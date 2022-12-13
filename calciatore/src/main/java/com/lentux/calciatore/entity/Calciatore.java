package com.lentux.calciatore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
	
	@ManyToOne
	@JoinColumn(name = "idSquadra")
	private Squadra squadra;
	
}
