package com.sergiandreplace.appunta.sample;

import java.util.ArrayList;

import com.sergiandreplace.appunta.point.Point;


public class PointsModel {
	static ArrayList<Point> getPoints(String pointDrawer) {
		ArrayList<Point> points=new ArrayList<Point>();
		points.add(new Point(1, 40.418889, -3.691944, pointDrawer, "Madrid")); // Madrid
		points.add(new Point(2, 48.86223, 2.351074, pointDrawer, "Paris"));// Paris
		points.add(new Point(3, 43.60426, 1.44367, pointDrawer, "Toulousse")); // TOulousse
		points.add(new Point(4, 41.65, -0.88, pointDrawer, "Zaragoza")); // Zaragoza
		points.add(new Point(5, 39.566667, 2.65, pointDrawer, "Palma"));
		points.add(new Point(6, 43.2976, 5.377223, pointDrawer, "Marsella"));
		points.add(new Point(7, 36.833333, 10.15, pointDrawer, "Tunez"));
		points.add(new Point(8, 41.9, 12.5, pointDrawer, "Roma"));
		points.add(new Point(9, 38.98, 1.43, pointDrawer, "Ibiza"));
		points.add(new Point(7, 39.966667, 4.083333, pointDrawer, "Menorca"));
		return points;
	}
}
