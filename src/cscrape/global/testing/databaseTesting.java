package cscrape.global.testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import cscrape.velo.connection.DatabaseConnection;

class databaseTesting {

	@Test
	void test() throws SQLException {
		
		DatabaseConnection con = new DatabaseConnection();
		con.openConnection();
		con.getEntry("Harward");
		
	}

}
