/*
 * 
 */
package cscrape.global.scrapers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

import cscrape.velo.connection.DatabaseConnection;
import cscrape.velo.profiles.Entry;
import cscrape.velo.profiles.SchoolType;

// TODO: Auto-generated Javadoc
/**
 * The Class ICU_scraper.
 */
public class ICU_scraper implements scraper {

	/** The facilities and services. */
	private String nameXpath = "/html/body/div[3]/div[4]/div[1]/div/div[2]/table/tbody/tr[1]/td/a",
			admissionsTable = "/html/body/div[3]/div[7]/div[2]/div/div[2]/table/tbody/tr",
			sizeAndProfileTable = "/html/body/div[3]/div[8]/div[2]/div/div[1]/table/tbody/tr",
			facilitiesAndServices = "/html/body/div[3]/div[9]/div[2]/div/div",
			internationalCosts = "/html/body/div[3]/div[6]/div[2]/div/table/tbody/tr[2]/td/strong/text()[2]";

	/** The country codes. */
	private String[] countryCodes = { "AF", "AX", "AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW",
			"AU", "AT", "AZ", "BS", "BH", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BQ", "BA", "BW", "BV",
			"BR", "IO", "UM", "VG", "VI", "BN", "BG", "BF", "BI", "KH", "CM", "CA", "CV", "KY", "CF", "TD", "CL", "CN",
			"CX", "CC", "CO", "KM", "CG", "CD", "CK", "CR", "HR", "CU", "CW", "CY", "CZ", "DK", "DJ", "DM", "DO", "EC",
			"EG", "SV", "GQ", "ER", "EE", "ET", "FK", "FO", "FJ", "FI", "FR", "GF", "PF", "TF", "GA", "GM", "GE", "DE",
			"GH", "GI", "GR", "GL", "GD", "GP", "GU", "GT", "GG", "GN", "GW", "GY", "HT", "HM", "VA", "HN", "HK", "HU",
			"IS", "IN", "ID", "CI", "IR", "IQ", "IE", "IM", "IL", "IT", "JM", "JP", "JE", "JO", "KZ", "KE", "KI", "KW",
			"KG", "LA", "LV", "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MO", "MK", "MG", "MW", "MY", "MV", "ML", "MT",
			"MH", "MQ", "MR", "MU", "YT", "MX", "FM", "MD", "MC", "MN", "ME", "MS", "MA", "MZ", "MM", "NA", "NR", "NP",
			"NL", "NC", "NZ", "NI", "NE", "NG", "NU", "NF", "KP", "MP", "NO", "OM", "PK", "PW", "PS", "PA", "PG", "PY",
			"PE", "PH", "PN", "PL", "PT", "PR", "QA", "XK", "RE", "RO", "RU", "RW", "BL", "SH", "KN", "LC", "MF", "PM",
			"VC", "WS", "SM", "ST", "SA", "SN", "RS", "SC", "SL", "SG", "SX", "SK", "SI", "SB", "SO", "ZA", "GS", "KR",
			"SS", "ES", "LK", "SD", "SR", "SJ", "SZ", "SE", "CH", "SY", "TW", "TJ", "TZ", "TH", "TL", "TG", "TK", "TO",
			"TT", "TN", "TR", "TM", "TC", "TV", "UG", "UA", "AE", "GB", "US", "UY", "UZ", "VU", "VE", "VN", "WF", "EH",
			"YE", "ZM", "ZW" };

	/** The client. */
	private WebClient client = new WebClient();

	/**
	 * Instantiates a new IC U scraper.
	 */
	public ICU_scraper() {
		client.getOptions().setJavaScriptEnabled(true);
		client.getOptions().setCssEnabled(true);
		client.getOptions().setThrowExceptionOnScriptError(false);
	}

	/**
	 * Gets the uni data.
	 *
	 * @param link the link
	 * @return the uni data
	 * @throws FailingHttpStatusCodeException the failing http status code exception
	 * @throws MalformedURLException          the malformed URL exception
	 * @throws IOException                    Signals that an I/O exception has
	 *                                        occurred.
	 */
	private void getUniData(String link) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		HtmlPage uni = client.getPage(link);

	}

	/**
	 * Api.
	 *
	 * @param cc the cc
	 * @return the string
	 */
	private String api(String cc) {
		return "https://www.4icu.org/" + cc + "/a-z/";

	}

	/**
	 * Srape.
	 *
	 * @throws FailingHttpStatusCodeException the failing http status code exception
	 * @throws MalformedURLException          the malformed URL exception
	 * @throws IOException                    Signals that an I/O exception has
	 *                                        occurred.
	 * @throws SQLException                   the SQL exception
	 */
	@Override
	public void srape() throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException {
		HtmlPage s;
		for (String cc : countryCodes) {
			HtmlPage unis = null;
			try {
				unis = client.getPage(api(cc.toLowerCase()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (unis != null) {
				List<?> unisForCountry = unis.getByXPath("/html/body/div[2]/div/div[2]/div/table/tbody/tr/td[2]/a");
				for (Object uni : unisForCountry) {

					Entry prev = (new DatabaseConnection()).getEntry(((HtmlAnchor) uni).asText());
					if (prev != null) {
					} else {

						String link = ((HtmlAnchor) uni).getAttribute("href");
						link = "https://www.4icu.org" + link;
						System.out.println(link);
						s = client.getPage(link);
						try {
							if (s.getWebResponse().getStatusCode() != 404) {
								generateReportFromPage(s);
							} else {
							}
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			} else {
			}
		}

	}

	/**
	 * All scraped.
	 *
	 * @return the integer
	 */
	@Override
	public Integer allScraped() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Checks if is alphaic.
	 *
	 * @param str the str
	 * @return true, if is alphaic
	 */
	private static boolean isAlphaic(String str) {
		String regex = "^[A-Za-z]+$";
		Pattern p = Pattern.compile(regex);
		if (str == null) {
			return false;
		}
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * Generate report from page.
	 *
	 * @param page the page
	 * @throws SQLException
	 */
	@Override
	public void generateReportFromPage(HtmlPage page) throws SQLException {
		Entry E = new Entry();
		List<?> names = page.getByXPath(nameXpath);
		Boolean isValid = false;
		for (Object op : names) {
			String nameString = ((HtmlAnchor) op).asText();
			if (isAlphaic(nameString.replaceAll("\\W+", ""))) {
				E.setName(nameString);
				isValid = true;
			}
		}

		if (isValid) {
			try {
				E.setSource((new URI(page.getBaseURI())));
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<?> params = page.getByXPath(admissionsTable);
			for (Object param : params) {
				try {
					HtmlTableRow row = ((HtmlTableRow) param);
					String[] rowString = row.asText().split("\t");

					String title = rowString[0].toLowerCase().trim(), body = rowString[1].toLowerCase().trim();

					if (body.equals("not reported") || body.equals("not available")) {
					} else {

						if (title.contains("gender")) {
							E.setGender(body.contains("coed") ? "coed" : body.contains("men") ? "men" : "women");
						}

						else if (title.contains("international")) {
							E.setIsInternational(rowString[1].contains("yes"));
						}

						else if (title.contains("rate")) {
							body = body.replace("%", "");
							if (body.contains("-")) {
								body = body.split("-")[0]; // the the lower ACC Rate
							} else {
							}
							E.setAdmissionRate(Integer.valueOf(body));

						}

						else if (title.contains("selection")) {
							E.setSelectionType(body);
							E.setIsSelective(body.contains("yes"));
						}

						else {

						}

					}
				} catch (Exception e) {

					e.printStackTrace();
				}
			}

			List<?> profile = page.getByXPath(sizeAndProfileTable);
			for (Object dat : profile) {
				try {
					HtmlTableRow row = ((HtmlTableRow) dat);
					String[] r = row.asText().split("\t");
					String title = r[0].toLowerCase().trim(), body = r[1].toLowerCase().trim();

					if (!body.equals("not reported")) {

						if (title.contains("enrollment")) {
							String e = body;
							if (e.contains("-")) {
								e = e.split("-")[1].replaceAll(",", "");
							} else {
							}
							E.setStudentEnrollment(Integer.valueOf(e));
						}

						else if (title.contains("staff")) {
							String s = body;
							if (s.contains("-")) {
								s = s.split("-")[1].replaceAll(",", "");
							}
							E.setAcademicStaff(Integer.valueOf(s));
						}

						else if (title.contains("type")) {
							E.setType(body.equals("private") ? SchoolType.PRIVATE : SchoolType.PUBLIC);
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			List<?> costs = page.getByXPath(internationalCosts);
			if (costs.size() > 0) {
				try {
					E.setInternationalUndergradCost(Integer
							.valueOf(String.valueOf(costs.get(0).toString()).split("-")[1].replaceAll("\\D", "")));
				} catch (Exception e) {
				}
				try {
					E.setInternationalUndergradCost(Integer
							.valueOf(String.valueOf(costs.get(1).toString()).split("-")[1].replaceAll("\\D", "")));
				} catch (Exception e) {
				}
				// System.out.println(costs.get(1));
				// E.setInternationalUndergradCost(Integer.valueOf(costsList[0]));
			}

			E.UploadSelf(new DatabaseConnection());
			E.show();
			try {
				TimeUnit.SECONDS.sleep(4);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

		}

	}

}
