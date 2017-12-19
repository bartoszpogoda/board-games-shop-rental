package bgshoprental.controller.administration;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;
import bgshoprental.entity.ExternalOrderStatus;
import bgshoprental.entity.Supplier;
import bgshoprental.service.BoardGamesService;
import bgshoprental.service.UserService;
import bgshoprental.service.external.ExternalOrderService;
import bgshoprental.service.external.SupplierService;

@Controller
@RequestMapping("/zarzadzanie/zamowienia/zewnetrzne")
public class ExternalOrdersController {

	@Autowired 
	ExternalOrderService externalOrderService;

	@Autowired 
	SupplierService supplierService;

	@Autowired 
	UserService userService;
	
	@Autowired 
	BoardGamesService boardGamesService;
	
	@RequestMapping(value = "/")
	public String externalOrderList(Model model) {

		model.addAttribute("externalOrderList", externalOrderService.getAll());

		return "externalOrderList";
	}
	
	@RequestMapping(value = "/dodaj")
	public String addExternalOrderForm(HttpSession session, Model model,
			@RequestParam("dostawca") String supplierName) {

		ExternalOrder externalOrder = new ExternalOrder();
		Supplier supplier = supplierService.getSupplierByName(supplierName);
		externalOrder.setSupplier(supplier);
		model.addAttribute("externalOrder", externalOrder);

		session.setAttribute("creatingExternalOrderForSupplier", supplier);

		return "addExternalOrder";
	}
	

	@RequestMapping(value = "/dodaj", method = RequestMethod.POST)
	public String addExternalOrder(HttpSession session, Model model, Principal principal,
			@ModelAttribute ExternalOrder externalOrder) {

		externalOrder.setCreationDate(Calendar.getInstance());
		externalOrder.setCreatedBy(userService.getEmployeeByEmail(principal.getName()));
		externalOrder.setStatus(ExternalOrderStatus.CREATED);

		Supplier supplier;
		if ((supplier = (Supplier) session.getAttribute("creatingExternalOrderForSupplier")) != null) {
			externalOrder.setSupplier(supplier);
		}

		externalOrderService.add(externalOrder);

		return "redirect:/zarzadzanie/zamowienia/zewnetrzne/";
	}
	

	@RequestMapping(value = "/{externalOrderId}")
	public String externalOrderDetails(Model model, @PathVariable("externalOrderId") Integer externalOrderId) {
		model.addAttribute("externalOrder", externalOrderService.getById(externalOrderId));
		model.addAttribute("elements", externalOrderService.getElements(externalOrderId));
		return "externalOrderDetails";
	}
	

	@RequestMapping(value = "/{externalOrderId}/dodaj", method = RequestMethod.GET)
	public String addExternalOrderElementForm(Model model, @PathVariable("externalOrderId") int externalOrderId) {
		
		model.addAttribute("boardGames", boardGamesService.getAllBoardGames());
		model.addAttribute("externalOrder", externalOrderService.getById(externalOrderId));
		
		return "addExternalOrderElement";
	}
	
	@RequestMapping(value = "/{externalOrderId}/dodaj", method = RequestMethod.POST)
	public String addExternalOrderElement(@PathVariable("externalOrderId") int externalOrderId, @RequestParam("boardGameId") int boardGameId, @RequestParam("quantity") int quantity, @RequestParam("price") BigDecimal price ) {
		
		ExternalOrderElement externalOrderElement = new ExternalOrderElement();
		externalOrderElement.setBoardGame(boardGamesService.getBoardGameById(boardGameId));
		externalOrderElement.setQuantity(quantity);
		externalOrderElement.setPrice(price);
		
		externalOrderService.addElement(externalOrderId, externalOrderElement);

		return "redirect:/zarzadzanie/zamowienia/zewnetrzne/" +externalOrderId;
	}
	
	@RequestMapping(value = "/{externalOrderId}/{elementId}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteExternalOrderElement(@PathVariable("externalOrderId") int externalOrderId,
			@PathVariable int elementId) {
		
		externalOrderService.removeExternalOrderElement(externalOrderId, elementId);
		
		return "Removed";
	}
	
}
