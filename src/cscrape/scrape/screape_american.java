package cscrape.scrape;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import GET.get;
import api.src;
import uni.profile;

public class screape_american {
	private get GET = new get();
	private write_to_database db = new write_to_database();
	private Integer gin(String val) {
		String st = "";
		for(String c : val.split("")) {
			Integer i = 0;
			try {
				i = Integer.parseInt(c);
				st+=c;
			}
			catch (Exception e){}
		}
		return Integer.getInteger(st);
	}
	public void begin() {
		db.connect();
		URL url = null;
		for(Integer p = 0 ; p < 92 ; p+=1) {
			try {
				url = new URL(new src().US(p));
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try (InputStream is = url.openStream();
			     JsonReader rdr = Json.createReader(is)) {
					     JsonObject obj = rdr.readObject();
			    JsonArray results = obj.getJsonArray("results");
			    for (JsonObject result : results.getValuesAs(JsonObject.class)) {
			    	try {
			    		profile P = new profile(result.getString("name"));
						if(db.exists(result.getString("name"))) {}
						else {
							
							JsonObject location = result.getJsonObject("address");
							P.setLocation(location.getString("city")+"-"+location.getString("state")+"-US");							
							P.setType(result.getString("universityType").toString());
							try {
								P.setPrice(result.getString("outOfStateTuition"));
							}
							catch (Exception e) {								
							}
							P.setACR(result.getString("admissionRate").contains("%")?result.getString("admissionRate").substring(0, result.getString("admissionRate").indexOf("%")):null);							
							try {								
								P.setStudents(Integer.toString(result.getInt("undergraduates")));
							}
							catch (Exception e) {
//								e.printStackTrace();
							}
							
							
							
							String data[] = {P.name,P.type,P.location,"",P.price,"","English",P.students,"",P.acr};		    	
							System.out.println(Arrays.deepToString(data));		        		        
						    System.out.println("-----------");
						    db.Insert(data);
							}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	catch (Exception e) {
			    		e.printStackTrace();
			    	}
			    }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
