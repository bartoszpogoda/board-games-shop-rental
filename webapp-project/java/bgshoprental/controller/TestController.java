package bgshoprental.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bgshoprental.entity.ExternalOrder;
import bgshoprental.entity.ExternalOrderElement;
import bgshoprental.entity.InternalOrderElement;
import bgshoprental.repository.BoardGamesRepository;
import bgshoprental.repository.ClientRepository;
import bgshoprental.repository.ExternalOrderRepository;
import bgshoprental.util.InternalOrderBuilder;

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
	
	@Autowired
	ExternalOrderRepository externalOrderRepository;
	
	@Autowired
	InternalOrderBuilder internalOrderBuilder;
	
	@RequestMapping(value = "/")
	public @ResponseBody String test(Model model) {
		

//		Iterable<ExternalOrder> xOrders = externalOrderRepository.findAll();
//		
//		xOrders.forEach((order) -> {
//			List<ExternalOrderElement> elements = order.getElements();
//			elements.forEach((element) -> {
//				System.out.println("Element " + element.getBoardGame().getTitle());
//			});
//		});
		
		InternalOrderElement internalOrderElement = new InternalOrderElement();
		
		internalOrderElement.setBoardGame(boardGamesRepository.findAll().iterator().next());
		
		internalOrderBuilder.addElement(internalOrderElement);
		
		
		return "abc";
		
//		Iterable<BoardGame> findAll = boardGamesRepository.findAll();
//		
//		findAll.forEach(boardGame -> System.out.println(boardGame.getSupplier().getName()));
//		
//		Client findUserByEmail = clientRepository.findUserByEmail("mstokelle@homestead.com");
//		
//		System.out.println(request.getUserPrincipal());
		
//		return findUserByEmail.getFirstName();
	}
}
