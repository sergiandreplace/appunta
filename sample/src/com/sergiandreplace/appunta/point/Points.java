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

package com.sergiandreplace.appunta.point;

import java.util.ArrayList;
import java.util.List;

import com.sergiandreplace.appunta.location.Location;

/***
 * A simple class intented to store points and perform basic operations
 * @author Sergi Martínez
 *
 */
@SuppressWarnings("serial")
public  class Points extends ArrayList<Point>{

	/***
	 * Calculate the distance from a given point to all the points stored and sets
	 * the distance property for all them
	 * @param location Latitude and longitude of the given point
	 */
	public void calculateDistance(Location location) {
		for (Point poi:this) {
			poi.setDistance(poi.distanceKm(location));
		}
	}
	
	/***
	 * Returns a subset of points that are below a distance of a given point
	 * @param location Latitude and longitude of the given point
	 * @param distance Distance to filter
	 * @return The subset list
	 */
	public List<Point> getNearPoints(Location location, double distance) {
		calculateDistance(location);
		List<Point> subPoints=new ArrayList<Point>();
		for (Point poi:this) {
			if (poi.getDistance()<=distance) {
				subPoints.add(poi);
			}
		}
		return subPoints;
	}
}
