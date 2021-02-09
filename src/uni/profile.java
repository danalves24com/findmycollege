package uni;

import java.util.Arrays;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class profile {
	
	private String mod(String in) {
		if (in.contains("(")) {
			in = in.substring(0, in.indexOf("("));
		}
		if(in.contains("[")) {
			return in.substring(0, in.indexOf("["));
		}
		return in.trim();
	}
	
	
	// some vars
	public String name, location, type, established, Web, price, students;	
	private Double score;
	// constructor duh
	public profile(String Name) {
		this.name = Name.contains("Wikipedia")?Name.replace(" - Wikipedia", ""):Name;
	}
	
	// all the setters
	public void setLocation(String loc) {this.location = loc.contains(",")?loc.split(",")[loc.split(",").length-1].trim():loc.trim();}
	public void setType(String tye) {this.type = tye.toLowerCase().contains("private")?"private":"public";}
	public void setPrice(String p) {this.price = mod(p);}	
	public void setStudents (String s) { this.students = mod(s.replaceAll(",", "")); }		
	public void setEstablished (String year) {this.established = year;}
	public void setWeb(String site) {this.Web = site!=""&&site.contains(".")?site:null;}
	
	// helper methods	
	private Double low = 0.1E-10;
	private Double high = 1- low;
	private Double clip(Double in) {
		return in<=low?low:in>=high?high:in;
	}
	
	private Double prod(Double[] nums) {
		if(nums.length == 0) {
			return 0.0;
		}
		else if (nums.length == 1) {
			return nums[0];
		}
		Double out = 1.0;
		for(Double num : nums) {
			out*=clip(num);
		}
		return out;
	}
	
	// getters and some evaluation stuff	
	public String getProfile() {
		if(this.score!=low) {
		String prof = "# " + name + "(" + (Math.round(100*this.score)) + "%)\n";				
			prof += location != null?"Location \t - " + location+"\n":""; 
			prof += type != null?"Type \t" + type+"\n":""; 
			prof += price != null?"Price \t" + price+"\n":""; 
			prof += students != null?"Students \t" + students+"\n":""; 
			prof += Web != null?"Web \t - " + Web+"\n":""; 
			return prof;
		}
		else {
			return "";
		}
		
	}
		
	private Double intersetsScore(String[] programs, HtmlPage school) {
		Double programScore = 0.0;
		for(String program : programs) {
			if(school.asText().contains(program)) {
				programScore+=1;
			}
		}
		programScore /= programs.length;				
		return clip(programScore);
		
	}
	
	private Double typeScore(String type) {
		if(this.type == null) {
			return 1.0;
		}
		return type.toLowerCase().equals(this.type.toLowerCase())?high:low;
	}
	
	public void generateScore(String[] programs, HtmlPage school, String type) {
		Double interestScore = intersetsScore(programs, school);
		Double typeScore = type==null?low:typeScore(type);
		Double[] scores = {interestScore, typeScore};
		this.score = clip(prod(scores));
	}
	
	
	public Double getScore() {return this.score;}
	
}
