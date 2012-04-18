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
import android.widget.FrameLayout;
import android.widget.Toast;

import com.sergiandreplace.appunta.location.LocationFactory;
import com.sergiandreplace.appunta.orientation.Orientation;
import com.sergiandreplace.appunta.orientation.OrientationManager;
import com.sergiandreplace.appunta.orientation.OrientationManager.OnOrientationChangedListener;
import com.sergiandreplace.appunta.point.Point;
import com.sergiandreplace.appunta.point.renderer.PointRenderer;
import com.sergiandreplace.appunta.point.renderer.impl.EyeViewRenderer;
import com.sergiandreplace.appunta.point.renderer.impl.SimplePointRenderer;
import com.sergiandreplace.appunta.ui.AppuntaView.OnPointPressedListener;
import com.sergiandreplace.appunta.ui.CameraView;
import com.sergiandreplace.appunta.ui.EyeView;
import com.sergiandreplace.appunta.ui.RadarView;

public class EyeViewActivity extends Activity implements
		OnOrientationChangedListener, OnPointPressedListener {

	private EyeView ar;
	private RadarView cv;
	private CameraView camera;
	private FrameLayout cameraFrame;
	private OrientationManager compass;
	
	float x,y,z;
	private List<Point> points;
	private List<Point> cpoints;

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
		ar.setMaxDistance(3);
		cv.setMaxDistance(1);
		
		ar.setOnPointPressedListener(this);
		cv.setOnPointPressedListener(this);

		PointRenderer arRenderer = new EyeViewRenderer(getResources(), R.drawable.circle_selected,R.drawable.circle_unselected);

		points = PointsModel.getPoints(arRenderer);
		cpoints = PointsModel.getPoints(new SimplePointRenderer());

		ar.setPoints(points);
		ar.setPosition(LocationFactory.createLocation(41.405098,2.192363));// BCN
		ar.setOnPointPressedListener(this);
		cv.setPoints(cpoints);
		cv.setPosition(LocationFactory.createLocation(41.405098,2.192363));// BCN
		cv.setRotableBackground(R.drawable.arrow);

		cameraFrame = (FrameLayout) findViewById(R.id.cameraFrame);
		camera = new CameraView(this);
		cameraFrame.addView(camera);


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
		super.onStart();
		compass.startSensor(this);

	}

	@Override
	public void onOrientationChanged(Orientation orientation) {

		

		ar.setOrientation(orientation);
		ar.setPhoneRotation(OrientationManager.getPhoneRotation(this));
		cv.setOrientation(orientation);

	}




	@Override
	public void onPointPressed(Point p) {
		Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show();
		unselectAllPoints();
		p.setSelected(true);
	}

	private void unselectAllPoints() {
		for (Point point: points) {
			point.setSelected(false);
		}
	}

}
