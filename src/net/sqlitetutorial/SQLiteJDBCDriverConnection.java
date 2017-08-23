 
package net.sqlitetutorial;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
 
/**
 *
 * @author sqlitetutorial.net
 */
public class SQLiteJDBCDriverConnection {
     /**
     * Connect to a sample database
     */
    public Connection connect() {
        Connection conn = null;
        try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            // db parameters
            String url = "jdbc:sqlite:/Users/nealpatel/test.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            //System.out.println("Connection to SQLite has been established.");
            
            /*try {
                Statement stmt = conn.createStatement();
                try {
                    String query = "select * from user";
                    ResultSet rs = stmt.executeQuery(query);
                    try {
                    	   ResultSetMetaData rsmd = rs.getMetaData();
                    	   System.out.println(query);
                                int columnsNumber = rsmd.getColumnCount();
                                while (rs.next()) {
                                    for (int i = 1; i <= columnsNumber; i++) {
                                        if (i > 1) System.out.print(",  ");
                                        String columnValue = rs.getString(i);
                                        System.out.print(rsmd.getColumnName(i) + "=" + columnValue);
                                    }
                                    System.out.println("");
                                }
                    } finally {
                        try { rs.close(); } catch (Exception ignore) {}
                    }
                } finally {
                    try { stmt.close(); } catch (Exception ignore) {}
                }
            } finally {
                try { conn.close(); } catch (Exception ignore) {}
            }*/
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } /*finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }*/
		return conn;
    }
    /**
     * authenticates user if username and password are correct
     * 
     * @param String username of user
     * @param String password of the user
     * @return Boolean true if credentials are accurate. False if not
     */
    public static boolean isAuthenticated(String username, String password) {
    	SQLiteJDBCDriverConnection connection = new SQLiteJDBCDriverConnection();
     	Connection conn = connection.connect();
     	boolean authenticated = false;
     	try {
            Statement stmt = conn.createStatement();
            try {
                String query = "select exists(select * from user where username='" + username + "' and password='" + password +"')";
                ResultSet rs = stmt.executeQuery(query);
      
                authenticated = rs.getBoolean(1);
               
            } finally {
                try { stmt.close(); } catch (Exception ignore) {}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try { conn.close(); } catch (Exception ignore) {}
        }
     	return authenticated;
    }
    
    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
        //SQLiteJDBCDriverConnection connection = new SQLiteJDBCDriverConnection();
    	//connection.connect();
    	boolean works = isAuthenticated("napatel5", "neal");
    	if (works == true) {
    		System.out.println("True");
    	}
    }*/
}
 

