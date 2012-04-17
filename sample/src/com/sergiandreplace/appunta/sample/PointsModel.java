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
		points.add(new SimplePoint(1, LocationBuilder.createLocation(41.402649,2.188544), renderer, "SW"));
		points.add(new SimplePoint(2, LocationBuilder.createLocation(41.414165,2.192471), renderer, "N"));
		points.add(new SimplePoint(3, LocationBuilder.createLocation(41.396656,2.191998), renderer, "S"));
		points.add(new SimplePoint(4, LocationBuilder.createLocation(41.4048,2.209143), renderer, "E"));
		points.add(new SimplePoint(5, LocationBuilder.createLocation(41.405361,2.178201), renderer, "W"));
		points.add(new SimplePoint(6, LocationBuilder.createLocation(41.41449,2.179167), renderer, "NW"));
		points.add(new SimplePoint(7, LocationBuilder.createLocation(41.41209,2.188952), renderer, "NNW"));
		points.add(new SimplePoint(8, LocationBuilder.createLocation(41.403011,2.196333), renderer, "SE"));
		points.add(new SimplePoint(9, LocationBuilder.createLocation(41.408001,2.198221), renderer, "NE"));
		points.add(new SimplePoint(10, LocationBuilder.createLocation(41.411091,2.196933), renderer, "NNE"));
		points.add(new SimplePoint(11, LocationBuilder.createLocation(41.406265,2.203071), renderer, "NEE"));
		points.add(new SimplePoint(12, LocationBuilder.createLocation(41.403206,2.208134), renderer, "SEE"));
		points.add(new SimplePoint(13, LocationBuilder.createLocation(41.401951,2.19453), renderer, "SSE"));
		points.add(new SimplePoint(14, LocationBuilder.createLocation(41.395157,2.187965), renderer, "SSW"));
		points.add(new SimplePoint(15, LocationBuilder.createLocation(41.400696,2.176463), renderer, "SWW"));
		points.add(new SimplePoint(16, LocationBuilder.createLocation(41.407776,2.185389), renderer, "NWW"));
		points.add(new SimplePoint(17, LocationBuilder.createLocation(41.412701,2.195217), renderer, "   "));
		points.add(new SimplePoint(18, LocationBuilder.createLocation(41.409485,2.19835), renderer, "   "));
		points.add(new SimplePoint(19, LocationBuilder.createLocation(41.407066,2.202642), renderer, "   "));
		points.add(new SimplePoint(20, LocationBuilder.createLocation(41.405521,2.205946), renderer, "   "));
		points.add(new SimplePoint(21, LocationBuilder.createLocation(41.402657,2.199766), renderer, "   "));
		points.add(new SimplePoint(22, LocationBuilder.createLocation(41.400276,2.199165), renderer, "   "));
		points.add(new SimplePoint(23, LocationBuilder.createLocation(41.403076,2.192856), renderer, "   "));
		points.add(new SimplePoint(24, LocationBuilder.createLocation(41.399536,2.190625), renderer, "   "));
		points.add(new SimplePoint(25, LocationBuilder.createLocation(41.402946,2.189896), renderer, "   "));
		points.add(new SimplePoint(26, LocationBuilder.createLocation(41.401047,2.184703), renderer, "   "));
		points.add(new SimplePoint(27, LocationBuilder.createLocation(41.403336,2.17818), renderer, "   "));
		points.add(new SimplePoint(28, LocationBuilder.createLocation(41.40797,2.180926), renderer, "   "));
		points.add(new SimplePoint(29, LocationBuilder.createLocation(41.41254,2.179295), renderer, "   "));
		points.add(new SimplePoint(30, LocationBuilder.createLocation(41.41328,2.190282), renderer, "   "));
		points.add(new SimplePoint(31, LocationBuilder.createLocation(41.411446,2.187706), renderer, "   "));

return points;
	}
}
