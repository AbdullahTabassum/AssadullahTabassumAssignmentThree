package ca.sheridancollege.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import ca.sheridancollege.beans.Credentials;
import ca.sheridancollege.beans.RectangleCube;
import ca.sheridancollege.database.DatabaseAccess;

@Controller
public class VolumeCalculator {
	private Message myMessage;
	private Credentials credentials;


	public VolumeCalculator(Message myMessage, Credentials credentials) {
		super();
		this.myMessage = myMessage;
		this.credentials = credentials;
	}
	
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/volume")
	public String getVolumes(Model model) {
		List<RectangleCube> cubes = da.getRectangularCubes();

		model.addAttribute("cubes", cubes);
		model.addAttribute("newCube", new RectangleCube());
		myMessage.printMessage();
        credentials.showCredentials();
		return "volumes.html";
	}
	
	@GetMapping("/addCube")
	public String doAddRectangleCube(Model model, @ModelAttribute RectangleCube newCube) {
		da.insertRectangularCube(newCube);
		// need to reset the newCube object and the cubes list
		model.addAttribute("cubes", da.getRectangularCubes());
		model.addAttribute("newCube", new RectangleCube());
		return "volumes.html";
	}
	
	@GetMapping("/editCube/{id}")
	public String editCube(Model model, @PathVariable int id) {
		// get the cube by id and show it in the editVolume page
		RectangleCube cube = da.getRectangularCube(id);
		model.addAttribute("selectedCube", cube);
		return "editVolume.html";
	}
	
	@GetMapping("/saveCube")
	public String updateCube(Model model, @ModelAttribute RectangleCube selectedCube) {
		// update the 
		boolean success = da.updateCube(selectedCube);
		model.addAttribute("cubes", da.getRectangularCubes());
		model.addAttribute("newCube", new RectangleCube());
		return "volumes.html";
	}
	
	@GetMapping("/deleteCube/{id}")
	public String deleteCube(Model model, @PathVariable int id) {
		boolean success = da.deleteCube(id);
		model.addAttribute("cubes", da.getRectangularCubes());
		model.addAttribute("newCube", new RectangleCube());
		return "volumes.html";
	}


}
