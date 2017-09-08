package autolog.DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import autolog.core.ServiceEntry;
import autolog.core.Vehicle;

public class ServiceEntryDAO extends DataBaseDAO {
	private Connection con;
    private Statement st;
    private ResultSet rs;
    private ServiceEntry se;
    
    public ServiceEntryDAO() { 
        // TODO: Should initialize 'con', probably
    }
    
    public ArrayList<ServiceEntry> getAllServiceEntries(int vehicleID) throws SQLException {
    	ArrayList<ServiceEntry> serviceEntryList = new ArrayList<ServiceEntry>();
    	//con = getRemoteConnection();
    	con = getConnection();
    	se = new ServiceEntry(0, 0, null, null, null, 0);
    	
    	try {
            st = con.createStatement();
            try {
            	String query = "use ebdb";
                rs = st.executeQuery(query);
                
                query = "select * from ServiceEntry where vehicleID='" + vehicleID + "'";
                rs = st.executeQuery(query);
                try {
                	   //System.out.println(query);
                	   	rs.beforeFirst();
                            while (rs.next()) {
                            		ServiceEntry tempSE = new ServiceEntry(0, 0, null, null, null, 0);

                                	int id = rs.getInt("ID");
                                    tempSE.setID(id);
    
                                    int vID = rs.getInt("vehicleID");
                                    tempSE.setVehicleID(vID);
                                    
                                    String desc = rs.getString("description");
                                    tempSE.setDescription(desc);
                                    
                                    Date date = rs.getDate("date");
                                    tempSE.setDate(date);
                                    
                                    BigDecimal cost = rs.getBigDecimal("cost");
                                    tempSE.setCost(cost);
                                    
                                    int mileage = rs.getInt("mileage");
                                    tempSE.setMileage(mileage);
                                    
                                    se = tempSE;

                                    serviceEntryList.add(se);
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
    	return serviceEntryList;
    }
    
    public void addServiceEntry(int vehicleID, String desc, Date date, BigDecimal cost, int mileage) throws SQLException{
    	//con = getRemoteConnection();
    	con = getConnection();
    	
    	try {
            st = con.createStatement();
            try {
            	String query = "use ebdb";
                rs = st.executeQuery(query);
                
                query = "insert into ServiceEntry (vehicleID, description, date, cost, mileage)" + " values (?,?,?,?,?)";
                
                PreparedStatement preparedStmt = con.prepareStatement(query);
                preparedStmt.setInt(1, vehicleID);
                preparedStmt.setString(2, desc);
                preparedStmt.setDate(3, date);
                preparedStmt.setBigDecimal(4, cost);
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
    
    public void deleteServiceEntry(int ID) throws SQLException {
    	//con = getRemoteConnection();
    	con = getConnection();

    	try {
            st = con.createStatement();
            try {
            	String query = "use ebdb";
                rs = st.executeQuery(query);
                
                query = "delete from ServiceEntry where ID='" + ID + "'";
                
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
    
    public void editServiceEntry(int seID, String desc, Date date, BigDecimal cost, int mileage) throws SQLException{
    	//con = getRemoteConnection();
    	con = getConnection();

    	try {
            st = con.createStatement();
            try {
            	String sql = "use ebdb";
                rs = st.executeQuery(sql);
                
                sql = "update ServiceEntry set description = ? , "
                + "date = ? , "
                + "cost = ? , "
                + "mileage = ? "
                + "where ID = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(sql);
                preparedStmt.setString(1, desc);
                preparedStmt.setDate(2, date);
                preparedStmt.setBigDecimal(3, cost);
                preparedStmt.setInt(4, mileage);
                preparedStmt.setInt(5, seID);
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
