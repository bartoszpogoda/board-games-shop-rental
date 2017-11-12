package bgshoprental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bgshoprental.entity.BoardGame;
import bgshoprental.repository.BoardGamesRepository;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	
	@Autowired
	BoardGamesRepository boardGamesRepository;
	
	@RequestMapping(value = "/")
	public @ResponseBody String test() {
		
		Iterable<BoardGame> findAll = boardGamesRepository.findAll();
		
		findAll.forEach(boardGame -> System.out.println(boardGame.getSupplier().getSupplierName()));
		
		return "Test";
	}
}
