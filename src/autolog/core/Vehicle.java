package autolog.core;

/**
 * class for vehicle
 * 
 * @author nealpatel
 */
public class Vehicle {

	/**
	 * integer ID of the vehicle
	 */
	private int ID;
	
	/**
	 * int userID of the vehicle
	 */
	
	private int userID;
	
	/**
	 * string of the make of the vehicle
	 */
	private String make;
	
	/**
	 * string of the model of the vehicle
	 */
	private String model;
	
	/**
	 * integer of the year of the vehicle
	 */
	private int year;
	
	/**
	 * integer of the total mileage of the vehicle
	 */
	private int mileage;
	
	public Vehicle(int ID, int userID, String make, String model, int year, int mileage) {
		super();
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setID(int id) {
		this.ID = id;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getMake() {
		return this.make;
	}
	
	public void setMake(String make) {
		this.make = make;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getMileage() {
		return this.mileage;
	}
	
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
}
