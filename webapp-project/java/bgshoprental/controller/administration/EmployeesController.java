package bgshoprental.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bgshoprental.service.EmployeeService;

@Controller
@RequestMapping("/administracja")
public class EmployeesController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/pracownicy", method = RequestMethod.GET)
	public String employeeList(Model model) {
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "zarzadzanie/pracownicy/employeeList";
	}
	
	@RequestMapping(value = "/pracownicy/dodaj", method = RequestMethod.GET)
	public String addEmployeeForm() {
		return "zarzadzanie/pracownicy/addEmployee";
	}
	
	@RequestMapping(value = "/pracownicy/dodaj", method = RequestMethod.POST)
	public String addEmployeeForm(String firstName, String lastName, String email, String password) {
		
		employeeService.register(firstName, lastName, email, password);

		return "redirect:/administracja/pracownicy";
	}
	
}
