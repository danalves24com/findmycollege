package cscrape.velo.profiles;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Course.
 */
public class Course {
	
	/** The place. */
	String name, place;
	
	/** The cost estimate. */
	private Integer costEstimate;
	
	/** The level. */
	private CourseLevel level;
	
	/** The qualifications. */
	ArrayList<String> qualifications;
	
	/**
	 * Instantiates a new course.
	 */
	public Course() {}

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
	 * Gets the place.
	 *
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Sets the place.
	 *
	 * @param place the new place
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	/**
	 * Gets the cost estimate.
	 *
	 * @return the cost estimate
	 */
	public Integer getCostEstimate() {
		return costEstimate;
	}

	/**
	 * Sets the cost estimate.
	 *
	 * @param costEstimate the new cost estimate
	 */
	public void setCostEstimate(Integer costEstimate) {
		this.costEstimate = costEstimate;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public CourseLevel getLevel() {
		return level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(CourseLevel level) {
		this.level = level;
	}

	/**
	 * Gets the qualifications.
	 *
	 * @return the qualifications
	 */
	public ArrayList<String> getQualifications() {
		return qualifications;
	}

	/**
	 * Sets the qualifications.
	 *
	 * @param qualifications the new qualifications
	 */
	public void setQualifications(ArrayList<String> qualifications) {
		this.qualifications = qualifications;
	}
	
	
	
}
