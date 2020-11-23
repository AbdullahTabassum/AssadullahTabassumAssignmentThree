package ca.sheridancollege.controllers;

import org.springframework.stereotype.Component;

@Component
public class MessageImpl implements Message {
	private String name = "Assadullah Tabassum 991541528";

	@Override
	public void printMessage() {
		System.out.println(name);
	} 
}