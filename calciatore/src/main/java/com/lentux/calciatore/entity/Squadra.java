package com.lentux.calciatore.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
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
