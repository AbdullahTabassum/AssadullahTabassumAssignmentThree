package ca.sheridancollege.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rocket {
	long id;
	private String name;
	private int height;
	private int capacity;
	private int reuses;
}
