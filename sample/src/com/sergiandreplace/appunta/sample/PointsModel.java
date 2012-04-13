package com.sergiandreplace.appunta.sample;

import java.util.ArrayList;
import java.util.List;

import com.sergiandreplace.appunta.location.LocationBuilder;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.impl.SimplePoint;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;

public class PointsModel {
	static List<Point> getPoints(PointRenderer renderer) {
		List<Point> points = new ArrayList<Point>();
		points.add(new SimplePoint(1, LocationBuilder.createLocation(41.389545,2.1523280), renderer, "Clínic"));
		points.add(new SimplePoint(2, LocationBuilder.createLocation(41.379642,2.1397590), renderer, "Sants"));
		points.add(new SimplePoint(3, LocationBuilder.createLocation(41.377991,2.1482880), renderer, "Joan Miró"));
		points.add(new SimplePoint(4, LocationBuilder.createLocation(41.378597,2.1620110), renderer, "Sant Antoni"));
		points.add(new SimplePoint(5, LocationBuilder.createLocation(41.386875,2.1636120), renderer, "UB"));
		points.add(new SimplePoint(6, LocationBuilder.createLocation(41.364731,2.1556380), renderer, "Estadi Olímpic"));
		points.add(new SimplePoint(7, LocationBuilder.createLocation(41.374008,2.1496780), renderer, "Espanay"));
		points.add(new SimplePoint(8, LocationBuilder.createLocation(41.400105,2.1811940), renderer, "Monumental"));
		points.add(new SimplePoint(9, LocationBuilder.createLocation(41.403378,2.1739090), renderer, "Sagrada Familia"));
		points.add(new SimplePoint(10, LocationBuilder.createLocation(41.380932,2.1227650), renderer, "Camp Nou"));
		points.add(new SimplePoint(11, LocationBuilder.createLocation(41.386211,2.1166710), renderer, "UPC"));
		points.add(new SimplePoint(12, LocationBuilder.createLocation(41.383797,2.1052120), renderer, "Cervantes"));
		points.add(new SimplePoint(13, LocationBuilder.createLocation(41.371311,2.1495760), renderer, "Caixaforum"));
		points.add(new SimplePoint(14, LocationBuilder.createLocation(41.39352,2.1840910), renderer, "Estació del Nord"));
		return points;
	}
}
