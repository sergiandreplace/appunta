package com.sergiandreplace.appunta.sample;

import com.sergiandreplace.appunta.location.Location;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.Points;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;

public class PointsModel {
	static Points getPoints(PointRenderer renderer) {
		Points points = new Points();
		points.add(new Point(1, new Location(40.418889, -3.691944, 0), renderer, "Madrid")); 
		points.add(new Point(2, new Location(48.862230,  2.351074, 0), renderer, "Paris"));
		points.add(new Point(3, new Location(43.604260,  1.443670, 0), renderer, "Toulousse")); 
		points.add(new Point(4, new Location(41.650000, -0.880000, 0), renderer, "Zaragoza")); 
		points.add(new Point(5, new Location(39.566667,  2.650000, 0), renderer, "Palma"));
		points.add(new Point(6, new Location(43.297600,  5.377223, 0), renderer, "Marsella"));
		points.add(new Point(7, new Location(36.833333, 10.150000, 0), renderer, "Tunez"));
		points.add(new Point(8, new Location(41.900000, 12.500000, 0), renderer, "Roma"));
		points.add(new Point(9, new Location(38.980000,  1.430000, 0), renderer, "Ibiza"));
		points.add(new Point(7, new Location(39.966667,  4.083333, 0), renderer, "Menorca"));
		return points;
	}
}
