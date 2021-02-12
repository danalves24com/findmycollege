package cscrape.velo.profiles;

import java.util.ArrayList;

public class Course {
	String name, place;
	private Integer costEstimate;
	private CourseLevel level;
	ArrayList<String> qualifications;
	
	public Course() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getCostEstimate() {
		return costEstimate;
	}

	public void setCostEstimate(Integer costEstimate) {
		this.costEstimate = costEstimate;
	}

	public CourseLevel getLevel() {
		return level;
	}

	public void setLevel(CourseLevel level) {
		this.level = level;
	}

	public ArrayList<String> getQualifications() {
		return qualifications;
	}

	public void setQualifications(ArrayList<String> qualifications) {
		this.qualifications = qualifications;
	}
	
	
	
}
