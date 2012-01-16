package com.sergiandreplace.sample;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.sergiandreplace.appunta.CompassManager;
import com.sergiandreplace.appunta.CompassManager.OnCompassChangedListener;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.renderer.DrawablePointRenderer;
import com.sergiandreplace.appunta.ui.AppuntaView.OnPointPressedListener;
import com.sergiandreplace.appunta.ui.PanoramaView;
import com.sergiandreplace.appunta.ui.RadarView;

public class AugmentedActivity extends Activity implements
		OnCompassChangedListener, OnPointPressedListener {

	TextView textviewAzimuth, textviewPitch, textviewRoll;
	private PanoramaView ar;
	private RadarView cv;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ar);

		CompassManager compass = new CompassManager(this);
		compass.setOnCompassChangeListener(this);
		ar = (PanoramaView) findViewById(R.id.augmentedView1);
		cv = (RadarView) findViewById(R.id.radarView1);

		ar.putRenderer("simple", new DrawablePointRenderer(this.getResources(),
				R.drawable.marker));
		ar.setOnPointPressedListener(this);
		cv.putRenderer("simple", new DrawablePointRenderer(this.getResources(),
				R.drawable.marker));
		cv.setOnPointPressedListener(this);

		List<Point> points = new ArrayList<Point>();
		points.add(new Point(1, 40.418889, -3.691944, "simple", "Madrid")); // Madrid
		points.add(new Point(2, 48.86223, 2.351074, "simple", "Paris"));// Paris
		points.add(new Point(3, 43.60426, 1.44367, "simple", "Toulousse")); // TOulousse
		points.add(new Point(4, 41.65, -0.88, "simple", "Zaragoza")); // Zaragoza
		points.add(new Point(5, 39.566667, 2.65, "simple", "Palma"));
		points.add(new Point(6, 43.2976, 5.377223, "simple", "Marsella"));
		points.add(new Point(7, 36.833333, 10.15, "simple", "Tunez"));
		points.add(new Point(8, 41.9, 12.5, "simple", "Roma"));
		points.add(new Point(9, 38.98, 1.43, "simple", "Ibiza"));
		points.add(new Point(7, 39.966667, 4.083333, "simple", "Menorca"));

		List<Point> cpoints = new ArrayList<Point>();
		cpoints.add(new Point(1, 40.418889, -3.691944, "simple", "Madrid")); // Madrid
		cpoints.add(new Point(2, 48.86223, 2.351074, "simple", "Paris"));// Paris
		cpoints.add(new Point(3, 43.60426, 1.44367, "simple", "Toulousse")); // TOulousse
		cpoints.add(new Point(4, 41.65, -0.88, "simple", "Zaragoza")); // Zaragoza
		cpoints.add(new Point(5, 39.566667, 2.65, "simple", "Palma"));
		cpoints.add(new Point(6, 43.2976, 5.377223, "simple", "Marsella"));
		cpoints.add(new Point(7, 36.833333, 10.15, "simple", "Tunez"));
		cpoints.add(new Point(8, 41.9, 12.5, "simple", "Roma"));
		cpoints.add(new Point(9, 38.98, 1.43, "simple", "Ibiza"));
		cpoints.add(new Point(7, 39.966667, 4.083333, "simple", "Menorca"));
		
	
		ar.setPoints(points);
		ar.setPosition(41.3825, 2.176944);// BCN
		cv.setPoints(cpoints);
		cv.setPosition(41.3825, 2.176944);// BCN

		cv.setRotableBackground(R.drawable.arrow);
	}

	@Override
	public void onCompassChanged(float azimuth) {
		ar.setOrientation(azimuth);
		cv.setOrientation(azimuth);

	}

	@Override
	public void onPointPressed(Point p) {
		Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show();
	}
}