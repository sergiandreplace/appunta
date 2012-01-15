package com.sergiandreplace.sensors;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.sergiandreplace.appunta.CompassManager;
import com.sergiandreplace.appunta.CompassManager.OnCompassChangedListener;
import com.sergiandreplace.appunta.Point;
import com.sergiandreplace.appunta.pointdrawer.DrawablePointRenderer;
import com.sergiandreplace.appunta.pointdrawer.SimplePointRenderer;
import com.sergiandreplace.appunta.ui.RadarView;
import com.sergiandreplace.appunta.ui.RadarView.OnPointPressedListener;

public class SensorsActivity extends Activity implements OnCompassChangedListener, OnSeekBarChangeListener, OnPointPressedListener {

	TextView textviewAzimuth, textviewPitch, textviewRoll;
	private RadarView radar;
	private float lastAzimuth=0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		CompassManager compass=new CompassManager(this);
		compass.setOnCompassChangeListener(this);
		radar = (RadarView) findViewById(R.id.radarView1);
		radar.putRenderer("simple", new DrawablePointRenderer(this.getResources(), R.drawable.marker));
		radar.setOnPointPressedListener(this);
		List<Point> points=new ArrayList<Point>();
		points.add(new Point(1,40.418889, -3.691944, "simple","Madrid")); //Madrid
		points.add(new Point(2,48.86223, 2.351074, "simple","Paris"));//Paris
		points.add(new Point(3,43.60426,1.44367, "simple","Toulousse")); //TOulousse
		points.add(new Point(4,41.65,-0.88, "simple","Zaragoza")); //Zaragoza
		
		Random rand=new Random();
		for (int i=1;i<0;i++) {
			points.add(new Point(5+i,33+rand.nextDouble()*14, -10+rand.nextDouble()*20));
		}
		radar.setPoints(points);
		radar.setPosition(41.3825, 2.176944);//BCN
		radar.setRotableBackground(R.drawable.arrow);
		
		((SeekBar)findViewById(R.id.seekBar1)).setOnSeekBarChangeListener(this);
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
		radar.setOrientation(azimuth);
		
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		radar.setMaxDistance(arg1);
		radar.invalidate();
		this.setTitle("Distance: " + arg1 + "m.");
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPointPressed(Point p) {
		Toast.makeText(this, p.getName(), Toast.LENGTH_LONG).show();
	}
}