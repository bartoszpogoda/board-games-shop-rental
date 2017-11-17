package bgshoprental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bgshoprental.service.ClientService;

@Controller
@RequestMapping("/zarzadzanie")
public class ManagmentController {
	
	@Autowired
	ClientService clientService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String managementPage() {
		return "management";
	}
	
	@RequestMapping(value = "/klienci", method = RequestMethod.GET)
    public String clientList(Model model){
        model.addAttribute("clients", clientService.listAllClients());
        return "clients";
    }
}
