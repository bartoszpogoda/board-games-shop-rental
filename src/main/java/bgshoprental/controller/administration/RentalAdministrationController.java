package bgshoprental.controller.administration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bgshoprental.service.internal.RentalService;

@Controller
@RequestMapping("/zarzadzanie/wypozyczenia")
public class RentalAdministrationController {
	
	@Autowired
	RentalService rentalService;
	
	@RequestMapping("/")
	public String rentalList(Model model) {

		model.addAttribute("rentals", rentalService.findAllRentals());
		
		return "zarzadzanie/wypozyczenia/rentalList";
	}
	
	@RequestMapping("/{rentalId}")
	public String rentalDetails(Model model, @PathVariable Integer rentalId) {
		
		model.addAttribute("rental", rentalService.findRentalById(rentalId));
		
		return "zarzadzanie/wypozyczenia/rentalDetails";
	}

	@RequestMapping(value = "/{rentalId}/anuluj", method = RequestMethod.POST)
	public String cancelRental(@PathVariable Integer rentalId) {
		
		rentalService.cancelRental(rentalId);
		
		return "redirect:/zarzadzanie/wypozyczenia/" + rentalId;
	}
	
	@RequestMapping(value = "/{rentalId}/zrealizowane", method = RequestMethod.POST)
	public String realizeRental(@PathVariable Integer rentalId) {
		
		rentalService.realizeRental(rentalId);

		return "redirect:/zarzadzanie/wypozyczenia/" + rentalId;
	}

	@RequestMapping(value = "/{rentalId}/odebrane", method = RequestMethod.POST)
	public String markAsReceived(@PathVariable Integer rentalId) {
		
		rentalService.markAsReceived(rentalId);

		return "redirect:/zarzadzanie/wypozyczenia/" + rentalId;
	}
	

	@RequestMapping(value = "/{rentalId}/doodbioru", method = RequestMethod.POST)
	public String markAsReadyToReceive(@PathVariable Integer rentalId) {
		
		rentalService.markAsReadyToReceive(rentalId);

		return "redirect:/zarzadzanie/wypozyczenia/" + rentalId;
	}
	
	
}
