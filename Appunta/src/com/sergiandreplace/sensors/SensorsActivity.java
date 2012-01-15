package com.sergiandreplace.sensors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.sergiandreplace.appunta.CompassManager;
import com.sergiandreplace.appunta.CompassManager.OnCompassChangedListener;
import com.sergiandreplace.appunta.Point;
import com.sergiandreplace.appunta.pointdrawer.DrawablePointRenderer;
import com.sergiandreplace.appunta.pointdrawer.SimplePointRenderer;
import com.sergiandreplace.appunta.ui.RadarView;

public class SensorsActivity extends Activity implements OnCompassChangedListener {

	TextView textviewAzimuth, textviewPitch, textviewRoll;
	private RadarView radar;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		CompassManager compass=new CompassManager(this);
		compass.setOnCompassChangeListener(this);
		radar = (RadarView) findViewById(R.id.radarView1);
		radar.putRenderer("drawable", new SimplePointRenderer());
		radar.putRenderer("simple", new DrawablePointRenderer(this.getResources(), R.drawable.marker));
		List<Point> points=new ArrayList<Point>();
		points.add(new Point(1,40.418889, -3.691944, "simple")); //Madrid
		points.add(new Point(2,48.86223, 2.351074, "simple"));//Paris
		points.add(new Point(3,43.60426,1.44367, "simple")); //TOulousse
		points.add(new Point(4,41.65,-0.88, "simple")); //Zaragoza
		
		Random rand=new Random();
		for (int i=1;i<90;i++) {
			points.add(new Point(5+i,33+rand.nextDouble()*14, -10+rand.nextDouble()*20,"drawable"));
		}
		radar.setPoints(points);
		radar.setPosition(41.3825, 2.176944);//BCN
		radar.setRotableBackground(R.drawable.bkg);
	}

	@Override
	public void onCompassChanged(float azimuth) {
		radar.setOrientation(azimuth);
	}
}