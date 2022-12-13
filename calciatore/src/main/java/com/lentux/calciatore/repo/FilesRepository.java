package com.lentux.calciatore.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lentux.calciatore.entity.FileDB;

@Repository
public interface FilesRepository extends JpaRepository<FileDB, String> {

}