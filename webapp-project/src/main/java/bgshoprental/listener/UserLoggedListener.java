package bgshoprental.listener;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import bgshoprental.entity.Employee;
import bgshoprental.repository.EmployeeRepository;

public class UserLoggedListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	HttpSession httpSession;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent e) {
		String loggedUsserEmail = e.getAuthentication().getName();
		System.out.println(loggedUsserEmail + " logged in");
		
		Employee loggedEmployee = employeeRepository.findByEmail(loggedUsserEmail);
		if(loggedEmployee != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println(dateFormat.format(loggedEmployee.getHireDate().getTime()));
			
			System.out.println(loggedEmployee.isManager() ? "Manager logged in" : "Employee logged in");
		}
	}

}
