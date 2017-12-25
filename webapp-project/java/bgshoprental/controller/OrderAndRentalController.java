package bgshoprental.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bgshoprental.entity.Client;
import bgshoprental.service.ClientService;
import bgshoprental.service.UserService;
import bgshoprental.service.internal.InternalOrdersAndRentalService;

@Controller
@RequestMapping("/zamowienia")
public class OrderAndRentalController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	InternalOrdersAndRentalService internalOrdersAndRentalService;

	@RequestMapping("/")
	String orderAndRentalList(Model model, Principal loggedInUser) {
		
		String loggedInUserEmail = loggedInUser.getName();
		
		Client loggedInClient = userService.getClientByEmail(loggedInUserEmail);
		
		if(loggedInClient == null) {
			return "redirect:/zarzadzanie/klienci";
		} 
		
		model.addAttribute("orders", internalOrdersAndRentalService.findAllInternalOrdersByClient(loggedInClient));
		model.addAttribute("rentals", internalOrdersAndRentalService.findAllRentalsByClient(loggedInClient));
		
		return "orderAndRentalList";
	}
	
}
