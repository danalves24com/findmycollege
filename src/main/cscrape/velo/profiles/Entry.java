/*
 * 
 */
package cscrape.velo.profiles;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private URI source, page;

	/** The population. */
	private Integer population, admissionRate, studentEnrollment, academicStaff, internationalUndergradCost,
			internationalGradCost;

	public Integer getInternationalUndergradCost() {
		return internationalUndergradCost;
	}

	public void setInternationalUndergradCost(Integer internationalUndergradCost) {
		this.internationalUndergradCost = internationalUndergradCost;
	}

	public Integer getInternationalGradCost() {
		return internationalGradCost;
	}

	public void setInternationalGradCost(Integer internationalGradCost) {
		this.internationalGradCost = internationalGradCost;
	}

	/** The is international. */
	private Boolean isInternational, isSelective;
	/** The courses. */
	private ArrayList<Course> courses;

	/** The type. */
	private SchoolType type;

	
	
	public String getReport(String type) {
		Map<String, String> index = new HashMap<String, String>();
		Class<?> cl = this.getClass();
		List<Object> allObjects = new ArrayList<Object>();
		for (java.lang.reflect.Field f : cl.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				Object o = f.get(this);
				if (o != null) {
					index.put(f.getName(), String.valueOf(o));

					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		switch(type) {
		case "verbose":
			String rep="------------ <br>";
			for(Map.Entry<String, String> i : index.entrySet()) {
				rep+=(i.getKey() + "   >>   " + i.getValue()) + "<br>";
			}
			return rep;			
		default:
			return index.toString();
	}
		
	}
	
	
	
	/**
	 * Show.
	 */
	public void show() {
		Class<?> cl = this.getClass();
		List<Object> allObjects = new ArrayList<Object>();
		for (java.lang.reflect.Field f : cl.getDeclaredFields()) {
			f.setAccessible(true);
			try {
				Object o = f.get(this);
				if (o != null) {
					System.out.print(f.getName() + ":");
					System.out.print("\t" + o + "\n");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		for (Object o : allObjects) {

		}

	}

	/**
	 * Gets the checks if is selective.
	 *
	 * @return the checks if is selective
	 */
	public Boolean getIsSelective() {
		return isSelective;
	}

	/**
	 * Gets the student enrollment.
	 *
	 * @return the student enrollment
	 */
	public Integer getStudentEnrollment() {
		return studentEnrollment;
	}

	/**
	 * Sets the student enrollment.
	 *
	 * @param studentEnrollment the new student enrollment
	 */
	public void setStudentEnrollment(Integer studentEnrollment) {
		this.studentEnrollment = studentEnrollment;
	}

	/**
	 * Gets the academic staff.
	 *
	 * @return the academic staff
	 */
	public Integer getAcademicStaff() {
		return academicStaff;
	}

	/**
	 * Sets the academic staff.
	 *
	 * @param academicStaff the new academic staff
	 */
	public void setAcademicStaff(Integer academicStaff) {
		this.academicStaff = academicStaff;
	}

	/**
	 * Sets the checks if is selective.
	 *
	 * @param isSelective the new checks if is selective
	 */
	public void setIsSelective(Boolean isSelective) {
		this.isSelective = isSelective;
	}

	/**
	 * Gets the population.
	 *
	 * @return the population
	 */
	public Integer getPopulation() {
		return population;
	}

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

	public URI getSource() {
		return source;
	}

	public void setSource(URI source) {
		this.source = source;
	}

	public URI getPage() {
		return page;
	}

	public void setPage(URI page) {
		this.page = page;
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
