package cscrape.main;
import java.sql.SQLException;

import api.SendEmail;
import api.src;
import cscrape.scrape.scrape_all_colleges;
import cscrape.scrape.scrape_all_erasmus;
import cscrape.scrape.screap_specific;
import cscrape.scrape.screape_american;
import cscrape.scrape.write_to_database;
import cscrape.velo.connection.DatabaseConnection;
import cscrape.velo.profiles.Entry;
public class main {

	public static void main(String[] args) throws SQLException {
		
		
		
		
		// TODO Auto-generated method stub
/*
 		screap_specific all = new screap_specific("https://en.wikipedia.org/wiki/Lists_of_universities_and_colleges_by_country");
		all.scrape();
		all.getData();
		System.out.println(all.getBestColleges());
				write_to_database db = new write_to_database();
		db.connect();
		// (Name, Type, Location, Programms, Price, About, StudyLanguage, Students, misc)
		db.Insert(new String[] {"AAP", "Private", "Prague", "All", "512", "pretty damn good", "English", "160", ""});
 */

		
		
		
		
		
 	/* 	GET ALL FROM WIKI
 	 * 
 	 * 	
 	 * 
 	 */
		
/*
 * 		
 
 
 
 		screape_american sa = new screape_american();
		sa.begin();
 
 
 
 		scrape_all_colleges all = new scrape_all_colleges(new src().Wikipedia());

		
		
		all.scrape();
		all.getData();
		

		scrape_all_erasmus er = new scrape_all_erasmus();
		try {
			er.begin();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 */


		
		
		
		DatabaseConnection con = new DatabaseConnection();
		con.openConnection();
		
		
		Entry school = new Entry();
		
		
		school.setName("Harward");
		school.setLocation("not here");
		school.UploadSelf(con);
		

		
	}

}
