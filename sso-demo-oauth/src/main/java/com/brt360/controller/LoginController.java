package com.brt360.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@RequestMapping("/loginPage")
	public String loginPage(@RequestParam(name = "error", required = false) Object error, Model model) {
		if (error != null) {
			model.addAttribute("error", "用户名或者密码不正确");
		}
		return "loginPage";
	}

}
