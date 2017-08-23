package autolog.core;

import java.util.ArrayList;

public class User {
	
	private int ID;
	private String name;
	private String username;
	private String password;
	private ArrayList<Vehicle> vehicleList;
	
	public User(String username, String password) {
		super();
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setID(int id) {
		this.ID = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String setName(String name) {
		return this.name = name;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String uname) {
		this.username = uname;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public ArrayList<Vehicle> getVechicleList() {
		return this.vehicleList;
	}
	
}
