package com.brt360.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brt360.memoryDB.RoleMemoryMap;
import com.brt360.memoryDB.UserMemoryMap;

@Controller
public class RegisterController {
	
	@RequestMapping("/registerPage")
	public String registerPage() {
		return "registerPage";
	}
	
	@RequestMapping("/register")
	public String register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("passwordRepeat") String passwordRepeat, Model model) {
		//验证密码一致
		if (!password.equals(passwordRepeat)) {
			model.addAttribute("error", "两次输入的密码不一致");
			return "registerPage";
		}
		
		//验证用户是否存在
		if (UserMemoryMap.map.containsKey(username)) {
			model.addAttribute("error", "用户已存在");
			return "registerPage";
		}
		
		//注册用户
		UserMemoryMap.map.put(username, password);
		//默认user权限
		List<String> roles = new ArrayList<String>();
		roles.add("ROLE_USER");
		RoleMemoryMap.map.put(username, roles);
		//转到loginPage
		return "loginPage";
	}

}
