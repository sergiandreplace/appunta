/*
   Copyright Sergi Martínez (@sergiandreplace)

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

package com.sergiandreplace.sample.appunta;

import java.util.List;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.sergiandreplace.appunta.location.LocationFactory;
import com.sergiandreplace.appunta.orientation.Orientation;
import com.sergiandreplace.appunta.orientation.OrientationManager;
import com.sergiandreplace.appunta.orientation.OrientationManager.OnOrientationChangedListener;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.renderer.impl.DrawablePointRenderer;
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
		radar.setMaxDistance(1);
		
		DrawablePointRenderer renderer=new DrawablePointRenderer(this.getResources(), R.drawable.marker);
		List<Point> points=PointsModel.getPoints(renderer);
		radar.setPoints(points);
		radar.setPosition(LocationFactory.createLocation(41.405098,2.192363));//BCN
		
		radar.setRotableBackground(R.drawable.arrow);
		
		((SeekBar)findViewById(R.id.seekBar1)).setOnSeekBarChangeListener(this);
		
		
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
	

	
}

