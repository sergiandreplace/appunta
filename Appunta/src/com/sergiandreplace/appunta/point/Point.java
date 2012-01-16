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
/***
 * A single point representing a place, it contains information on where it's
 * located in space, in screen, it's id and name and the name of the renderer to
 * use to draw it.
 * 
 * @author Sergi Martínez
 *
 */
public class Point {
	private int id;
	private float latitude;
	private float longitude;
	private double distance;
	private String name;
	private String rendererName;
	private float x;
	private float y;
	
	public Point(int id, float latitude, float longitude, String rendererName, String name) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
		this.rendererName=rendererName;
		this.name=name;
	}
	public Point(int id, double latitude, double longitude, String rendererName, String name) {
		this(id, (float) latitude, (float) longitude, rendererName, name);
			
	}
	public Point(int id, float latitude, float longitude, String rendererName) {
		this(id,latitude,longitude,rendererName,"");
	}
	public Point(int id, double latitude, double longitude,  String rendererName) {
		this(id, (float) latitude,(float) longitude,rendererName);
	}

	public Point(int id, float latitude, float longitude) {
		this(id,latitude,longitude,null);
	}
	
	public Point(int id, double latitude, double longitude) {
		this(id, (float) latitude,(float) longitude,null);
	}
	
	/***
	 * Latitude of the point
	 * @return The current position of the point in decimal degrees
	 */
	public float getLatitude() {
		return latitude;
	}
	
	/***
	 * Latitude setter
	 * @param latitude The current position of the point in decimal degrees
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	/***
	 * Longitude of the point
	 * @return The current position of the point in decimal degrees
	 */
	public float getLongitude() {
		return longitude;
	}
	/***
	 * Longitude setter
	 * @param latitude The current position of the point in decimal degrees
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
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
	public String getRendererName() {
		return rendererName;
	}
	/***
	 * To assign a renderer to the current point
	 * @param rendererName
	 */
	public void setRendererName(String rendererName) {
		this.rendererName = rendererName;
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
	
	
}
