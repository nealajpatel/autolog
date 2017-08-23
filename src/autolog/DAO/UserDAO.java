package autolog.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import autolog.core.User;

public class UserDAO extends DataBaseDAO {
	
	private Connection con;
    private Statement st;
    private ResultSet rs;
    private User u;
	
	public UserDAO() { 
        // TODO: Should initialize 'con', probably
    }
	
	 /**
     * authenticates user if username and password are correct
     * 
     * @param String username of user
     * @param String password of the user
     * @return Boolean true if credentials are accurate. False if not
     */
    public User isAuthenticated(String username, String password) {
    	con = getRemoteConnection();
    	
    	u = null;
    	
     	//boolean authenticated = false;
     	try {
            Statement stmt = con.createStatement();
            try {
                String query = "use ebdb";
            	//String query = "select exists(select * from user where username='" + username + "' and password='" + password +"')";
                stmt.executeQuery(query);
            	query = "select exists(select * from User where username='" + username + "' and password='" + password +"')";
                
            	ResultSet rs = stmt.executeQuery(query);
            	
            	query = "select * from User where username='" + username + "' and password='" + password +"'";

            	rs = stmt.executeQuery(query);
            	
            	if (rs.next()) {
                    //authenticated = rs.getBoolean(1);
            		u = new User(username, password);
            		u.setID(rs.getInt(1));
            		u.setName(rs.getString(2));
            		u.setUsername(rs.getString(3));
            		u.setPassword(rs.getString(4));
            	}
            	
            	
            } finally {
                try { stmt.close(); } catch (Exception ignore) {}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try { con.close(); } catch (Exception ignore) {}
        }
     	return u;
    }
}
