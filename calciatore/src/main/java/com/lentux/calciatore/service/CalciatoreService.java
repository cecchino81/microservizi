package com.lentux.calciatore.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.lentux.calciatore.entity.Calciatore;

public interface CalciatoreService {
	List<Calciatore> getCalciatori();
	void salvaCalciatore(Calciatore calciatore);
	Calciatore getCalciatoreById(long id);
	void deleteCalciatoreById(long id);
	Page<Calciatore> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
