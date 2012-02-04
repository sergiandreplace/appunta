package com.sergiandreplace.appunta.location;

public class Location {

	private float latitude;
	private float longitude;
	private float altitude;
	public Location() {

	}

	public Location(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Location(float latitude, float longitude, float altitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude=altitude;
	}

	public Location(double latitude, double longitude) {
		setLatitude(latitude);
		setLongitude(longitude);
	}

	public Location(double latitude, double longitude, double altitude) {
		setLatitude(latitude);
		setLongitude(longitude);
		setAltitude(altitude);
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

	public float getAltitude() {
		return altitude;
	}

	public void setAltitude(float altitude) {
		this.altitude = altitude;
	}
	public void setAltitude(double altitude) {
		this.altitude=(float) altitude;
	}
	
	
}
