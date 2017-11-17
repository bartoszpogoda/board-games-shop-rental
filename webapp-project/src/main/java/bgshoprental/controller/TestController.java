package bgshoprental.controller;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bgshoprental.entity.BoardGame;
import bgshoprental.entity.Client;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.repository.ClientRepository;

/***
 * 
 * @author Student225988
 *
 * Thats temporary controller used only for testing
 */
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
	public @ResponseBody String test(HttpServletRequest request) {
		
		Iterable<BoardGame> findAll = boardGamesRepository.findAll();
		
		findAll.forEach(boardGame -> System.out.println(boardGame.getSupplier().getSupplierName()));
		
		Client findUserByEmail = clientRepository.findUserByEmail("mstokelle@homestead.com");
		
		System.out.println(request.getUserPrincipal());
		
		return findUserByEmail.getFirstName();
	}
}
