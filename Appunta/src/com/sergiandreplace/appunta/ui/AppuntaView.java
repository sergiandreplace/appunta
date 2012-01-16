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

public abstract class AppuntaView extends View {

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

	
	private HashMap<String, PointRenderer> renderers = new HashMap<String, PointRenderer>();;

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

	public void setOrientation(float azimuth) {
		this.setAzimuth(azimuth);
		this.invalidate();
	}

	public float getOrientation() {
		return getAzimuth();
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

	protected void extractSubPoints() {
		setShownPoints(pointsManager.getNearPoints(latitude, longitude,
				getMaxDistance()));

	}

	public void putRenderer(String name, PointRenderer renderer) {
		getRenderers().put(name, renderer);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		preRender(canvas);
		if (getShownPoints() != null) {
			for (Point point : getShownPoints()) {
				setPointCoordinates(point);
				PointRenderer renderer = obtainRenderer(point);
				renderer.drawPoint(point, canvas, getAzimuth());
			}
		}
		postRender(canvas);
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

	protected abstract void preRender(Canvas canvas);

	protected abstract void setPointCoordinates(Point point);

	protected abstract void postRender(Canvas canvas);

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

	protected double getAngle(Point point) {
		return Math.atan2(point.getLatitude() - latitude, point.getLongitude()
				- longitude);
	}

	public OnPointPressedListener getOnPointPressedListener() {
		return onPointPressedListener;
	}

	public void setOnPointPressedListener(
			OnPointPressedListener onPointPressedListener) {
		this.onPointPressedListener = onPointPressedListener;
	}

	public interface OnPointPressedListener {
		public void onPointPressed(Point p);
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

	protected List<Point> getShownPoints() {
		return shownPoints;
	}

	protected void setShownPoints(List<Point> shownPoints) {
		this.shownPoints = shownPoints;
	}

	protected float getAzimuth() {
		return azimuth;
	}

	protected void setAzimuth(float azimuth) {
		this.azimuth = azimuth;
		this.azimuthRadians = Math.toRadians(azimuth);
	}

	protected double getAzimuthRadians() {
		return azimuthRadians;
	}

	public HashMap<String, PointRenderer> getRenderers() {
		return renderers;
	}

	public void setRenderers(HashMap<String, PointRenderer> renderers) {
		this.renderers = renderers;
	}



}
