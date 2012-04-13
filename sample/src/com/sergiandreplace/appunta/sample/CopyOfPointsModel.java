package com.sergiandreplace.appunta.sample;

import java.util.ArrayList;
import java.util.List;

import com.sergiandreplace.appunta.location.LocationBuilder;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.impl.SimplePoint;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;

public class CopyOfPointsModel {
	static List<Point> getPoints(PointRenderer renderer) {
		List<Point> points = new ArrayList<Point>();
		points.add(new SimplePoint(1, LocationBuilder.createLocation(40.418889, -3.691944, 0), renderer, "Madrid")); 
		points.add(new SimplePoint(2, LocationBuilder.createLocation(48.862230,  2.351074, 0), renderer, "Paris"));
		points.add(new SimplePoint(3, LocationBuilder.createLocation(43.604260,  1.443670, 0), renderer, "Toulousse")); 
		points.add(new SimplePoint(4, LocationBuilder.createLocation(41.650000, -0.880000, 0), renderer, "Zaragoza")); 
		points.add(new SimplePoint(5, LocationBuilder.createLocation(39.566667,  2.650000, 0), renderer, "Palma"));
		points.add(new SimplePoint(6, LocationBuilder.createLocation(43.297600,  5.377223, 0), renderer, "Marsella"));
		points.add(new SimplePoint(7, LocationBuilder.createLocation(36.833333, 10.150000, 0), renderer, "Tunez"));
		points.add(new SimplePoint(8, LocationBuilder.createLocation(41.900000, 12.500000, 0), renderer, "Roma"));
		points.add(new SimplePoint(9, LocationBuilder.createLocation(38.980000,  1.430000, 0), renderer, "Ibiza"));
		points.add(new SimplePoint(7, LocationBuilder.createLocation(39.966667,  4.083333, 0), renderer, "Menorca"));
		return points;
	}
}
