package com.sergiandreplace.appunta.sample;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.sergiandreplace.appunta.CompassManager;
import com.sergiandreplace.appunta.CompassManager.OnCompassChangedListener;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.renderer.DrawablePointRenderer;
import com.sergiandreplace.appunta.ui.AppuntaView.OnPointPressedListener;
import com.sergiandreplace.appunta.ui.RadarView;

public class RadarActivity extends Activity implements OnCompassChangedListener, OnPointPressedListener, OnSeekBarChangeListener {

	TextView textviewAzimuth, textviewPitch, textviewRoll;
	private RadarView radar;
	private float lastAzimuth = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.radar);
		CompassManager compass = new CompassManager(this);
		compass.setOnCompassChangeListener(this);
		radar = (RadarView) findViewById(R.id.radarView1);
		radar.putRenderer("drawable", new DrawablePointRenderer(this.getResources(), R.drawable.marker));
		radar.setOnPointPressedListener(this);
		List<Point> points=PointsModel.getPoints("drawable");
		
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
		Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show();
	}
}

