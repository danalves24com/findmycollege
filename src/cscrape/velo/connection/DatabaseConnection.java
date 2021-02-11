package cscrape.velo.connection;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cscrape.velo.profiles.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Class Connection.
 */
public class DatabaseConnection {
	
	/** The password. */
	private String url = "jdbc:mysql://10.0.1.23:3306/edi", username = "root", password = "";
	
	private static Connection con = null;
	
	/**
	 * Instantiates a new connection.
	 */
	public DatabaseConnection () {
		
	}
	
	/**
	 * Instantiates a new connection.
	 *
	 * @param URL the url
	 */
	public DatabaseConnection (String URL) {
		this.url = URL;
	}
	
	
	public void addEntry(Entry entry) {
		
		
		String sql = "INSERT INTO list (Name, Type, Location, Programms, Price, About, StudyLanguage, Students, misc, admissionRate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] values = {entry.getName()};
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(sql);
			Integer index = 1;
			for(String param : ( new String[] {"idk"})) {
				statement.setString(index, param==null?null:param.trim().replaceAll("'", ""));
				index+=1;
			}
			System.out.println(statement);
			int rowsInserted;
			rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Open connection.
	 */
	public void openConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try {
	    	con = DriverManager.getConnection(this.url, this.username, this.password);	         	         
	    } catch(Exception e) {
	         e.printStackTrace();
	      }	 
	}
	
	
	
}
