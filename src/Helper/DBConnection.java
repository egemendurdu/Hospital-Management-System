package Helper;
import java.sql.*;

public class DBConnection {
	private String user_name = "root";
	private String password="";
	private String db_name="hospital";
	private String host = "localhost";
	private int port = 3306;
	
	private Connection con = null;
	private Statement statement = null;
	
	
	public DBConnection() {
	
	}
	
	public Connection connDb() {
		
		String url = "jdbc:mysql://"+host+":"+port+"/"+db_name;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch(ClassNotFoundException ex) {
			System.out.println("Driver Bulunamadı...");
		}
				
		try {
			con = DriverManager.getConnection(url, user_name, password);
			System.out.println("Connection Success...");
			
		} catch (SQLException e) {
			System.out.println("Connection Failed...");
		}
		return con;
		
	}

	
}
