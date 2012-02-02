package com.sergiandreplace.appunta.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sergiandreplace.appunta.location.Location;
import com.sergiandreplace.appunta.orientation.Orientation;
import com.sergiandreplace.appunta.orientation.OrientationManager;
import com.sergiandreplace.appunta.orientation.OrientationManager.OnOrientationChangedListener;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.Points;
import com.sergiandreplace.appunta.point.renderer.AugmentedDrawablePointRenderer;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;
import com.sergiandreplace.appunta.ui.AppuntaView.OnPointPressedListener;
import com.sergiandreplace.appunta.ui.CameraView;
import com.sergiandreplace.appunta.ui.PanoramaView;
import com.sergiandreplace.appunta.ui.RadarView;

public class AugmentedRealityActivity extends Activity implements OnOrientationChangedListener, OnPointPressedListener {

	TextView textviewAzimuth, textviewPitch, textviewRoll;
	private PanoramaView ar;
	private RadarView cv;
	private CameraView camera;
	private FrameLayout cameraFrame;
	private OrientationManager compass;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ar);
		compass = new OrientationManager(this);
		compass.setOnOrientationChangeListener(this);
		
		ar = (PanoramaView) findViewById(R.id.augmentedView1);
        cv = (RadarView) findViewById(R.id.radarView1);

        ar.setOnPointPressedListener(this);
        cv.setOnPointPressedListener(this);

		PointRenderer arRenderer=new AugmentedDrawablePointRenderer(this.getResources(),
                R.drawable.city);
		
		
		Points points=PointsModel.getPoints(arRenderer);
		Points cpoints=PointsModel.getPoints(null);
		
		ar.setPoints(points);
		ar.setPosition(new Location(41.3825, 2.176944));//BCN
		ar.setOnPointPressedListener(this);
		cv.setPoints(cpoints);
		cv.setPosition(new Location(41.3825, 2.176944));//BCN
		cv.setRotableBackground(R.drawable.arrow);
		
		cameraFrame=(FrameLayout) findViewById(R.id.cameraFrame);
		camera=new CameraView(this);
		cameraFrame.addView(camera);
		
	}
	@Override
	protected void onPause() {
		super.onPause();
		compass.stopSensor();
	}
	@Override
	protected void onResume() {
		super.onPause();
		compass.startSensor(this);
	}
	
	@Override
	public void onOrientationChanged(Orientation orientation) {

		ar.setOrientation(orientation);
		cv.setOrientation(orientation);
		
		
	}

	@Override
	public void onPointPressed(Point p) {
		Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show();
	}
}

