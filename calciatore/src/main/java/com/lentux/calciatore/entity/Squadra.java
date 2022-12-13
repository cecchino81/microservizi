package com.lentux.calciatore.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "squadra")
public class Squadra {
	
	@Id
	@SequenceGenerator (name="squadra_id_sequence", sequenceName="squadra_id_sequence")
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "squadra_id_sequence")
	private long id;
	
	@NotEmpty(message = "Il campo Nome non può essere vuoto")
	@Column(name = "nome")
	private String nome;
	
	@NotEmpty(message = "Il campo Città non può essere vuoto")
	@Column(name = "citta")
	private String citta;
	
	@NotEmpty(message = "Il campo Capienza non può essere vuoto")
	@Column(name = "capienza")
	private String capienza;
	
	@NotEmpty(message = "Il campo Stadio non può essere vuoto")
	@Column(name = "stadio")
	private String stadio;

//	@NotNull
	@OneToOne(mappedBy = "squadra", cascade = CascadeType.REMOVE, optional = false)
    private FileDB files;
	
	@OneToMany(mappedBy="squadra")
	public List<Calciatore> calciatori;
}
