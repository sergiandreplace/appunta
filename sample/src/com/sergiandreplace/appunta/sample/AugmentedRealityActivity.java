package com.sergiandreplace.appunta.sample;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sergiandreplace.appunta.CompassManager;
import com.sergiandreplace.appunta.CompassManager.OnCompassChangedListener;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.renderer.AugmentedDrawablePointRenderer;
import com.sergiandreplace.appunta.ui.AppuntaView.OnPointPressedListener;
import com.sergiandreplace.appunta.ui.CameraView;
import com.sergiandreplace.appunta.ui.PanoramaView;
import com.sergiandreplace.appunta.ui.RadarView;

public class AugmentedRealityActivity extends Activity implements OnCompassChangedListener, OnPointPressedListener {

	TextView textviewAzimuth, textviewPitch, textviewRoll;
	private float lastAzimuth = 0;
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

        ar.putRenderer("drawable", new AugmentedDrawablePointRenderer(this.getResources(),
                        R.drawable.marker));
        ar.setOnPointPressedListener(this);
        cv.setOnPointPressedListener(this);

		
		
		
		List<Point> points=PointsModel.getPoints("drawable");
		List<Point> cpoints=PointsModel.getPoints(null);
		
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
		if (Math.abs(lastAzimuth-azimuth)>2) {
			if (azimuth>lastAzimuth) {
				lastAzimuth+=2;
			}else{
				lastAzimuth-=2;
			}
		}else{
			lastAzimuth=azimuth;
		}
		ar.setOrientation(azimuth);
		cv.setOrientation(azimuth);
		
		
	}

	@Override
	public void onPointPressed(Point p) {
		Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show();
	}
}

