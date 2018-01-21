package bgshoprental.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bgshoprental.entity.Employee;
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
	
	@RequestMapping(value = "/pracownicy/{employeeId}/usun", method = RequestMethod.GET)
	public String removeEmployeeConfirmation(Model model, @PathVariable Integer employeeId) {
		
		Employee employee = employeeService.findEmployeeById(employeeId);
		model.addAttribute("employee", employee);
		
		return "zarzadzanie/pracownicy/removeEmployeeConfirmation";
	}
	
	@RequestMapping(value = "/pracownicy/{employeeId}/usun", method = RequestMethod.POST)
	public String removeEmployee(Model model, @PathVariable Integer employeeId) {
		
		employeeService.removeEmployee(employeeId);

		return "redirect:/administracja/pracownicy";
	}
	
}
