package ca.sheridancollege.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import ca.sheridancollege.beans.Credentials;
import ca.sheridancollege.beans.Rocket;
import ca.sheridancollege.database.DatabaseAccessRocket;

@Controller
public class ReusableRocketController {
	private Message myMessage;
	private Credentials credentials;


	public ReusableRocketController(Message myMessage, Credentials credentials) {
		super();
		this.myMessage = myMessage;
		this.credentials = credentials;
	}
	@Autowired
	private DatabaseAccessRocket da;
	
	@GetMapping("/rockets")
	public String getRockets(Model model) {
		List<Rocket> rockets = da.getRockets();

		model.addAttribute("rockets", rockets);
		model.addAttribute("newRocket", new Rocket());
		myMessage.printMessage();
        credentials.showCredentials();
		return "rockets.html";
	}
	
	@GetMapping("/addRocket")
	public String doAddRocket(Model model, @ModelAttribute Rocket newRocket) {
		da.insertRocket(newRocket);
		model.addAttribute("rockets", da.getRockets());
		model.addAttribute("newRocket", new Rocket());
		return "rockets.html";
	}
	
	@GetMapping("/editRocket/{id}")
	public String editRocket(Model model, @PathVariable int id) {
		Rocket rocket = da.getRocket(id);
		model.addAttribute("selectedRocket", rocket);
		return "editRocket.html";
	}
	
	@GetMapping("/saveRocket")
	public String updateRocket(Model model, @ModelAttribute Rocket selectedRocket) {
		boolean success = da.updateRocket(selectedRocket);
		model.addAttribute("rockets", da.getRockets());
		model.addAttribute("newRocket", new Rocket());
		return "rockets.html";
	}
	
	@GetMapping("/deleteRocket/{id}")
	public String deleteRocket(Model model, @PathVariable int id) {
		boolean success = da.deleteRocket(id);
		model.addAttribute("rockets", da.getRockets());
		model.addAttribute("newRocket", new Rocket());
		return "rockets.html";
	}
}
