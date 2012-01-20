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

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.Points;
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


	private int rotableBackground = 0;
	private float azimuth;
	private double azimuthRadians;
	private double longitude;
	private double latitude;

	
	private double maxDistance = DEFAULT_MAX_DISTANCE;


	private Points points;

	private OnPointPressedListener onPointPressedListener;

	private Double minorDistance;

	private double distance;

	private Point p;


	private SimplePointRenderer simplePointRenderer;

	public AppuntaView(Context context) {
		super(context);
	}

	public AppuntaView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AppuntaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	protected Point findNearestPoint(float x, float y) {
		p = null;
		minorDistance = (double) Math.max(this.getWidth(),this.getHeight());
		for (Point point : getpoints()) {
			distance = Math.sqrt(Math.pow((point.getX() - x), 2)
					+ Math.pow((point.getY() - y), 2));
			if (distance < minorDistance) {
				minorDistance = distance;
				p = point;
			}
		}
		return p;
	}


	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		preRender(canvas);
		if (getpoints() != null) {
			for (Point point : getpoints()) {
				calculatePointCoordinates(point);
				if (point.getRenderer()!=null){
					point.getRenderer().drawPoint(point, canvas, getAzimuth());
					
				}else{
					if (simplePointRenderer==null) {
						simplePointRenderer=new SimplePointRenderer();
					}
					simplePointRenderer.drawPoint(point,canvas,getAzimuth());
				}
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

	

	public int getRotableBackground() {
		return rotableBackground;
	}

	protected Points getpoints() {
		return points;
	}

	protected void setAzimuth(float azimuth) {
		this.azimuth = azimuth;
		this.azimuthRadians = Math.toRadians(azimuth);
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
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

	public void setPoints(Points points) {
			this.points=points;
	}

	public void setPosition(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		points.calculateDistance(latitude, longitude);
	}

	public void setRotableBackground(int rotableBackground) {
		this.rotableBackground = rotableBackground;
	}



}
