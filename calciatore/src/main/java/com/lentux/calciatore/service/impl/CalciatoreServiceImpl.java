package com.lentux.calciatore.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lentux.calciatore.GoalCheckResponse;
import com.lentux.calciatore.entity.Calciatore;
import com.lentux.calciatore.repo.CalciatoreRepository;
import com.lentux.calciatore.service.CalciatoreService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CalciatoreServiceImpl implements CalciatoreService {

	private CalciatoreRepository calciatoreRepository;
    private RestTemplate restTemplate;

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
			throw new RuntimeException(" Calciatore non trovato con id: {} " + id);
		}
		
	     ResponseEntity<GoalCheckResponse> responseEntity = restTemplate
	                .getForEntity("http://localhost:8080/api/v1/goal-check/" + calciatore.getId(),
	                GoalCheckResponse.class);
	     
	     GoalCheckResponse goalCheckResponse = responseEntity.getBody();
	     System.out.println(responseEntity.getStatusCode());
	     
	     calciatore.setHaSegnato(goalCheckResponse.haSegnato());
	     
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
