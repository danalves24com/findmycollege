package cscrape.scrape;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;

import uni.profile;

public class scrape_all_erasmus {
	private WebClient client = new WebClient();
	private write_to_database db;
	String[] programs = {"Computer Science", "Business", "Engineering", "Managment"};
	String type = "private";
	Integer MaxCost = 50000, schoolsScraped = 0;	
	public scrape_all_erasmus() {
		db = new write_to_database();
		
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setCssEnabled(true);
		client.getOptions().setThrowExceptionOnScriptError(false);
	}
	public void begin() throws InterruptedException, SQLException {
		db.connect();
		for(Integer p = 0 ; p <= 254 ; p += 1) {
			HtmlPage page = null;
			try {
				
				
				
				
				page = client.getPage(new api.src().erasmus(p));
				List<Object> links = page.getByXPath("/html/body/div[1]/div[1]/div[3]/div[3]/div/div/table/tbody/tr/td[2]/h3/a");
				for(Object l : links) {
					HtmlAnchor lk = (HtmlAnchor) l;
					l = (String)lk.getAttribute("href");										
					page = client.getPage((String) l);
					HtmlPage ePage = page;
					List<Object> name = page.getByXPath("/html/body/div[1]/div[1]/div[3]/div[2]/div[1]/div/ul[1]/li[1]");										
					HtmlListItem nameData = (HtmlListItem) name.get(0);					
					profile Profile = new profile(nameData.asText().split(":")[1].trim().replaceAll("'", ""));
					if(db.exists(Profile.name)) {
						
					}
					else {																			
						String aug = nameData.asText().split(":")[1].trim().replaceAll(" ", "_");
						page = client.getPage("https://en.wikipedia.org/wiki/"+aug);
						
						// develop a programs list from erasmus
						
						String progList = "";
						String epPath = "/html/body/div[1]/div[1]/div[3]/div[2]/div[2]/ul/li/div/h3/a";
						List<?> progs = ePage.getByXPath(epPath);
						if(progs.size() > 0) {
							for(Object prog : progs) {
								String progName = ((HtmlAnchor)prog).asText();
								progName = progName.replaceAll(" ", "_");
								progList+=progName+", ";
								
							}
						}
						progList = progList.trim();
						System.out.println(progList);
						Profile.setPrograms(progList);
						
						// develope a certain school profile based on wiki response
						if(page.getWebResponse().getStatusCode()!=404) {									
							String tablePath = "/html/body/div[3]/div[3]/div[5]/div[1]/table";	
							List<?> table = page.getByXPath(tablePath);
							if(table.size() > 0 ) {
								for(Object tb : table) {
									String tab = (((HtmlTable)tb).asText());
									String[] dt = tab.split("\n");
	
									for(String d : dt) {
										try {
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
										catch (Exception e) {e.printStackTrace();}
									}
	//								System.out.println(Arrays.toString(tab.split("\n")));
								}
							
							}
						}
						else {															
							Profile.location = ((String) ePage.getByXPath("/html/body/div[1]/div[1]/div[3]/div[2]/div[1]/div/ul[1]/li[2]").get(0)).split(": ")[1];
							Profile.type = "?";
						}
	
												
						System.out.println(Profile.programs);
						db.Insert(new String[] {Profile.name, Profile.type, Profile.location, Profile.programs, Profile.price, Profile.Web, "", Profile.students, Profile.acr});
				}
			}
			} catch (FailingHttpStatusCodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			// /html/body/div[1]/div[1]/div[3]/div[3]/div/div/table/tbody/tr[1]/td[2]/h3/a

			
			
			
		}
		db.close();
	}
}
