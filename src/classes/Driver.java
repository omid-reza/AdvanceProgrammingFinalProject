package classes;

public class Driver extends User {
	private float salary;
	
	public Driver(String userName) {
		super(userName);
	}
	
	public Driver(int id) {
		super(id);
	}
	
	public Driver(String userName, String password, float salary) {
		super(userName, password);
		this.salary = salary;
	}

	// GETTERS & SETTERS :
	public float getSalary() { return salary; }
	public void setSalary(float salary) { this.salary = salary; }
	
}
