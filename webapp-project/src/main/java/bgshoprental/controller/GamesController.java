package bgshoprental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bgshoprental.service.BoardGamesService;

@Controller()
@RequestMapping("/gry")
public class GamesController {
	
	@Autowired
	BoardGamesService boardGamesService;
	
	@RequestMapping("/dostepne")
	String allGamesListPage(Model model) {
		model.addAttribute("availableGames", boardGamesService.getAvailableBoardGames());
		return "availableGames";
	}
}
