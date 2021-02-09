package cscrape.main;
import java.sql.SQLException;

import api.src;
import cscrape.scrape.scrape_all_colleges;
import cscrape.scrape.scrape_all_erasmus;
import cscrape.scrape.screap_specific;
import cscrape.scrape.write_to_database;
public class main {

	public static void main(String[] args) {
		
		
		
		
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
 	 * 	scrape_all_colleges all = new scrape_all_colleges(new src().Wikipedia());
		all.scrape();
		all.getData();
 	 * 
 	 */
		
/*
 * 		
 
 		write_to_database db = new write_to_database();
		try {
			db.connect();
			System.out.println();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
 */
		

		scrape_all_erasmus er = new scrape_all_erasmus();
		try {
			er.begin();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
