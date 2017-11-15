package bgshoprental.controller;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.Client;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.repository.ClientRepository;

@Controller
@RequestMapping(value = "/test")
public class TestController {
	
	@Autowired
	BoardGamesRepository boardGamesRepository;
	

	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@RequestMapping(value = "/")
	public @ResponseBody String test() {
		
		Iterable<BoardGame> findAll = boardGamesRepository.findAll();
		
		findAll.forEach(boardGame -> System.out.println(boardGame.getSupplier().getSupplierName()));
		
		Client findUserByEmail = clientRepository.findUserByEmail("mstokelle@homestead.com");
		
		
		
		return findUserByEmail.getFirstName();
	}
}
