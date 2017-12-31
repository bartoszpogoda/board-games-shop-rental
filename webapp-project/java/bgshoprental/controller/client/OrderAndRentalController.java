package bgshoprental.controller.client;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bgshoprental.entity.Client;
import bgshoprental.entity.InternalOrder;
import bgshoprental.service.UserService;
import bgshoprental.service.internal.InternalOrderService;
import bgshoprental.service.internal.RentalService;

@Controller
@RequestMapping("/zamowienia")
public class OrderAndRentalController {

	@Autowired
	UserService userService;

	@Autowired
	InternalOrderService internalOrderService;

	@Autowired
	RentalService rentalService;

	@RequestMapping("/")
	String orderAndRentalList(Model model, Principal loggedInUser) {

		String loggedInUserEmail = loggedInUser.getName();

		Client loggedInClient = userService.getClientByEmail(loggedInUserEmail);

		if (loggedInClient == null) {
			return "redirect:/zarzadzanie/klienci";
		}

		model.addAttribute("orders", internalOrderService.findAllInternalOrdersByClient(loggedInClient));
		model.addAttribute("rentals", rentalService.findAllRentalsByClient(loggedInClient));
		
		model.addAttribute("internalOrderService", internalOrderService);

		return "zamowienia/orderAndRentalList";
	}

	@RequestMapping("/{internalOrderId}")
	String internalOrderDetails(Model model, Principal loggedInUser, @PathVariable("internalOrderId") Integer internalOrderId) {
		String loggedInUserEmail = loggedInUser.getName();

		Client loggedInClient = userService.getClientByEmail(loggedInUserEmail);

		if (loggedInClient == null) {
			return "redirect:/zarzadzanie/klienci";
		}
		
		InternalOrder internalOrder = internalOrderService.findInternalOrderByIdAndClient(internalOrderId, loggedInClient);

		if(internalOrder == null) {
			return "redirect:/";
		}
		
		model.addAttribute("internalOrder", internalOrder);
		model.addAttribute("elements", internalOrder.getElements());

		return "zamowienia/internalOrderDetails";
	}

}
