package com.mangastech.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mangastech.model.MangasEntity;
import com.mangastech.repository.MangasRepository;

@Controller
public class HomeController {
	
	
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/hello")
	public Principal user(Principal user) {
		return user;
	}
	
	
//	@RequestMapping("/login")
//	public String login() {
//		return "index";
//	}
}
