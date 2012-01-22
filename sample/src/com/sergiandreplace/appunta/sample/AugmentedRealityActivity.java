package com.sergiandreplace.appunta.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sergiandreplace.appunta.CompassManager;
import com.sergiandreplace.appunta.CompassManager.OnCompassChangedListener;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.Points;
import com.sergiandreplace.appunta.point.renderer.AugmentedDrawablePointRenderer;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;
import com.sergiandreplace.appunta.ui.AppuntaView.OnPointPressedListener;
import com.sergiandreplace.appunta.ui.CameraView;
import com.sergiandreplace.appunta.ui.PanoramaView;
import com.sergiandreplace.appunta.ui.RadarView;

public class AugmentedRealityActivity extends Activity implements OnCompassChangedListener, OnPointPressedListener {

	TextView textviewAzimuth, textviewPitch, textviewRoll;
	private PanoramaView ar;
	private RadarView cv;
	private CameraView camera;
	private FrameLayout cameraFrame;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.ar);
		CompassManager compass = new CompassManager(this);
		compass.setOnCompassChangeListener(this);
		
		ar = (PanoramaView) findViewById(R.id.augmentedView1);
        cv = (RadarView) findViewById(R.id.radarView1);

        ar.setOnPointPressedListener(this);
        cv.setOnPointPressedListener(this);

		PointRenderer arRenderer=new AugmentedDrawablePointRenderer(this.getResources(),
                R.drawable.city);
		
		
		Points points=PointsModel.getPoints(arRenderer);
		Points cpoints=PointsModel.getPoints(null);
		
		ar.setPoints(points);
		ar.setPosition(41.3825, 2.176944);//BCN
		ar.setOnPointPressedListener(this);
		cv.setPoints(cpoints);
		cv.setPosition(41.3825, 2.176944);//BCN
		cv.setRotableBackground(R.drawable.arrow);
		
		cameraFrame=(FrameLayout) findViewById(R.id.cameraFrame);
		camera=new CameraView(this);
		cameraFrame.addView(camera);
		
	}
	@Override
	public void onCompassChanged(float azimuth) {

		ar.setAzimuth(azimuth);
		cv.setAzimuth(azimuth);
		
		
	}

	@Override
	public void onPointPressed(Point p) {
		Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show();
	}
}

