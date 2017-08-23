package autolog.core;

import java.math.BigDecimal;
import java.sql.Date;

public class ServiceEntry {
	/**
	 * integer ID of the service entry
	 */
	private int ID;
	
	/**
	 * int vehicleID of the service entry
	 */
	
	private int vehicleID;
	
	/**
	 * string of the description of the service entry
	 */
	private String description;
	
	/**
	 * string of the date of the service entry
	 */
	private Date date;
	
	/**
	 * float of the cost of the service entry
	 */
	private BigDecimal cost;
	
	/**
	 * integer of the mileage of service entry
	 */
	private int mileage;
	


	public ServiceEntry(int ID, int vehicleID, String description, Date date, BigDecimal cost, int mileage) {
		super();
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setID(int id) {
		this.ID = id;
	}
	
	public int getVehicleID() {
		return this.vehicleID;
	}
	
	public void setVehicleID(int id) {
		this.vehicleID = id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public BigDecimal getCost() {
		return this.cost;
	}
	
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	public int getMileage() {
		return this.mileage;
	}
	
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}

}