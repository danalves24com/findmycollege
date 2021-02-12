package cscrape.velo.connection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import cscrape.velo.profiles.Entry;

// TODO: Auto-generated Javadoc
/**
 * The Class Connection.
 */
public class DatabaseConnection {
	
	/** The connection params. */
	private String url = "jdbc:mysql://localhost:3306/edi", username = "root", password = "";
	
	/** The con. */
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
	 * @param uName the u name
	 * @param psswd the psswd
	 */
	public DatabaseConnection (String URL, String uName, String psswd) {
		this.url = URL;
		this.username = uName;
		this.password = psswd;
	}
	
	
    /**
     * Convert entry to blob.
     *
     * @param entry the entry
     * @return the blob
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private Blob convertEntryToBlob(Entry entry) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();                 
		ObjectOutputStream objOstream = new ObjectOutputStream(baos);                 
		objOstream.writeObject(entry);                   
		objOstream.flush();                 
		objOstream.close();                   
		byte[] bArray = baos.toByteArray(); 
		
	    Blob blob = null;
	    try {
			blob = new SerialBlob(bArray);
		} catch (SerialException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    return blob;
    }
	
	
   
    
    
	
	/**
	 * Adds the entry.
	 *
	 * @param entry the entry
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void addEntry(Entry entry) throws IOException {				

		String sql = "INSERT INTO blobs (Name, data) VALUES (?, ?)";
		PreparedStatement statement;
		try {
			statement = con.prepareStatement(sql);
			statement.setString(1, (String) entry.getName());
			statement.setBlob(2, convertEntryToBlob(entry));
			System.out.println(statement);
			int rowsInserted;
			rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				// inserted - success
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
			con = DriverManager.getConnection(this.url, this.username, this.password);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
