package bgshoprental.controller.client;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.Client;
import bgshoprental.service.BoardGamesService;
import bgshoprental.service.ClientService;
import bgshoprental.service.internal.RentalService;

@Controller
@RequestMapping("/wypozyczenia")
public class RentalController {
	
	@Autowired
	BoardGamesService boardGamesService;
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	RentalService rentalService;
	
	@RequestMapping("/utworz") 
	public String addElementForm(Model model, @RequestParam("boardGameId") Integer boardGameId) {
		
		BoardGame boardGame = boardGamesService.findBoardGameById(boardGameId);
		model.addAttribute("boardGame", boardGame);
		
		return "zamowienia/addRental";
	}
	
	@RequestMapping(value="/utworz", method = RequestMethod.POST) 
	public String addRental(Principal principal, Integer boardGameId, Integer numberOfDays) {
		
		Client client = clientService.getClientFromPrincipal(principal);
		
		rentalService.createRental(client, boardGameId, numberOfDays);
		
		return "redirect:/zamowienia/";
	}
}
