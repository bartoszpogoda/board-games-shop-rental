package bgshoprental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bgshoprental.entity.Client;
import bgshoprental.service.ClientService;

@Controller
public class LoginController {
	
	@Autowired
	ClientService clientService;
	
	@RequestMapping("/login")
    String loginPage(){
        return "login";
    }
	
	@RequestMapping("/rejestracja")
	String registerPage() {
		return "register";
	}
	
	@RequestMapping(value="/rejestracja", method = RequestMethod.POST)
	String register(Client client, String password) {
		clientService.register(client, password);
		
		return "redirect:/login";
	}
	
	
}
