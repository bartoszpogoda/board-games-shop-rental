package bgshoprental.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bgshoprental.service.SupplierService;

@Controller
@RequestMapping("/zarzadzanie/dostawcy")
public class SuppliersController {

	@Autowired
	SupplierService supplierService;

	@RequestMapping("/")
	public String listSuppliers(Model model) {
		model.addAttribute("supplierList", supplierService.getAllSuppliers());

		return "supplierList";
	}

}
