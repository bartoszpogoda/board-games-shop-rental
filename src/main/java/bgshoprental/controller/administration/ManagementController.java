package bgshoprental.controller.administration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/zarzadzanie")
public class ManagementController {

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String managementPage() {
		return "zarzadzanie/management";
	}

}
