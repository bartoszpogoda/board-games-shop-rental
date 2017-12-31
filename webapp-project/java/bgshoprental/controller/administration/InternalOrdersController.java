package bgshoprental.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value = "/{internalOrderId}/doodbioru", method = RequestMethod.POST)
	public String markAsReady(Model model, @PathVariable("internalOrderId") Integer internalOrderId) {
		
		internalOrderService.markAsReadyToPickup(internalOrderId);
		
		return "redirect:/zarzadzanie/zamowienia/wewnetrzne/" + internalOrderId;
	}
	
	@RequestMapping(value = "/{internalOrderId}/anuluj", method = RequestMethod.POST)
	public String markAsCanceled(Model model, @PathVariable("internalOrderId") Integer internalOrderId) {
		
		internalOrderService.processCancelation(internalOrderId);
		
		return "redirect:/zarzadzanie/zamowienia/wewnetrzne/" + internalOrderId;
	}
	
	@RequestMapping(value = "/{internalOrderId}/zrealizowane", method = RequestMethod.POST)
	public String markAsRealised(Model model, @PathVariable("internalOrderId") Integer internalOrderId) {
		
		internalOrderService.processRealization(internalOrderId);
		
		return "redirect:/zarzadzanie/zamowienia/wewnetrzne/" + internalOrderId;
	}
	
	
}
