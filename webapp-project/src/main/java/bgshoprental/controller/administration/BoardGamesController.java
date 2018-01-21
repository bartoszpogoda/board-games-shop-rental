package bgshoprental.controller.administration;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import bgshoprental.service.BoardGamesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/zarzadzanie/gry")
public class BoardGamesController {
	
	@Autowired
	BoardGamesService boardGamesService;
	
	@RequestMapping("/")
	public String listOfGames(Model model) {
		
		model.addAttribute("boardGameList", boardGamesService.findAllBoardGames());
		
		return "zarzadzanie/gry/gamesList";
	}
	
	@RequestMapping("/dodaj") 
	public String addGameForm(Model model, String supplierName) {
		model.addAttribute("supplierName", supplierName);
		
		return "zarzadzanie/gry/addGame";
	}
	
	@RequestMapping(value = "/dodaj", method = RequestMethod.POST)
	public String addGame(String title, String supplierName, int sellQuantity, BigDecimal sellPrice, int rentalQuantity, BigDecimal rentalPrice) {
		
		boardGamesService.addBoardGame(title, supplierName, sellQuantity, sellPrice, rentalQuantity, rentalPrice);
		
		return "redirect:/zarzadzanie/gry/";
	}
	
	@RequestMapping("/przeznacz")
	public String transferToRentalForm(Model model, @RequestParam("boardGameId") Integer boardGameId){ 
		
		model.addAttribute("boardGame", boardGamesService.findBoardGameById(boardGameId));
		
		return "zarzadzanie/gry/transferToRentalForm";
	}
	
	@RequestMapping(value = "/przeznacz", method = RequestMethod.POST)
	public String transferToRental(Integer boardGameId, int numberOfItemsToTransfer) {
		
		boardGamesService.transferItemsToRental(boardGameId, numberOfItemsToTransfer);
		
		return "redirect:/zarzadzanie/gry/";
	}
	
	@RequestMapping("/usun")
	public String removeItemsForm(Model model, Integer boardGameId) {
		
		model.addAttribute("boardGame", boardGamesService.findBoardGameById(boardGameId));
		
		return "zarzadzanie/gry/removeItemsForm";
	}

	@RequestMapping(value = "/usun", method = RequestMethod.POST)
	public String removeItems(Integer boardGameId, Integer quantityToRemoveRental, Integer quantityToRemoveSell) {
		
		boardGamesService.removeItems(boardGameId, quantityToRemoveRental, quantityToRemoveSell);
		
		return "redirect:/zarzadzanie/gry/";
	}


}
