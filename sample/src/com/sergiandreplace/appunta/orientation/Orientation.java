package com.sergiandreplace.appunta.orientation;

public class Orientation {

	private float azimuth = 0;
	private float compass = 0;
	private double compassRadians = 0;
	private double azimuthRadians = 0;
	private float roll = 0;
	private float pitch = 0;

	public float getAzimuth() {
		return azimuth;
	}

	protected double getAzimuthRadians() {
		return azimuthRadians;
	}

	public float getCompass() {
		return compass;
	}

	public double getCompassRadians() {
		return compassRadians;
	}

	public void setAzimuth(float azimuth) {
		this.azimuth = azimuth;
		this.azimuthRadians = Math.toRadians(azimuth);
		this.compass = (azimuth + 90) % 360;
		this.compassRadians = Math.toRadians(compass);
	}

	public void setCompass(float compass) {
		this.compass = compass;
		this.compassRadians = Math.toRadians(compass);
		this.azimuth = (compass + 270) % 360;
		this.azimuthRadians = Math.toRadians(azimuth);

	}

	public float getRoll() {
		return roll;
	}

	public void setRoll(float roll) {
		this.roll = roll;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

}
