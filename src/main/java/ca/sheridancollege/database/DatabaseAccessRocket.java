package ca.sheridancollege.database;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.Rocket;


@Repository
public class DatabaseAccessRocket {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void insertRocket(Rocket rocket) {
		String name = rocket.getName();
		int height = rocket.getHeight();
		int capacity = rocket.getCapacity();
		int reuses = rocket.getReuses();
		
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		String query="INSERT INTO rockets(name, height, capacity, reuses) VALUES (:name, :height, :capacity, :reuses)";
		namedParameters.addValue("name", name);
		namedParameters.addValue("height", height);
		namedParameters.addValue("capacity", capacity);
		namedParameters.addValue("reuses", reuses);
		int rowsAffected= jdbc.update(query, namedParameters);
		if (rowsAffected> 0)
			System.out.println("Inserted rocket into database.");
		else
			System.out.println("Error inserting rocket into the db");
	}
	
	public List<Rocket> getRockets() {
		String query = "SELECT * FROM rockets";
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Rocket>(Rocket.class));
	}
	
	public boolean updateRocket(Rocket rocket) {
		String name = rocket.getName();
		int height = rocket.getHeight();
		int capacity = rocket.getCapacity();
		int reuses = rocket.getReuses();
		
		String query = "UPDATE rockets SET name=:name, height=:height, capacity=:capacity, reuses=:reuses WHERE id=:id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("name", name);
		namedParameters.addValue("height", height);
		namedParameters.addValue("capacity", capacity);
		namedParameters.addValue("reuses", reuses);
		namedParameters.addValue("id", rocket.getId());
		int rowsAffected = jdbc.update(query, namedParameters);
		return rowsAffected > 0;
	}
	
	public boolean deleteRocket(long id) {
		String query = "DELETE from rockets WHERE id=:id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		int rowsAffected = jdbc.update(query, namedParameters);
		return rowsAffected > 0;
	}
	
	public Rocket getRocket(long id) {
		String query = "SELECT * FROM rockets where id= :id";
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		List<Rocket> cubes = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<Rocket>(Rocket.class));
		if(cubes.size() > 0)
			return cubes.get(0);
		return null;
	}
	
}

