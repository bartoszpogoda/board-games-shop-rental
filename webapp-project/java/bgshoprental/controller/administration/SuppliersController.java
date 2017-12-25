package bgshoprental.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bgshoprental.entity.Supplier;
import bgshoprental.service.BoardGamesService;
import bgshoprental.service.UserService;
import bgshoprental.service.external.ExternalOrderService;
import bgshoprental.service.external.SupplierService;

@Controller
@RequestMapping("/zarzadzanie/dostawcy")
public class SuppliersController {

	@Autowired
	SupplierService supplierService;

	@Autowired
	ExternalOrderService externalOrderService;
	
	@Autowired
	BoardGamesService boardGamesService;

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String listSuppliers(Model model) {
		model.addAttribute("supplierList", supplierService.getAllSuppliers());

		return "supplierList";
	}

	@RequestMapping("/dodaj")
	public String addSupplierForm(Model model) {
		model.addAttribute("supplier", new Supplier());

		return "addSupplier";
	}

	@RequestMapping(value = "/dodaj", method = RequestMethod.POST)
	public String addSupplier(Model model, @ModelAttribute Supplier supplier) {
		if (supplierService.saveSupplierIfDoesntExist(supplier)) {
			return "redirect:/zarzadzanie/dostawcy/";
		} else {
			model.addAttribute("supplier", supplier);
			model.addAttribute("errorExists", true);
			return "addSupplier";
		}

	}





}
