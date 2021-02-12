package cscrape.global.scrapers;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

// TODO: Auto-generated Javadoc
/**
 * The Interface scraper.
 */
public interface scraper {
	
	/**
	 * Srape.
	 *
	 * @throws FailingHttpStatusCodeException the failing http status code exception
	 * @throws MalformedURLException the malformed URL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void srape() throws FailingHttpStatusCodeException, MalformedURLException, IOException;
	
	/**
	 * All scraped.
	 *
	 * @return the integer
	 */
	public Integer allScraped();
	
	/**
	 * Generate report from page.
	 *
	 * @param page the page
	 */
	public void generateReportFromPage(HtmlPage page);
}
