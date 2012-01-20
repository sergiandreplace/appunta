package com.sergiandreplace.appunta.sample;

import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.Points;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;


public class PointsModel {
	static Points getPoints(PointRenderer renderer) {
		Points points=new Points();
		points.add(new Point(1, 40.418889, -3.691944, renderer, "Madrid")); // Madrid
		points.add(new Point(2, 48.86223, 2.351074, renderer, "Paris"));// Paris
		points.add(new Point(3, 43.60426, 1.44367, renderer, "Toulousse")); // TOulousse
		points.add(new Point(4, 41.65, -0.88, renderer, "Zaragoza")); // Zaragoza
		points.add(new Point(5, 39.566667, 2.65, renderer, "Palma"));
		points.add(new Point(6, 43.2976, 5.377223, renderer, "Marsella"));
		points.add(new Point(7, 36.833333, 10.15, renderer, "Tunez"));
		points.add(new Point(8, 41.9, 12.5, renderer, "Roma"));
		points.add(new Point(9, 38.98, 1.43, renderer, "Ibiza"));
		points.add(new Point(7, 39.966667, 4.083333, renderer, "Menorca"));
		return points;
	}
}
