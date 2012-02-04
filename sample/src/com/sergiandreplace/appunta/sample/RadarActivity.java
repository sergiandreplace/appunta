package com.sergiandreplace.appunta.sample;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.sergiandreplace.appunta.location.Location;
import com.sergiandreplace.appunta.orientation.Orientation;
import com.sergiandreplace.appunta.orientation.OrientationManager;
import com.sergiandreplace.appunta.orientation.OrientationManager.OnOrientationChangedListener;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.Points;
import com.sergiandreplace.appunta.point.renderer.DrawablePointRenderer;
import com.sergiandreplace.appunta.ui.AppuntaView.OnPointPressedListener;
import com.sergiandreplace.appunta.ui.RadarView;

public class RadarActivity extends Activity implements OnOrientationChangedListener, OnPointPressedListener, OnSeekBarChangeListener {

	TextView textviewAzimuth, textviewPitch, textviewRoll;
	private RadarView radar;
	private OrientationManager compass;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.radar);
		compass = new OrientationManager(this);
		compass.setOnOrientationChangeListener(this);
		radar = (RadarView) findViewById(R.id.radarView1);
		radar.setOnPointPressedListener(this);
		
		DrawablePointRenderer renderer=new DrawablePointRenderer(this.getResources(), R.drawable.marker);
		Points points=PointsModel.getPoints(renderer);
		radar.setDeviceOrientation(getDeviceOrientation());
		radar.setPoints(points);
		radar.setPosition(new Location(41.3825, 2.176944));//BCN
		radar.setRotableBackground(R.drawable.arrow);
		
		((SeekBar)findViewById(R.id.seekBar1)).setOnSeekBarChangeListener(this);
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		radar.setDeviceOrientation(newConfig.orientation);

	}
	
	@Override
	protected void onPause() {
		super.onPause();
		compass.stopSensor();
	}
	@Override
	protected void onResume() {
		super.onResume();
		compass.startSensor(this);
	}
	
	@Override
	public void onOrientationChanged(Orientation orientation) {
		radar.setOrientation(orientation);
		
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
	
	private int getDeviceOrientation() {
		Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
		return display.getOrientation();
	}
}

