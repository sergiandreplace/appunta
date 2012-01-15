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
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.sergiandreplace.appunta.Point;
import com.sergiandreplace.appunta.PointsManager;
import com.sergiandreplace.appunta.pointdrawer.PointRenderer;
import com.sergiandreplace.appunta.pointdrawer.SimplePointRenderer;

public class RadarView extends View {

	private static final double DEFAULT_MAX_DISTANCE = 1000;

	private static final double MAX_DISTANCE_TAP = 48;

	private PointsManager pointsManager;
	private float azimuth;
	private double longitude;
	private double latitude;
	private Context context;
	private double maxDistance = DEFAULT_MAX_DISTANCE;
	private int rotableBackground = 0;
	private HashMap<String, PointRenderer> renderers;

	private List<Point> shownPoints;

	private int size;

	private int center;

	private OnPointPressedListener onPointPressedListener;
	
	
	public RadarView(Context context) {
		super(context);
		this.context = context;
		init();
	}

	public RadarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		init();
	}

	RadarView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		init();
	}

	public void init() {
		renderers = new HashMap<String, PointRenderer>();
	}

	public void setOrientation(float azimuth) {
		this.azimuth = azimuth;
		this.invalidate();
	}

	public float getOrientation() {
		return azimuth;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
		extractSubPoints();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(long latitude) {
		this.latitude = latitude;
		extractSubPoints();
	}

	public void setPosition(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		extractSubPoints();
	}

	public void setPoints(List<Point> points) {
		if (pointsManager == null) {
			pointsManager = new PointsManager(points);
		} else {
			this.pointsManager.setPoints(points);
		}
	}

	private void extractSubPoints() {
		shownPoints = pointsManager.getNearPoints(latitude, longitude,
				getMaxDistance());

	}

	public void putRenderer(String name, PointRenderer renderer) {
		renderers.put(name, renderer);
	}

	/***
	 * Returns the correct size of the control when needed (Basically
	 * maintaining the ratio)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int measuredWidth = getDefaultSize(getSuggestedMinimumWidth(),
				widthMeasureSpec);
		int measuredHeight = getDefaultSize(getSuggestedMinimumHeight(),
				heightMeasureSpec);

		size = Math.max(measuredWidth, measuredHeight);
		center = size / 2;
		setMeasuredDimension(size, size);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		if (shownPoints != null) {
			double azRadians = Math.toRadians(azimuth);
			drawBackground(canvas, -azimuth);

			Paint pointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			pointPaint.setColor(Color.GREEN);
			SimplePointRenderer simplePoint = new SimplePointRenderer();

			for (Point point : shownPoints) {

				double pointAngle = getAngle(point) + azRadians;
				double pixelDistance = point.getDistance() * center
						/ getMaxDistance();
				double pointy = center - pixelDistance * Math.sin(pointAngle);
				double pointx = center + pixelDistance * Math.cos(pointAngle);
				point.setX((float) pointx);
				point.setY((float) pointy);

				if (point.getRendererName() != null
						&& renderers.containsKey(point.getRendererName())) {
					PointRenderer renderer = renderers.get(point
							.getRendererName());
					renderer.drawPoint(point, canvas, azimuth);
				} else {
					simplePoint.drawPoint(point, canvas, azimuth);
				}

			}
			pointPaint.setColor(Color.RED);
			canvas.drawCircle(center, center, 10, pointPaint);
		}
	}

	private void drawBackground(Canvas canvas, double azimuth) {
		if (rotableBackground != 0) {
			Bitmap bg = BitmapFactory.decodeResource(context.getResources(),
					rotableBackground);

			Matrix transform = new Matrix();
			transform.setRectToRect(
					new RectF(0, 0, bg.getWidth(), bg.getHeight()), new RectF(
							0, 0, size, size), Matrix.ScaleToFit.CENTER);
			transform.preRotate((float) azimuth, bg.getWidth() / 2,
					bg.getHeight() / 2);
			canvas.drawBitmap(bg, transform, null);
		}
	}

	private double getAngle(Point point) {
		return Math.atan2(point.getLatitude() - latitude, point.getLongitude()
				- longitude);
	}

	public int getRotableBackground() {
		return rotableBackground;
	}

	public void setRotableBackground(int rotableBackground) {
		this.rotableBackground = rotableBackground;
	}

	public double getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(double maxDistance) {
		this.maxDistance = maxDistance;
		this.invalidate();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			Point p = findNearestPoint(event.getX(), event.getY());
			if (p != null && getOnPointPressedListener()!=null) {
				onPointPressedListener.onPointPressed(p);
			}
		}

		return super.onTouchEvent(event);
	}

	private Point findNearestPoint(float x, float y) {
		Point p = null;
		Double minorDistance = (double) size;
		for (Point point : shownPoints) {
			double distance = Math.sqrt(Math.pow((point.getX() - x), 2)
					+ Math.pow((point.getY() - y), 2));
			if (distance < minorDistance) {
				minorDistance = distance;
				p = point;
			}
		}
		if (minorDistance <= MAX_DISTANCE_TAP) {
			return p;
		}else{
			return null;
		}
	}
	
	public OnPointPressedListener getOnPointPressedListener() {
		return onPointPressedListener;
	}

	public void setOnPointPressedListener(OnPointPressedListener onPointPressedListener) {
		this.onPointPressedListener = onPointPressedListener;
	}

	public interface OnPointPressedListener {
		public void onPointPressed(Point p);
	}
}
