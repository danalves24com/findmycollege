package uni;

import java.util.Arrays;

public class profile {
	// some vars
	private String name, location, type, established, Web, price, students;	
	// constructor duh
	public profile(String Name) {
		this.name = Name.contains("Wikipedia")?Name.replace(" - Wikipedia", ""):Name;
	}
	// all the setters
	public void setLocation(String loc) {this.location = loc;}
	public void setType(String tye) {this.type = tye.toLowerCase().contains("private")?"private":"public";}
	public void setPrice(String p) {this.price = p;}	
	public void setStudents (String s) { this.students = s; }
	public void setEstablished (String year) {this.established = year;}
	public void setWeb(String site) {this.Web = site;}
	
	// getters and some evaluation stuff
	
	public String getProfile() {
		String[] prof = {name, location, type, price, students, established, Web};
		return Arrays.deepToString(prof);
	}
	
}
