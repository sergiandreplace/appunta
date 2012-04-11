package com.sergiandreplace.appunta.point;

import android.location.Location;

import com.sergiandreplace.appunta.point.renderer.PointRenderer;

public interface Point {

	/***
	 * Distance to a point
	 * @return the distance in Km if previously set
	 */
	public abstract double getDistance();

	/***
	 * Allows to store the distance to a point
	 * @param distance Distance to a point in Km
	 */
	public abstract void setDistance(double distance);

	/***
	 * Name of the point. Created in order to make your life easier
	 * @return the name of the point
	 */
	public abstract String getName();

	/***
	 * Allows to store a name for the point
	 * @param name the intended name
	 */
	public abstract void setName(String name);

	/***
	 * Gets the name of the renderer to use to draw this point
	 * @return The renderer
	 */
	public abstract PointRenderer getRenderer();

	/***
	 * To assign a renderer to the current point
	 * @param renderer
	 */
	public abstract void setRenderer(PointRenderer renderer);

	/***
	 * A unique id
	 * @return
	 */
	public abstract int getId();

	/***
	 * A unique id
	 * @param id
	 */
	public abstract void setId(int id);

	/***
	 * Last X coordinate where the point should be drawn
	 * @return X coordinate of the canvas
	 */
	public abstract float getX();

	public abstract void setX(float x);

	/***
	 * Last Y coordinate where the point should be drawn
	 * @return Y coordinate of the canvas
	 */
	public abstract float getY();

	public abstract void setY(float y);

	public abstract Location getLocation();

	public abstract void setLocation(Location location);

	
}