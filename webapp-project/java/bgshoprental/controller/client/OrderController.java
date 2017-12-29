package bgshoprental.controller.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bgshoprental.controller.form.InternalOrderForm;
import bgshoprental.entity.BoardGame;
import bgshoprental.entity.InternalOrderElement;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.service.BoardGamesService;
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
	
	@RequestMapping("/utworz")
	public String internalOrderBuilderList(Model model) {
		
		model.addAttribute("elements", internalOrderBuilder.getElements());
		
		return "zamowienia/internalOrderBuilderList";
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
