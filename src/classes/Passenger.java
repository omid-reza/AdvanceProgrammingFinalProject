package classes;

public class Passenger extends User{
	public int mony;
	public Passenger(String userName, String password) {
		super(userName, password);
	}
	public int getMony() {
		return mony;
	}
	public void setMony(int mony) {
		this.mony = mony;
	}
	
	
	
}
