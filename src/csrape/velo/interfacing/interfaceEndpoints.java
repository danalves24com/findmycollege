package csrape.velo.interfacing;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cscrape.velo.connection.DatabaseConnection;
import cscrape.velo.profiles.Entry;

@SpringBootApplication
@RestController
public class interfaceEndpoints {
	@GetMapping("/stat")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("running");
	}
	
	@GetMapping("/f/{school}")
	public String school(@PathVariable(value = "school") String name) {
		try {
			return (new DatabaseConnection()).getEntry(name).getReport();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	
	@GetMapping("/dump")
	public String dump(@RequestParam(value = "name", defaultValue = "World") String name) {
		String dump = "";
		try {
			ArrayList<Entry> out = (new DatabaseConnection()).getAll();
			for(Entry ref : out) {
				dump+=ref.getReport();
			}
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.format(dump);
	}
}
