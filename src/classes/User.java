package classes;

public class User {
	private int id;
	private String userName, password;
	
	public User(String userName) {
		super();
		this.userName = userName;
	}
	public User(int id) {
		this.id=id;
	}
	
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	// GETTERS & SETTERS :
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getUserName() { return userName; }
	public String getPassword() { return password; }

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}