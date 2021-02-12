package cscrape.velo.profiles;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.ArrayList;

import org.apache.http.entity.SerializableEntity;

import cscrape.velo.connection.DatabaseConnection;
import cscrape.velo.profiles.CourseLevel;


// TODO: Auto-generated Javadoc
/**
 * The Class Entry.
 */
// this class represents a school
public class Entry implements Serializable {
    
    /**
     * Instantiates a new entry.
     */
    public Entry() {

 
	}


	/** The location. */
	private String name, location, gender, selectionType;
	

	/** The population. */
	private Integer population, admissionRate;
	
	/** The is international. */
	private Boolean isInternational;
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
	 *
	 * @param input the input
	 * @return the string
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
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}



	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}



	/**
	 * Gets the selection type.
	 *
	 * @return the selection type
	 */
	public String getSelectionType() {
		return selectionType;
	}



	/**
	 * Sets the selection type.
	 *
	 * @param selectionType the new selection type
	 */
	public void setSelectionType(String selectionType) {
		this.selectionType = selectionType;
	}



	/**
	 * Gets the admission rate.
	 *
	 * @return the admission rate
	 */
	public Integer getAdmissionRate() {
		return admissionRate;
	}



	/**
	 * Sets the admission rate.
	 *
	 * @param admissionRate the new admission rate
	 */
	public void setAdmissionRate(Integer admissionRate) {
		this.admissionRate = admissionRate;
	}



	/**
	 * Gets the checks if is international.
	 *
	 * @return the checks if is international
	 */
	public Boolean getIsInternational() {
		return isInternational;
	}



	/**
	 * Sets the checks if is international.
	 *
	 * @param isInternational the new checks if is international
	 */
	public void setIsInternational(Boolean isInternational) {
		this.isInternational = isInternational;
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
