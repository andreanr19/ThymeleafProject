package co.edu.icesi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import co.edu.icesi.services.UserService;

@Controller
public class UserController {

	private UserService userservice;
	
	@Autowired
	public UserController(UserService userservice ) {
		this.userservice= userservice;
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "/login";
	}
	
	@GetMapping("products/index")
	public String index(Model model) {
		return "/index";
	}
}
