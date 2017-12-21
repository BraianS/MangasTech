package com.mangastech.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mangastech.model.PaginasEntity;
import com.mangastech.repository.PaginasRepository;

@Service
public class PaginasServiceimpl implements PaginasService{
	
	@Autowired
	private PaginasRepository paginasRepository;

	@Override
	public PaginasEntity save(MultipartFile file) throws IOException {
		PaginasEntity pag = new PaginasEntity();
		pag.setFotos(file.getBytes());
		paginasRepository.save(pag);
		return pag;
	}

	

	@Override
	public List<PaginasEntity> findAll() {
		// TODO Auto-generated method stub
		return (List<PaginasEntity>) paginasRepository.findAll();
	}



	@Override
	public byte[] getPaginasFile(Long id) {
		// TODO Auto-generated method stub
		return paginasRepository.findOne(id).getFotos();
	}
	
	
}
