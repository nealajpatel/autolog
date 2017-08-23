package autolog.DAO;

import java.sql.*;
import java.util.ArrayList;

import autolog.core.Vehicle;


public class VehicleDAO extends DataBaseDAO {
	private Connection con;
    private Statement st;
    private ResultSet rs;
    private Vehicle v;

    public VehicleDAO() { 
        // TODO: Should initialize 'con', probably
    }
    
    /*public Vehicle getVehicle(int userID) {
    	//if (con.isClosed() || con == null) {
		con = getRemoteConnection();
		//}
    	v = new Vehicle(0, 0, null, null, 0, 0);
    	try {
            st = con.createStatement();
            try {
            	String query = "use ebdb";
                rs = st.executeQuery(query);
                
                //query = "select * from Vehicle where ID='" + ID + "' and " + "userID= '" + userID + "'";
                query = "select * from Vehicle where userID= '" + userID + "'";
                
                rs = st.executeQuery(query);

                try {
             	   //ResultSetMetaData rsmd = rs.getMetaData();
             	   System.out.println(query);
                         //int columnsNumber = rsmd.getColumnCount();
             	   	if (rs.next()) {
                                 int id = rs.getInt(1);
                                 v.setID(id);
 
                                 int uID = rs.getInt(2);
                                 v.setUserID(uID);
                                 
                                 String make = rs.getString(3);
                                 v.setMake(make);
                                 
                                 String model = rs.getString(4);
                                 v.setModel(model);
                                 
                                 int year = rs.getInt(5);
                                 v.setYear(year);
                                 
                                 int mileage = rs.getInt(6);
                                 v.setMileage(mileage);
             	   	}
                                 
                } finally {
                try { rs.close(); } catch (Exception ignore) {}
                }
               
            } finally {
                try { st.close(); } catch (Exception ignore) {}
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
        } 
    	closeConnection();
    	return v;
    }*/


    public ArrayList<Vehicle> getAllVehicles(int userID) throws SQLException {
    	ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
    	con = getRemoteConnection();
    	v = new Vehicle(0, 0, null, null, 0, 0);
    	
    	try {
            st = con.createStatement();
            try {
            	String query = "use ebdb";
                rs = st.executeQuery(query);
                
                query = "select * from Vehicle where userID='" + userID + "'";
                rs = st.executeQuery(query);
                try {
                	   //System.out.println(query);
                	   	rs.beforeFirst();
                            while (rs.next()) {
                            		Vehicle tempV = new Vehicle(0, 0, null, null, 0, 0);

                                	int id = rs.getInt("ID");
                                    tempV.setID(id);
    
                                    int uID = rs.getInt("userID");
                                    tempV.setUserID(uID);
                                    
                                    String make = rs.getString("make");
                                    tempV.setMake(make);
                                    
                                    String model = rs.getString("model");
                                    tempV.setModel(model);
                                    
                                    int year = rs.getInt("year");
                                    tempV.setYear(year);
                                    
                                    int mileage = rs.getInt("mileage");
                                    tempV.setMileage(mileage);
                                    
                                    v = tempV;
                                    
                                    vehicleList.add(v);
                                    /*if (i > 1) System.out.print(",  ");
                                    String columnValue = rs.getString(i);
                                    System.out.print(rsmd.getColumnName(i) + "=" + columnValue);*/
                                    //System.out.println("");
                            }
                } finally {
                    try { rs.close(); } catch (Exception ignore) {}
                }
            } finally {
                try { st.close(); } catch (Exception ignore) {}
            }
        } finally {
            try { closeConnection(); } catch (Exception ignore) {}
        }
    	return vehicleList;
    }
    
    public void addVehicle(int userID, String make, String model, int year, int mileage) throws SQLException{
    	con = getRemoteConnection();
    	try {
            st = con.createStatement();
            try {
            	String query = "use ebdb";
                rs = st.executeQuery(query);
                
                query = "insert into Vehicle (userID, make, model, year, mileage)" + " values (?,?,?,?,?)";
                
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, userID);
                preparedStmt.setString(2, make);
                preparedStmt.setString(3, model);
                preparedStmt.setInt(4, year);
                preparedStmt.setInt(5, mileage);
                preparedStmt.executeUpdate();
                               
            }finally {
                try { rs.close(); } catch (Exception ignore) {}
            }
    	} finally {
            try { st.close(); } catch (Exception ignore) {}
        } 
    	closeConnection();
    }
    public void deleteVehicle(int ID) throws SQLException {
    	con = getRemoteConnection();
    	try {
            st = con.createStatement();
            try {
            	String query = "use ebdb";
                rs = st.executeQuery(query);
                
                query = "delete from Vehicle where ID='" + ID + "'";
                
                PreparedStatement preparedStmt = con.prepareStatement(query);
                
                preparedStmt.executeUpdate();
                               
            }finally {
                try { rs.close(); } catch (Exception ignore) {}
            }
    	} finally {
            try { st.close(); } catch (Exception ignore) {}
        } 
    	closeConnection();
    }
    
    public void editVehicle(int vehicleID, String make, String model, int year, int mileage) throws SQLException{
    	con = getRemoteConnection();
    	try {
            st = con.createStatement();
            try {
            	String sql = "use ebdb";
                rs = st.executeQuery(sql);
                
                sql = "update Vehicle set make = ? , "
                + "model = ? , "
                + "year = ? , "
                + "mileage = ? "
                + "where ID = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(sql);
                preparedStmt.setString(1, make);
                preparedStmt.setString(2, model);
                preparedStmt.setInt(3, year);
                preparedStmt.setInt(4, mileage);
                preparedStmt.setInt(5, vehicleID);
                preparedStmt.executeUpdate();
                               
            }finally {
                try { rs.close(); } catch (Exception ignore) {}
            }
    	} finally {
            try { st.close(); } catch (Exception ignore) {}
        } 
    	closeConnection();
    }
}
