package ca.sheridancollege.beans;

import org.springframework.stereotype.Component;

@Component
public class CredentialsImpl implements Credentials {

	@Override
	public void showCredentials() {
		System.out.println("Assadullah Tabassum 991541528");		
	}
	
}
