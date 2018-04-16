package com.mangastech.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
