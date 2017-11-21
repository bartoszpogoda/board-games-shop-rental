package bgshoprental.listener;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

public class UserLoggedListener implements ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	HttpSession httpSession;
	
	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent e) {
		String loggedUsserEmail = e.getAuthentication().getName();
		System.out.println(loggedUsserEmail + " logged in");
	}

}
