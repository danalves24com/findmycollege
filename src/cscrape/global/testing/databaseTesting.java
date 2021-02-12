package cscrape.global.testing;



import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import cscrape.velo.connection.DatabaseConnection;
import cscrape.velo.profiles.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Class databaseTesting.
 */
class databaseTesting {

	/**
	 * Test.
	 *
	 * @throws SQLException the SQL exception
	 */
	@Test
	void test() throws SQLException {
		
		DatabaseConnection con = new DatabaseConnection();
		con.openConnection();
		
		
		Entry school = new Entry();
		school.setName("Harward");
		school.setLocation("not here");
		school.UploadSelf(con);
		
		
		
		con.getEntry("Harward");
		
	}

}
