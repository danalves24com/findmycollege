package cscrape.velo.profiles;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.http.entity.SerializableEntity;

import cscrape.velo.connection.DatabaseConnection;
import cscrape.velo.profiles.CourseLevel;


/**
 * The Class Entry.
 */
// this class represents a school
public class Entry implements Serializable {
    public Entry() {

 
	}


	/** The location. */
	private String name, location;
	
	/** The population. */
	private Integer population;
	
	/** The courses. */
	private ArrayList<Course> courses;
	
	/** The type. */
	private SchoolType type;
	
	
	/**
	 * Gets the population.
	 *
	 * @return the population
	 */
	public Integer getPopulation() {
		return population;
	}



	/**
	 * Instantiates a new entry.
	 */

		
	
	/**
	 * Correct string.
	 *
	 * @param input the input
	 * @return the string
	 */
	private String correctString(String input) {
		String correctedInput;
		correctedInput = input.trim();
		return correctedInput;
	}
	
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}


	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	

	/**
	 * Sets the population.
	 *
	 * @param population the new population
	 */
	public void setPopulation(Integer population) {
		this.population = population;
	}


	/**
	 * Gets the courses.
	 *
	 * @return the courses
	 */
	public ArrayList<Course> getCourses() {
		return courses;
	}


	/**
	 * Adds the course.
	 *
	 * @param course the course
	 */
	public void addCourse(Course course) {
		this.courses.add(course);
	}

	/**
	 * Sets the courses.
	 *
	 * @param courses the new courses
	 */
	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}




	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public SchoolType getType() {
		return type;
	}


	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(SchoolType type) {
		this.type = type;
	}

	
	/**
	 * Upload self.
	 *
	 * @param con the con
	 */
	public void UploadSelf(DatabaseConnection con) {
		try {
			con.addEntry(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}
