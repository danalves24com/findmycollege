package cscrape.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import cscrape.global.scrapers.ICU_scraper;
import cscrape.velo.connection.DatabaseConnection;
import csrape.velo.interfacing.college_list;

@SpringBootApplication
@RestController
public class Main {

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException {
		(new DatabaseConnection("jdbc:mysql://localhost:3306/edi", "root", "Saniroot")).openConnection();
		ICU_scraper icu = new ICU_scraper();
		icu.srape();
		SpringApplication.run(college_list.class, args);
	}


}
