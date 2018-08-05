package classes;

public class Travel {
	private int id;
	private Driver driver;
	private Passenger passenger;
	private int point;
	private String status;
	private double from_place_latitude,
				from_place_longitude,
				to_place_lltitude,
				to_place_longitude;
	private float cost;
	

	public Travel(Passenger passenger) {
		this.passenger = passenger;
	}
	
	public Travel(int id, double from_place_latitude, double from_place_longitude, double to_place_lltitude,
			double to_place_longitude ,float cost) {
		super();
		this.id = id;
		this.from_place_latitude = from_place_latitude;
		this.from_place_longitude = from_place_longitude;
		this.to_place_lltitude = to_place_lltitude;
		this.to_place_longitude = to_place_longitude;
		this.cost=cost;
	}
	
	//GETTERS AND SETTERS :
	public Driver getDriver() { return driver; }
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public void setDriver(Driver driver) { this.driver = driver; }
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	public int getPoint() { return point; }
	public void setPoint(int point) { this.point = point; }
	public double getFrom_place_latitude() { return from_place_latitude; }
	public void setFrom_place_latitude(double from_place_latitude) { this.from_place_latitude = from_place_latitude; }
	public double getFrom_place_longitude() { return from_place_longitude; }
	public void setFrom_place_longitude(double from_place_longitude) { this.from_place_longitude = from_place_longitude; }
	public double getTo_place_lltitude() { return to_place_lltitude; }
	public void setTo_place_lltitude(double to_place_lltitude) { this.to_place_lltitude = to_place_lltitude; }
	public double getTo_place_longitude() { return to_place_longitude; }
	public void setTo_place_longitude(double to_place_longitude) { this.to_place_longitude = to_place_longitude; }
	public float getCost() { return cost; }
	public void setCost(float cost) { this.cost = cost; }
	public Passenger getPassenger() { return passenger; }
}
