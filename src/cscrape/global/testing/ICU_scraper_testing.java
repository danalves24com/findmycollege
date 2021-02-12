package cscrape.global.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.jupiter.api.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import cscrape.global.scrapers.ICU_scraper;
import cscrape.velo.connection.DatabaseConnection;

// TODO: Auto-generated Javadoc
/**
 * The Class ICU_scraper_testing.
 */
class ICU_scraper_testing {

	/**
	 * Test.
	 *
	 * @throws FailingHttpStatusCodeException the failing http status code exception
	 * @throws MalformedURLException the malformed URL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		DatabaseConnection con = new DatabaseConnection();
		con.openConnection();
		ICU_scraper icu = new ICU_scraper();
		icu.srape();
	}

}
