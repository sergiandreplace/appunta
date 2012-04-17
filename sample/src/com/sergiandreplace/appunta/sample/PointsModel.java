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
		points.add(new SimplePoint(1, LocationBuilder.createLocation(41.402649,2.188544), renderer, "Torre Agbar - SW"));
		points.add(new SimplePoint(2, LocationBuilder.createLocation(41.409016,2.192342), renderer, "N"));
		points.add(new SimplePoint(3, LocationBuilder.createLocation(41.401161,2.192127), renderer, "S"));
		points.add(new SimplePoint(4, LocationBuilder.createLocation(41.405216,2.197728), renderer, "E"));
		points.add(new SimplePoint(5, LocationBuilder.createLocation(41.405106,2.186612), renderer, "W"));
		points.add(new SimplePoint(6, LocationBuilder.createLocation(41.407791,2.189295), renderer, "NW"));
		points.add(new SimplePoint(7, LocationBuilder.createLocation(41.408676,2.190797), renderer, "NNW"));
		points.add(new SimplePoint(8, LocationBuilder.createLocation(41.403011,2.196333), renderer, "SE"));
		points.add(new SimplePoint(9, LocationBuilder.createLocation(41.407936,2.195775), renderer, "NE"));
		points.add(new SimplePoint(10, LocationBuilder.createLocation(41.408516,2.194015), renderer, "NNE"));
		points.add(new SimplePoint(11, LocationBuilder.createLocation(41.40649,2.196934), renderer, "NEE"));
		points.add(new SimplePoint(12, LocationBuilder.createLocation(41.404491,2.19762), renderer, "SEE"));
		points.add(new SimplePoint(13, LocationBuilder.createLocation(41.401951,2.19453), renderer, "SSE"));
		points.add(new SimplePoint(14, LocationBuilder.createLocation(41.401855,2.189853), renderer, "SSW"));
		points.add(new SimplePoint(15, LocationBuilder.createLocation(41.403915,2.187278), renderer, "SWW"));
		points.add(new SimplePoint(16, LocationBuilder.createLocation(41.406551,2.187878), renderer, "NWW"));
		points.add(new SimplePoint(17, LocationBuilder.createLocation(41.40884,2.193114), renderer, " "));
		points.add(new SimplePoint(18, LocationBuilder.createLocation(41.408325,2.194874), renderer, " "));
		points.add(new SimplePoint(19, LocationBuilder.createLocation(41.40723,2.196462), renderer, " "));
		points.add(new SimplePoint(20, LocationBuilder.createLocation(41.406036,2.197277), renderer, " "));
		points.add(new SimplePoint(21, LocationBuilder.createLocation(41.403786,2.197105), renderer, " "));
		points.add(new SimplePoint(22, LocationBuilder.createLocation(41.402401,2.195689), renderer, " "));
		points.add(new SimplePoint(23, LocationBuilder.createLocation(41.401306,2.193457), renderer, " "));
		points.add(new SimplePoint(24, LocationBuilder.createLocation(41.401531,2.191054), renderer, " "));
		points.add(new SimplePoint(25, LocationBuilder.createLocation(41.402431,2.189209), renderer, " "));
		points.add(new SimplePoint(26, LocationBuilder.createLocation(41.403336,2.187836), renderer, " "));
		points.add(new SimplePoint(27, LocationBuilder.createLocation(41.404751,2.187063), renderer, " "));
		points.add(new SimplePoint(28, LocationBuilder.createLocation(41.405876,2.187235), renderer, " "));
		points.add(new SimplePoint(29, LocationBuilder.createLocation(41.407326,2.188436), renderer, " "));
		points.add(new SimplePoint(30, LocationBuilder.createLocation(41.409031,2.191655), renderer, " "));
		points.add(new SimplePoint(31, LocationBuilder.createLocation(41.408325,2.189981), renderer, " "));
return points;
	}
}
