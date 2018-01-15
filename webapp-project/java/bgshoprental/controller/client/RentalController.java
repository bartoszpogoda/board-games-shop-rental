package bgshoprental.controller.client;

import java.io.Console;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.Client;
import bgshoprental.entity.Rental;
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
	
	@RequestMapping(value="/{rentalId}/przedluz", method = RequestMethod.GET) 
	public String extendRentalForm(Model model, Principal principal, @PathVariable Integer rentalId) {
		
		Client client = clientService.getClientFromPrincipal(principal);
		
		boolean authorized = rentalService.doesBelongToClient(rentalId, client);
		
		if(authorized) {
			Rental rental = rentalService.findRentalById(rentalId);
			
			model.addAttribute("maxExtensionDays", rentalService.getMaxExtensionDays(rentalId));
			model.addAttribute("rental", rental);
			
			return "zamowienia/extendRental";
			
		} else {
			return "redirect:/zamowienia/";	
		}
	}
	
	@RequestMapping(value="/{rentalId}/przedluz", method = RequestMethod.POST) 
	public String extendRental(Principal principal, Integer rentalId, int numberOfDaysToExtend) {
		
		Client client = clientService.getClientFromPrincipal(principal);
		
		rentalService.extendRental(client, rentalId, numberOfDaysToExtend);
		
		return "redirect:/zamowienia/";	
	}
	
	@RequestMapping(value="/{rentalId}/kup", method = RequestMethod.GET) 
	public String buyRentGameForm(Model model, Principal principal, @PathVariable Integer rentalId) {
		
		Client client = clientService.getClientFromPrincipal(principal);
		
		boolean authorized = rentalService.doesBelongToClient(rentalId, client);
		
		if(authorized) {
			Rental rental = rentalService.findRentalById(rentalId);
			
			model.addAttribute("rental", rental);
			
			return "zamowienia/rentGameBuyConfirmation";
			
		} else {
			return "redirect:/zamowienia/";	
		}
	}
	
	@RequestMapping(value="/{rentalId}/kup", method = RequestMethod.POST) 
	public String buyRentGame(Principal principal, @PathVariable Integer rentalId) {
		
		Client client = clientService.getClientFromPrincipal(principal);
		
		rentalService.processBuyRentGame(client, rentalId);
		
		return "redirect:/zamowienia/";	
	}
}
