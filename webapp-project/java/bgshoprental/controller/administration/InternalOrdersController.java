package bgshoprental.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bgshoprental.service.internal.InternalOrdersAndRentalService;

@Controller
@RequestMapping("/zarzadzanie/zamowienia/wewnetrzne")
public class InternalOrdersController {
	
	@Autowired
	InternalOrdersAndRentalService internalOrdersAndRentalService;
	
	@RequestMapping(value = "/")
	public String internalOrderList(Model model) {

		model.addAttribute("internalOrders", internalOrdersAndRentalService.findAllInternalOrders());

		return "internalOrderList";
	}
}
