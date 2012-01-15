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

package com.sergiandreplace.appunta;

import java.util.ArrayList;
import java.util.List;

import com.sergiandreplace.appunta.util.GeoUtils;

/***
 * A simple class intented to store points and perform basic operations
 * @author Sergi Martínez
 *
 */
public  class PointsManager {

	private List<Point> pointss;
	
	/***
	 * Constructor assigning a list of points
	 * @param points
	 */
	public PointsManager(List<Point> points) {
		this.setPoints(points);
	}

	/***
	 * Returns the list of points stored in the Manager
	 * @return The list of points
	 */
	public List<Point> getPoints() {
		return pointss;
	}

	/***
	 * Stores another set of points
	 * @param points The list of points
	 */
	public void setPoints(List<Point> points) {
		this.pointss = points;
	}

	/***
	 * Calculate the distance from a given point to all the points stored and sets
	 * the distance property for all them
	 * @param latitude Latitude of the given point
	 * @param longitude Longitude of the given point
	 */
	public void calculateDistance(double latitude, double longitude) {
		for (Point poi:pointss) {
			poi.setDistance(GeoUtils.distanceKm(latitude, longitude, poi.getLatitude(), poi.getLongitude()));
		}
	}
	
	/***
	 * Returns a subset of points that are below a distance of a given point
	 * @param latitude Latitude of the given point
	 * @param longitude Longitude of the given point
	 * @param distance Distance to filter
	 * @return The subset list
	 */
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
