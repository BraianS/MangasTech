package com.mangastech.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mangastech.model.GruposEntity;
import com.mangastech.repository.GruposRepository;

@RestController
@Transactional
public class GruposController {
	
	@Autowired
	private GruposRepository gruposRepository;
	
	@RequestMapping(value="/grupo", method = RequestMethod.GET)
	public List<GruposEntity> listarAll () {
		
		return gruposRepository.findAll();
	}
}
