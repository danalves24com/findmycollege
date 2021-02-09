package cscrape.scrape;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class write_to_database {
	private String insert  = "INSERT into list (Name, Type, Location, Programms, Price, About, StudyLanguage, Students, misc)\r\n" + 
			"VALUES (\"Test\", \"private\", \"Canada\", \"cs, mba\", 12894, \"decent\", \"English\", 3551, \"\");";
	
	
	String dbURL = "jdbc:mysql://10.0.1.23:3306/edi";
	String username = "root";
	String password = "";
	private Connection con = null;
	public Boolean exists(String name) throws SQLException {
		String sql = "SELECT * FROM list where Name='"+name+"'";
		 
		Statement statement = con.createStatement();
		ResultSet result = statement.executeQuery(sql);
		 
		int count = 0;
		String find = "";
		while (result.next()){
		    
			find = result.getString(1);
		}
		return find.equals(name);
	}
	public void Insert(String[] params) {
		String sql = "INSERT INTO list (Name, Type, Location, Programms, Price, About, StudyLanguage, Students, misc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(sql);
			Integer index = 1;
			for(String param : params) {
				statement.setString(index, param);
				index+=1;
			}
			int rowsInserted;
			rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void connect() {		
	      
	      try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	      try {
	         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/edi?useSSL=false", "root", "");	         
	         System.out.println("Connection is successful !!!!!");
	      } catch(Exception e) {
	         e.printStackTrace();
	      }	       
	}
}
