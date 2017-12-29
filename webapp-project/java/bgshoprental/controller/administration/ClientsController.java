package bgshoprental.controller.administration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bgshoprental.entity.Client;
import bgshoprental.service.ClientService;

@Controller
@RequestMapping("/zarzadzanie")
public class ClientsController {

	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/klienci", method = RequestMethod.GET)
	public String clientList(Model model) {
		model.addAttribute("clients", clientService.getAllClients());
		return "zarzadzanie/klienci/clientList";
	}
	
	@RequestMapping(value = "/klienci", method = RequestMethod.POST)
	public String filteredClientList(Model model, @RequestParam String lastNamePortion) {
		List<Client> filteredListOfClients = clientService.findByLastNameContains(lastNamePortion);
		
		model.addAttribute("clients", filteredListOfClients);
		
		return "zarzadzanie/klienci/clientList";
	}
}
