package cscrape.scrape;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import uni.profile;

public class scrape_all_colleges {
	private write_to_database db;
	private String report = "";
	private ArrayList<String> schools = new ArrayList<String>();
	private ArrayList<profile> collegeData = new ArrayList<profile>();
	private String url = "";
	WebClient client = new WebClient();

	public scrape_all_colleges(String Url) {
		db = new write_to_database();
		db.connect();
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
								String[] intel = new String[9];
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
								
								System.out.println(db.exists(Profile.name));
								if(!collegeData.contains(Profile)) {
									if(db.exists(Profile.name)) {
										
									}
									else {
										db.Insert(new String[] {Profile.name, Profile.type, Profile.location, "", Profile.price, Profile.Web, "", Profile.students, ""});
										collegeData.add(Profile);										
									}
								}
//								System.out.println(Arrays.toString(tab.split("\n")));
							}
						}			
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
