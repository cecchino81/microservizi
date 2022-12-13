package com.lentux.calciatore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lentux.calciatore.entity.Calciatore;

@Repository
public interface CalciatoreRepository extends JpaRepository<Calciatore, Long>{

}
