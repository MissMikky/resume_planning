package ch.makery.address;
import java.sql.Connection; 
import java.sql.DriverManager; 


public class MySQLconnection {
	public static Connection Connector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connexion =  DriverManager.getConnection("jdbc:mysql://localhost/tni","root","");
			return connexion;
		} catch (Exception e){
			System.out.println(e);
			return null;
		}
	}
}
