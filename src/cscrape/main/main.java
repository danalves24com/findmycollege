package cscrape.main;
import cscrape.scrape.screap_specific;
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		screap_specific all = new screap_specific("https://en.wikipedia.org/wiki/Lists_of_universities_and_colleges_by_country");

		all.scrape();
		all.getData();
		System.out.println(all.getBestColleges());
	}

}
