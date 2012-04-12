package com.sergiandreplace.appunta.sample;

import java.util.List;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sergiandreplace.appunta.location.LocationBuilder;
import com.sergiandreplace.appunta.orientation.Orientation;
import com.sergiandreplace.appunta.orientation.OrientationManager;
import com.sergiandreplace.appunta.orientation.OrientationManager.OnOrientationChangedListener;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;
import com.sergiandreplace.appunta.point.renderer.impl.AugmentedDrawablePointRenderer;
import com.sergiandreplace.appunta.ui.AppuntaView.OnPointPressedListener;
import com.sergiandreplace.appunta.ui.CameraView;
import com.sergiandreplace.appunta.ui.EyeView;
import com.sergiandreplace.appunta.ui.RadarView;

public class AugmentedRealityActivity extends Activity implements
		OnOrientationChangedListener, OnPointPressedListener {

	private EyeView ar;
	private RadarView cv;
	private CameraView camera;
	private FrameLayout cameraFrame;
	private OrientationManager compass;
	private TextView axisOutputTextView;
	private String axisOutputString;
	float x,y,z;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ar);
		compass = new OrientationManager(this);
		compass.setAxisMode(OrientationManager.MODE_AR);
		compass.setOnOrientationChangeListener(this);
		compass.startSensor(this);

		ar = (EyeView) findViewById(R.id.augmentedView1);
		cv = (RadarView) findViewById(R.id.radarView1);
		ar.setMaxDistance(10000);
		ar.setOnPointPressedListener(this);
		cv.setOnPointPressedListener(this);

		PointRenderer arRenderer = new AugmentedDrawablePointRenderer(
				this.getResources(), R.drawable.city);

		List<Point> points = PointsModel.getPoints(arRenderer);
		List<Point> cpoints = PointsModel.getPoints(null);

		ar.setPoints(points);
		ar.setPosition(LocationBuilder.createLocation(41.3825, 2.176944));// BCN
		ar.setOnPointPressedListener(this);
		cv.setPoints(cpoints);
		cv.setPosition(LocationBuilder.createLocation(41.3825, 2.176944));// BCN
		cv.setRotableBackground(R.drawable.arrow);

		cameraFrame = (FrameLayout) findViewById(R.id.cameraFrame);
		camera = new CameraView(this);
		cameraFrame.addView(camera);

	
		axisOutputTextView = (TextView) findViewById(R.id.AxisOutput);
		axisOutputString = getString(R.string.AxisOutput);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);


	}

	@Override
	protected void onPause() {
		super.onPause();
		compass.stopSensor();
	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	@Override
	public void onOrientationChanged(Orientation orientation) {

		axisOutputTextView.setText(String.format(axisOutputString,
				orientation.getX(), orientation.getY(), orientation.getZ()));

		ar.setOrientation(orientation);
		cv.setOrientation(orientation);

	}


	public int getRotation() {
		return getWindowManager().getDefaultDisplay().getRotation();
	}

	@Override
	public void onPointPressed(Point p) {
		Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show();
	}

}
