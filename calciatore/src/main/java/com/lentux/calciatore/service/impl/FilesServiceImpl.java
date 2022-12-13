package com.lentux.calciatore.service.impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lentux.calciatore.entity.FileDB;
import com.lentux.calciatore.entity.Squadra;
import com.lentux.calciatore.repo.FilesRepository;
import com.lentux.calciatore.service.FilesService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FilesServiceImpl implements FilesService {

	  private FilesRepository filesRepository;

	  public FileDB store(MultipartFile file,Squadra squadra) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    FileDB fileToSave = FileDB.builder()
	    		  .name(fileName)
	    		  .type(file.getContentType())
	    		  .data(file.getBytes())
	    		  .squadra(squadra)
	    		  .build();

	    return filesRepository.save(fileToSave);
	  }
}
