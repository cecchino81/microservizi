package com.lentux.calciatore.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lentux.calciatore.entity.Calciatore;
import com.lentux.calciatore.entity.Squadra;

public interface SquadraService {
	List<Squadra> listaSquadre();
	List<Calciatore> getCalciatoriByIdSquadra(long id);
	Squadra salvaSquadra(Squadra squadra);
	Squadra getSquadraById(long id);
	void deleteSquadraById(long id);
	Page<Squadra> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);


}
