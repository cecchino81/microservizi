package com.lentux.calciatore.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.lentux.calciatore.entity.Calciatore;
import com.lentux.calciatore.entity.Squadra;
import com.lentux.calciatore.repo.SquadraRepository;
import com.lentux.calciatore.service.SquadraService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SquadraServiceImpl implements SquadraService {

	private SquadraRepository squadraRepository;

	@Override
	public List<Squadra> listaSquadre() {
		return squadraRepository.findAll();
	}

	@Override
	public List<Calciatore> getCalciatoriByIdSquadra(long id) {
		Optional<Squadra> optional = squadraRepository.findById(id);
		List<Calciatore> listaCalciatoriBySquadra = new ArrayList<>();
		if (optional.isPresent()) {
			listaCalciatoriBySquadra = optional.get().getCalciatori();
		} else {
			throw new RuntimeException(" Nessun calciatore trovato per la squadra {} " + optional.get().getNome());
		}
		return listaCalciatoriBySquadra;
	}
	
	@Override
	public Squadra salvaSquadra(Squadra squadra) {
		return squadraRepository.saveAndFlush(squadra);
	}
	
	@Override
	public Squadra getSquadraById(long id) {
		Optional<Squadra> optional = squadraRepository.findById(id);
		Squadra squadra = null;
		if (optional.isPresent()) {
			squadra = optional.get();
		} else {
			throw new RuntimeException(" Nessuna squadra trovata :: " + id);
		}
		return squadra;
	}
	
	@Override
	public void deleteSquadraById(long id) {
		this.squadraRepository.deleteById(id);
	}
	
	@Override
	public Page<Squadra> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
			Sort.by(sortField).descending();
		
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.squadraRepository.findAll(pageable);
	}
}
