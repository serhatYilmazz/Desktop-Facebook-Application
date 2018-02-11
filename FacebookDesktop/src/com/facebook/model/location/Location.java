package com.facebook.model.location;




/**
 * {@link Location} with relevant to {@link Post} class. It includes {@link #latitude} and {@link #longitude} of the post shared place. 
 * 
 */
public class Location {
	private double latitude;
	private double longitude;
	
	/**
	 * Constructor of {@link Location} class. Assigning initial values.
	 * @param latitude of the Location.
	 * @param longitude of the Location.
	 */
	public Location(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String toString(){
		return String.format("Latitude: %f, Longtitude: %f", latitude, longitude);
	}
}
