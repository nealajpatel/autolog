package autolog.DAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class DataBaseDAO {
	
	private static Connection con;
    private static Statement st;
    private static ResultSet rs;
    
    protected static Connection getRemoteConnection() {
    	//if (System.getProperty("autologdb.ccdoctkvqcql.us-east-2.rds.amazonaws.com") != null) {
          try {
        	  //System.out.println("Here");
          Class.forName("com.mysql.jdbc.Driver");
    	  //System.out.println("Here2");
    	  //System.out.println("Here");
          String dbName = "ebdb";
          String userName = "nealajpatel";
          String password = "";
          //String hostname = "autologdb.ccdoctkvqcql.us-east-2.rds.amazonaws.com";
          String hostname = "aa1k7m7c0lkotq2.ccdoctkvqcql.us-east-2.rds.amazonaws.com";
          String port = "3306";
          //String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
          
          String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
          //logger.trace("Getting remote connection with connection string from environment variables.");
          //System.out.println("Connection string = '" + jdbcUrl + "'");
          Connection con = DriverManager.getConnection(jdbcUrl);
          //logger.info("Remote connection successful.");
          return con;
        }
        catch (ClassNotFoundException e) { e.toString();}
        catch (SQLException e) { e.toString();}
        //}
        return null;
      }
    
    public static Connection getConnection() { 
		try {
			//Class.forName("org.sqlite.JDBC");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            // db parameters
            String url = "jdbc:sqlite:/Users/nealpatel/test.db";
        	//String url = "jdbc:mysql://autologdb.ccdoctkvqcql.us-east-2.rds.amazonaws.com:3306/autologdb?user=napatel5&password=Simran22";
            // create a connection to the database
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static void closeConnection() { 
        if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
}
