package com.lentux.calciatore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lentux.calciatore.entity.Squadra;

@Repository
public interface SquadraRepository extends JpaRepository<Squadra, Long>{
//commento prova
}
