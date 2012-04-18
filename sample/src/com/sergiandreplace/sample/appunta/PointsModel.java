/*
   Copyright Sergi Martínez (@sergiandreplace)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.sergiandreplace.sample.appunta;

import java.util.ArrayList;
import java.util.List;

import com.sergiandreplace.appunta.location.LocationFactory;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.impl.SimplePoint;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;

public class PointsModel {
	static List<Point> getPoints(PointRenderer renderer) {
		List<Point> points = new ArrayList<Point>();
		points.add(new SimplePoint(1, LocationFactory.createLocation(41.402649,2.188544), renderer, "SW"));
		points.add(new SimplePoint(2, LocationFactory.createLocation(41.414165,2.192471,10000), renderer, "N"));
		points.add(new SimplePoint(3, LocationFactory.createLocation(41.396656,2.191998), renderer, "S"));
		points.add(new SimplePoint(4, LocationFactory.createLocation(41.4048,2.209143), renderer, "E"));
		points.add(new SimplePoint(5, LocationFactory.createLocation(41.405361,2.178201), renderer, "W"));
		points.add(new SimplePoint(6, LocationFactory.createLocation(41.41449,2.179167), renderer, "NW"));
		points.add(new SimplePoint(7, LocationFactory.createLocation(41.41209,2.188952), renderer, "NNW"));
		points.add(new SimplePoint(8, LocationFactory.createLocation(41.403011,2.196333), renderer, "SE"));
		points.add(new SimplePoint(9, LocationFactory.createLocation(41.408001,2.198221), renderer, "NE"));
		points.add(new SimplePoint(10, LocationFactory.createLocation(41.411091,2.196933), renderer, "NNE"));
		points.add(new SimplePoint(11, LocationFactory.createLocation(41.406265,2.203071), renderer, "NEE"));
		points.add(new SimplePoint(12, LocationFactory.createLocation(41.403206,2.208134), renderer, "SEE"));
		points.add(new SimplePoint(13, LocationFactory.createLocation(41.401951,2.19453), renderer, "SSE"));
		points.add(new SimplePoint(14, LocationFactory.createLocation(41.395157,2.187965), renderer, "SSW"));
		points.add(new SimplePoint(15, LocationFactory.createLocation(41.400696,2.176463), renderer, "SWW"));
		points.add(new SimplePoint(16, LocationFactory.createLocation(41.407776,2.185389), renderer, "NWW"));
		points.add(new SimplePoint(17, LocationFactory.createLocation(41.412701,2.195217), renderer, "   "));
		points.add(new SimplePoint(18, LocationFactory.createLocation(41.409485,2.19835), renderer, "   "));
		points.add(new SimplePoint(19, LocationFactory.createLocation(41.407066,2.202642), renderer, "   "));
		points.add(new SimplePoint(20, LocationFactory.createLocation(41.405521,2.205946), renderer, "   "));
		points.add(new SimplePoint(21, LocationFactory.createLocation(41.402657,2.199766), renderer, "   "));
		points.add(new SimplePoint(22, LocationFactory.createLocation(41.400276,2.199165), renderer, "   "));
		points.add(new SimplePoint(23, LocationFactory.createLocation(41.403076,2.192856), renderer, "   "));
		points.add(new SimplePoint(24, LocationFactory.createLocation(41.399536,2.190625), renderer, "   "));
		points.add(new SimplePoint(25, LocationFactory.createLocation(41.402946,2.189896), renderer, "   "));
		points.add(new SimplePoint(26, LocationFactory.createLocation(41.401047,2.184703), renderer, "   "));
		points.add(new SimplePoint(27, LocationFactory.createLocation(41.403336,2.17818), renderer, "   "));
		points.add(new SimplePoint(28, LocationFactory.createLocation(41.40797,2.180926), renderer, "   "));
		points.add(new SimplePoint(29, LocationFactory.createLocation(41.41254,2.179295), renderer, "   "));
		points.add(new SimplePoint(30, LocationFactory.createLocation(41.41328,2.190282), renderer, "   "));
		points.add(new SimplePoint(31, LocationFactory.createLocation(41.411446,2.187706), renderer, "   "));

return points;
	}
}
