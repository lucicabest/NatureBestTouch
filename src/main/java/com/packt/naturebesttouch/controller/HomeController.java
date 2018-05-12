package com.packt.naturebesttouch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping
	public String welcome(Model model) {
	model.addAttribute("greeting", "Welcome to my Final Project!");
	model.addAttribute("tagline", "The one and only amazing	web store");
	return "welcome";
	}
}
