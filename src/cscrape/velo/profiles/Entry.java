package cscrape.velo.profiles;
import java.util.ArrayList;

import cscrape.velo.connection.DatabaseConnection;
import cscrape.velo.profiles.CourseLevel;

// this class represents a school
public class Entry {
	private String name, location;
	private Integer population;
	private ArrayList<Course> courses;
	private SchoolType type;
	
	
	public Integer getPopulation() {
		return population;
	}



	public Entry() {
		// ANYTHING WE NEED TO DO HERE? 
	}
		
	
	private String correctString(String input) {
		String correctedInput;
		correctedInput = input.trim();
		return correctedInput;
	}
	
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	

	public void setPopulation(Integer population) {
		this.population = population;
	}


	public ArrayList<Course> getCourses() {
		return courses;
	}


	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public void setCourses(ArrayList<Course> courses) {
		this.courses = courses;
	}




	public SchoolType getType() {
		return type;
	}




	public void setType(SchoolType type) {
		this.type = type;
	}


	
	
	
	
	
	public void UploadSelf(DatabaseConnection con) {
		con.addEntry(this);
	}
	
	
	
	
	
	
}
