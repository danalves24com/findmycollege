package cscrape.scrape;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import uni.profile;

class writer {
	private String data;
	public writer(String data) {
		this.data = data;
	}
	public void write() {	    
	    FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream("colleges.md");
			byte[] strToBytes = this.data.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    	    
	}
}

public class screap_specific {
	
	
	// USER PARAMETERS 
	// TODO add to args?
	
	String[] programs = {"Computer Science", "Business", "Engineering", "Managment"};
	String type = "private";
	Integer MaxCost = 50000, schoolsScraped = 0;		
	
	
	
	private String report = "";
	private ArrayList<String> schools = new ArrayList<String>();
	private ArrayList<profile> collegeData = new ArrayList<profile>();
	private String url = "";
	WebClient client = new WebClient();

	public screap_specific(String Url) {
		this.url = Url;
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setCssEnabled(true);
		client.getOptions().setThrowExceptionOnScriptError(false);
	}
	public void scrape() {
		try {
			HtmlPage page = client.getPage(this.url);
			String path = "//*[@id=\"mw-content-text\"]/div/ul/li/a";
			List<?> links = page.getByXPath(path);
//			System.out.println(Arrays.deepToString(links.toArray()));
			for(Object link : links) {
				HtmlAnchor lk = (HtmlAnchor)link;
				String href = lk.getAttribute("href").contains("http")?lk.getAttribute("href"):"https://en.wikipedia.org"+lk.getAttribute("href");
				if(href.contains("List")) {
//					System.out.println(href);
					schools.add(href);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getData() {
		String path = "/html/body/div[3]/div[3]/div[5]/div[1]/ul/li/a"; 
		for(String link0 : schools) {
			try {
				HtmlPage page = client.getPage(link0);			
				List<?> links = page.getByXPath(path);
				
				
				for(Object link : links) {   // here I have the link for a school
					HtmlAnchor lk = (HtmlAnchor)link;
					String href = lk.getAttribute("href").contains("http")?lk.getAttribute("href"):"https://en.wikipedia.org"+lk.getAttribute("href");
					if(!href.contains("List") && href.contains("wikipedia")) {
						System.out.println(href);						
						HtmlPage data = client.getPage(href);
						String school = data.asText();
						profile Profile = new profile(data.getTitleText());

						
						String tablePath = "/html/body/div[3]/div[3]/div[5]/div[1]/table";
						
						List<?> table = data.getByXPath(tablePath);
						if(table.size() > 0 ) {
							for(Object tb : table) {
								String tab = (((HtmlTable)tb).asText());
								String[] dt = tab.split("\n");
								for(String d : dt) {
									d=d.toLowerCase();
									if(d.contains("location")) {										
										Profile.setLocation(d.split("\t")[1]);
									}
									if(d.contains("type")) {										
										Profile.setType(d.split("\t")[1]);
									}
									if(d.contains("established")) {																			
										Profile.setEstablished(d.split("\t")[1]);
									}
									if(d.contains("web")) {
										Profile.setWeb(d.split("\t")[1]);
									}
									if(d.contains("tuition") || d.contains("price")) {
										Profile.setPrice(d.split("\t")[1]);
									}
									if(d.contains("students")) {
										Profile.setStudents(d.split("\t")[1]);
									}
								}
//								System.out.println(Arrays.toString(tab.split("\n")));
							}
						}
						Profile.generateScore(programs, data, type);
						String singularReport = Profile.getProfile();
						report += singularReport;
						//collegeData.add(Profile);
						schoolsScraped+=1;
						
						// THIS GOES BYE BYE ONCE I DONT NEED TO TEST
						if(schoolsScraped%4==0) {
							writer w = new writer(report);
							w.write();
						}
						
						System.out.println("-------- COUNT: "+schoolsScraped);
						
						
//						collegeData.add(data);												
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public String getBestColleges() {
		ArrayList<String> allBest = new ArrayList<String>();
		ArrayList<Double> pScores = new ArrayList<Double>();

		Double bestScore = 0.0;
		Integer bestScoreIndex = null;		
		Integer index = 0;
		for(Double score : pScores) {
			if(score > bestScore) bestScoreIndex = index;
			index+=1;
		}
		String bestSchool = schools.get(bestScoreIndex);
		return bestSchool;
	}
}
