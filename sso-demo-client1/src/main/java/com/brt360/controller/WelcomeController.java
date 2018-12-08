package com.brt360.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	
	@RequestMapping("/welcome")
	public String welcome(Principal user, Model model) {
		model.addAttribute("username", user.getName());
		return "welcome";
	}
	
}
