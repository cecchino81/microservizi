package com.lentux.calciatore.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.lentux.calciatore.entity.FileDB;
import com.lentux.calciatore.entity.Squadra;

public interface FilesService {
	FileDB store(MultipartFile file, Squadra squadra) throws IOException;
}
