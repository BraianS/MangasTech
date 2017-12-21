package com.mangastech.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.mangastech.model.PaginasEntity;

public interface PaginasService {
		
	PaginasEntity save(MultipartFile multipartFile) throws IOException;
	
	byte[] getPaginasFile(Long id);
	
	List<PaginasEntity> findAll();
	
}
