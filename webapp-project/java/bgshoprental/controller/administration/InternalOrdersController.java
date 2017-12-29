package bgshoprental.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bgshoprental.service.internal.InternalOrderService;

@Controller
@RequestMapping("/zarzadzanie/zamowienia/wewnetrzne")
public class InternalOrdersController {
	
	@Autowired
	InternalOrderService internalOrderService;
	
	@RequestMapping(value = "/")
	public String internalOrderList(Model model) {

		model.addAttribute("internalOrders", internalOrderService.findAllInternalOrders());

		return "zarzadzanie/zamowienia/wewnetrzne/internalOrderList";
	}

	@RequestMapping(value = "/{internalOrderId}")
	public String externalOrderDetails(Model model, @PathVariable("internalOrderId") Integer internalOrderId) {
		model.addAttribute("internalOrder", internalOrderService.findInternalOrderById(internalOrderId));
		model.addAttribute("elements", internalOrderService.findElementsForInternalOrderId(internalOrderId));
		return "zarzadzanie/zamowienia/wewnetrzne/internalOrderDetails";
	}
}
