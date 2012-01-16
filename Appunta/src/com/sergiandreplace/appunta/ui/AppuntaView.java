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

package com.sergiandreplace.appunta.ui;

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.PointsManager;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;
import com.sergiandreplace.appunta.point.renderer.SimplePointRenderer;

/***
 * <p>This is the base class in order to create Views using the Appunta system.
 * The class has all needed calculations and values to retrieve info from points.</p>
 * 
 * <p>It's important to understand how this will work. All the stuff happens in the onDraw Method.
 * 
 * <p>The {@link #onDraw} method has three phases: <b>preRender</b>, <b>pointRendering</b> & <b>postRender</b>. </p>
 * <ul>
 * <li>The <b>preRender</b> phase triggers the method {@link #preRender}, used to draw all needed elements used in
 * the background.</li>
 * 
 * <li>In the <b>pointRendering</b> phase, the method {@link #calculatePointCoordinates(Point)} is invoked per each on of the points, 
 * in order to calculate the screen coordinates for each one of them. Then, they are painted by calling
 * their PaintRenderer.
 * </li>
 * 
 * <li>In the <b>Post render</b> phase, the {@link #postRender(Canvas)} method is invoked in order to paint
 * the latest layer</li>
 * </ul>
 * 
 * @author Sergi Martínez
 *
 */
public abstract class AppuntaView extends View {

	public interface OnPointPressedListener {
		public void onPointPressed(Point p);
	}

	private static final double DEFAULT_MAX_DISTANCE = 1000;

	private static final double MAX_DISTANCE_TAP = 48;

	private int rotableBackground = 0;
	private SimplePointRenderer simpleRenderer;
	private PointsManager pointsManager;
	private float azimuth;
	private double azimuthRadians;
	private double longitude;
	private double latitude;

	
	private double maxDistance = DEFAULT_MAX_DISTANCE;

	private HashMap<String, PointRenderer> renderers = new HashMap<String, PointRenderer>();

	private List<Point> shownPoints;

	private OnPointPressedListener onPointPressedListener;

	public AppuntaView(Context context) {
		super(context);
	}

	public AppuntaView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AppuntaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	protected void extractSubPoints() {
		setShownPoints(pointsManager.getNearPoints(latitude, longitude,
				getMaxDistance()));

	}

	protected Point findNearestPoint(float x, float y) {
		Point p = null;
		Double minorDistance = (double) Math.max(this.getWidth(),
				this.getHeight());
		for (Point point : getShownPoints()) {
			double distance = Math.sqrt(Math.pow((point.getX() - x), 2)
					+ Math.pow((point.getY() - y), 2));
			if (distance < minorDistance) {
				minorDistance = distance;
				p = point;
			}
		}
		if (minorDistance <= MAX_DISTANCE_TAP) {
			return p;
		} else {
			return null;
		}
	}

	private PointRenderer obtainRenderer(Point point) {
		PointRenderer renderer;
		if (point.getRendererName() != null
				& getRenderers().containsKey(point.getRendererName())) {
			renderer = getRenderers().get(point.getRendererName());
		} else {
			if (simpleRenderer == null) {
				simpleRenderer = new SimplePointRenderer();
			}
			renderer = new SimplePointRenderer();
		}
		return renderer;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		preRender(canvas);
		if (getShownPoints() != null) {
			for (Point point : getShownPoints()) {
				calculatePointCoordinates(point);
				PointRenderer renderer = obtainRenderer(point);
				renderer.drawPoint(point, canvas, getAzimuth());
			}
		}
		postRender(canvas);
	}

	/***
	 * Returns the correct size of the control when needed (Basically
	 * maintaining the ratio)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Point p = findNearestPoint(event.getX(), event.getY());
			if (p != null && getOnPointPressedListener() != null) {
				onPointPressedListener.onPointPressed(p);
			}
		}

		return super.onTouchEvent(event);
	}

	protected abstract void preRender(Canvas canvas);

	protected abstract void calculatePointCoordinates(Point point);

	protected abstract void postRender(Canvas canvas);

	public void putRenderer(String name, PointRenderer renderer) {
		getRenderers().put(name, renderer);
	}

	protected double getAngle(Point point) {
		return Math.atan2(point.getLatitude() - latitude, point.getLongitude()
				- longitude);
	}

	protected float getAzimuth() {
		return azimuth;
	}

	protected double getAzimuthRadians() {
		return azimuthRadians;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getMaxDistance() {
		return maxDistance;
	}

	public OnPointPressedListener getOnPointPressedListener() {
		return onPointPressedListener;
	}

	public float getOrientation() {
		return getAzimuth();
	}

	public HashMap<String, PointRenderer> getRenderers() {
		return renderers;
	}

	public int getRotableBackground() {
		return rotableBackground;
	}

	protected List<Point> getShownPoints() {
		return shownPoints;
	}

	protected void setAzimuth(float azimuth) {
		this.azimuth = azimuth;
		this.azimuthRadians = Math.toRadians(azimuth);
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
		extractSubPoints();
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
		extractSubPoints();
	}

	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
		this.invalidate();

	}

	public void setOnPointPressedListener(
			OnPointPressedListener onPointPressedListener) {
		this.onPointPressedListener = onPointPressedListener;
	}

	public void setOrientation(float azimuth) {
		this.setAzimuth(azimuth);
		this.invalidate();
	}

	public void setPoints(List<Point> points) {
		if (pointsManager == null) {
			pointsManager = new PointsManager(points);
		} else {
			this.pointsManager.setPoints(points);
		}
	}

	public void setPosition(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		extractSubPoints();
	}

	public void setRenderers(HashMap<String, PointRenderer> renderers) {
		this.renderers = renderers;
	}

	public void setRotableBackground(int rotableBackground) {
		this.rotableBackground = rotableBackground;
	}

	protected void setShownPoints(List<Point> shownPoints) {
		this.shownPoints = shownPoints;
	}



}
