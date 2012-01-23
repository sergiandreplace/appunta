package com.sergiandreplace.appunta.location;

public class Location {

	private float latitude;
	private float longitude;

	public Location() {

	}

	public Location(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location(double latitude, double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}

	/***
	 * Latitude of the point
	 * 
	 * @return The current position of the point in decimal degrees
	 */
	public float getLatitude() {
		return latitude;
	}

	/***
	 * Latitude setter
	 * 
	 * @param latitude
	 *            The current position of the point in decimal degrees
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	/***
	 * Latitude setter as double
	 * 
	 * @param latitude
	 *            The current position of the point in decimal degrees
	 */
	public void setLatitude(double latitude) {
		this.latitude = (float) latitude;
	}

	/***
	 * Longitude of the point
	 * 
	 * @return The current position of the point in decimal degrees
	 */
	public float getLongitude() {
		return longitude;
	}

	/***
	 * Longitude setter
	 * 
	 * @param latitude
	 *            The current position of the point in decimal degrees
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	/***
	 * Longitude setter
	 * 
	 * @param latitude
	 *            The current position of the point in decimal degrees
	 */
	public void setLongitude(double longitude) {
		this.longitude = (float) longitude;
	}

	
	
}
