package ca.sheridancollege.database;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.beans.RectangleCube;


@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;
	
	public void insertRectangularCube(RectangleCube cube) {
		int width = cube.getWidth();
		int height = cube.getHeight();
		int length = cube.getLength();
		
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		String query="INSERT INTO volumes(width, height, length) VALUES (:width, :height, :length)";
		namedParameters.addValue("width", width);
		namedParameters.addValue("height", height);
		namedParameters.addValue("length", length);
		int rowsAffected= jdbc.update(query, namedParameters);
		if (rowsAffected> 0)
			System.out.println("Inserted student into database.");
		else
			System.out.println("Error inserting cube into the db");
	}
	
	public List<RectangleCube> getRectangularCubes() {
		String query = "SELECT * FROM volumes";
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<RectangleCube>(RectangleCube.class));
	}
	
	public boolean updateCube(RectangleCube cube) {
		String query = "UPDATE volumes SET width=:width, height=:height, length=:length WHERE id=:id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("width", cube.getWidth());
		namedParameters.addValue("height", cube.getHeight());
		namedParameters.addValue("length", cube.getLength());
		namedParameters.addValue("id", cube.getId());
		int rowsAffected = jdbc.update(query, namedParameters);
		return rowsAffected > 0;
	}
	
	public boolean deleteCube(long id) {
		String query = "DELETE from volumes WHERE id=:id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		int rowsAffected = jdbc.update(query, namedParameters);
		return rowsAffected > 0;
	}
	
	public RectangleCube getRectangularCube(long id) {
		String query = "SELECT * FROM volumes where id= :id";
		MapSqlParameterSource namedParameters= new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		List<RectangleCube> cubes = jdbc.query(query, namedParameters, new BeanPropertyRowMapper<RectangleCube>(RectangleCube.class));
		if(cubes.size() > 0)
			return cubes.get(0);
		return null;
	}
	
}

