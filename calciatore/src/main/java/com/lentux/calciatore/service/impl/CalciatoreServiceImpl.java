package com.lentux.calciatore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lentux.calciatore.entity.Calciatore;
import com.lentux.calciatore.repo.CalciatoreRepository;
import com.lentux.calciatore.service.CalciatoreService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CalciatoreServiceImpl implements CalciatoreService {

	private CalciatoreRepository calciatoreRepository;

	@Override
	public List<Calciatore> getCalciatori() {
		return calciatoreRepository.findAll();
	}

	@Override
	public void salvaCalciatore(Calciatore calciatore) {
		this.calciatoreRepository.save(calciatore);
	}

	@Override
	public Calciatore getCalciatoreById(long id) {
		Optional<Calciatore> optional = calciatoreRepository.findById(id);
		Calciatore calciatore = null;
		if (optional.isPresent()) {
			calciatore = optional.get();
		} else {
			throw new RuntimeException(" Employee not found for id :: " + id);
		}
		return calciatore;
	}

	@Override
	public void deleteCalciatoreById(long id) {
		this.calciatoreRepository.deleteById(id);
	}

	@Override
	public Page<Calciatore> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.calciatoreRepository.findAll(pageable);
	}
}
