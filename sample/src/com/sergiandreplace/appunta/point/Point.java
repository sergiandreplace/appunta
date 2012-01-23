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

import com.sergiandreplace.appunta.location.Location;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;

/***
 * A single point representing a place, it contains information on where it's
 * located in space, in screen, it's id and name and the name of the renderer to
 * use to draw it.
 * 
 * @author Sergi Martínez
 *
 */
public class Point {
	private static int EARTH_RADIUS_KM = 6371;

	public static int MILLION = 1000000;

	
	private int id;
	private Location location;
	private double distance;
	private String name;
	private PointRenderer renderer;
	private float x;
	private float y;
	
	public Point(int id, Location location, PointRenderer renderer, String name) {
		super();
		this.setLocation(location);
		this.renderer=renderer;
		this.name=name;
	}
	public Point(int id, Location location, PointRenderer renderer) {
		this(id,location,renderer,"");
	}

	public Point(int id, Location location) {
		this(id,location,null);
	}
	
	
	/***
	 * Distance to a point
	 * @return the distance in Km if previously set
	 */
	public double getDistance() {
		return distance;
	}
	/***
	 * Allows to store the distance to a point
	 * @param distance Distance to a point in Km
	 */
	public void setDistance(double distance) {
		this.distance = distance;
	}
	/***
	 * Name of the point. Created in order to make your life easier
	 * @return the name of the point
	 */
	public String getName() {
		return name;
	}
	/***
	 * Allows to store a name for the point
	 * @param name the intended name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/***
	 * Gets the name of the renderer to use to draw this point
	 * @return The renderer
	 */
	public PointRenderer getRenderer() {
		return renderer;
	}
	/***
	 * To assign a renderer to the current point
	 * @param renderer
	 */
	public void setRenderer(PointRenderer renderer) {
		this.renderer = renderer;
	}

	/***
	 * A unique id
	 * @return
	 */
	public int getId() {
		return id;
	}
	/***
	 * A unique id
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/***
	 * Last X coordinate where the point should be drawn
	 * @return X coordinate of the canvas
	 */
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	/***
	 * Last Y coordinate where the point should be drawn
	 * @return Y coordinate of the canvas
	 */
	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	/**
	 * Computes the distance in kilometers between two points on Earth.
	 * 
	 * @param location1
	 *            Latitude and longitude of the first point
	 * @param location2
	 *            Latitude and longitude of the second point
	 * @return Distance between the two points in kilometers.
	 */
	public  double distanceKm(Location otherLocation) {
		double lat1Rad = Math.toRadians(otherLocation.getLatitude());
		double lat2Rad = Math.toRadians(location.getLatitude());
		double deltaLonRad = Math.toRadians(location.getLongitude()
				- otherLocation.getLongitude());

		return Math
				.acos(Math.sin(lat1Rad) * Math.sin(lat2Rad) + Math.cos(lat1Rad)
						* Math.cos(lat2Rad) * Math.cos(deltaLonRad))
				* EARTH_RADIUS_KM;
	}	
	
}
