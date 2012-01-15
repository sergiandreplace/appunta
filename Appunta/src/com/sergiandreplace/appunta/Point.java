package com.sergiandreplace.appunta;

public class Point {
	private int id;
	private float latitude;
	private float longitude;
	private double distance;
	private String name;
	private String rendererName;
	
	
	public Point(int id, float latitude, float longitude, String rendererName) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.rendererName=rendererName;
	}
	
	public Point(int id, double latitude, double longitude,  String rendererName) {
		this(id, (float) latitude,(float) longitude,rendererName);
	}

	public Point(int id, float latitude, float longitude) {
		this(id,latitude,longitude,null);
	}
	
	public Point(int id, double latitude, double longitude) {
		this(id, (float) latitude,(float) longitude,null);
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRendererName() {
		return rendererName;
	}

	public void setRendererName(String rendererName) {
		this.rendererName = rendererName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
