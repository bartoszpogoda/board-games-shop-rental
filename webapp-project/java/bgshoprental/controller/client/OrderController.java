package bgshoprental.controller.client;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bgshoprental.controller.form.InternalOrderForm;
import bgshoprental.entity.BoardGame;
import bgshoprental.entity.Client;
import bgshoprental.entity.InternalOrderElement;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.service.BoardGamesService;
import bgshoprental.service.ClientService;
import bgshoprental.service.UserService;
import bgshoprental.service.internal.InternalOrderService;
import bgshoprental.util.InternalOrderBuilder;

@Controller
@RequestMapping("/zamowienia")
public class OrderController {
	
	@Autowired
	InternalOrderBuilder internalOrderBuilder;
	
	@Autowired
	InternalOrderService internalOrderService;
	
	@Autowired
	BoardGamesService boardGamesService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ClientService clientService;
	
	@RequestMapping("/utworz")
	public String internalOrderBuilderList(Model model) {
		
		model.addAttribute("elements", internalOrderBuilder.getElements());
		
		return "zamowienia/internalOrderBuilderList";
	}
	
	@RequestMapping(value = "/utworz/{elementId}", method = RequestMethod.DELETE)
	public @ResponseBody String internalOrderBuilderRemoveElement(Model model, @PathVariable("elementId") Integer elementId) {
		
		internalOrderBuilder.removeElement(elementId);
		
		return "Removed";
	}
	
	@RequestMapping("/utworz/potwierdz")
	public String internalOrderConfirmation(Model model) {
		
		model.addAttribute("totalPrice", internalOrderBuilder.calculateTotalPrice());
		
		return "zamowienia/internalOrderConfirmation";
	}
	
	@RequestMapping(value="/utworz", method = RequestMethod.POST)
	public String createInternalOrder(Principal loggedInUser) {
		
		Client loggedInClient = clientService.getClientFromPrincipal(loggedInUser);

		if (loggedInClient == null) {
			return "redirect:/zarzadzanie/klienci";
		}
		
		internalOrderService.createAndSaveInternalOrder(internalOrderBuilder.getElements(), loggedInClient);
		
		internalOrderBuilder.clear();
		
		return "redirect:/zamowienia/";
	}
	
	@RequestMapping("/dodaj") 
	public String addElementForm(Model model, @RequestParam("boardGameId") Integer boardGameId) {
		
		BoardGame boardGame = boardGamesService.findBoardGameById(boardGameId);
		model.addAttribute("boardGame", boardGame);
		
		return "zamowienia/addInternalOrderElement";
	}
	
	@RequestMapping(value = "/dodaj", method = RequestMethod.POST)
	public String addElement(InternalOrderForm internalOrderForm) {
		
		InternalOrderElement element = internalOrderService.createElement(internalOrderForm.getBoardGameId(), internalOrderForm.getQuantity());
		
		internalOrderBuilder.addElement(element);
		
		return "redirect:/zamowienia/utworz";
	}
	
}
