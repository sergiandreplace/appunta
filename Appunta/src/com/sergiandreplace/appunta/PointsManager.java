package com.sergiandreplace.appunta;

import java.util.ArrayList;
import java.util.List;

import com.sergiandreplace.appunta.util.GeoUtils;


public  class PointsManager {

	private List<Point> pointss;
	
	public PointsManager(List<Point> points) {
		this.setPoints(points);
	}

	public List<Point> getPoints() {
		return pointss;
	}

	public void setPoints(List<Point> points) {
		this.pointss = points;
	}

	public void calculateDistance(double latitude, double longitude) {
		for (Point poi:pointss) {
			poi.setDistance(GeoUtils.distanceKm(latitude, longitude, poi.getLatitude(), poi.getLongitude()));
		}
	}
	
	public List<Point> getNearPoints(double latitude, double longitude, double distance) {
		calculateDistance(latitude,longitude);
		List<Point> subPoints=new ArrayList<Point>();
		for (Point poi:pointss) {
			if (poi.getDistance()<=distance) {
				subPoints.add(poi);
			}
		}
		return subPoints;
	}
}
