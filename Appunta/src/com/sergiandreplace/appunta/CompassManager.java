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

package com.sergiandreplace.appunta;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * This class is responsible for providing the measure of the compass (in the 3
 * axis) everytime it changes and dealing with the service
 * 
 * @author Sergi Martínez
 * 
 */
public class CompassManager implements SensorEventListener {

	private SensorManager sensorManager;
	private float azimuth;
	private float pitch;
	private float roll;
	private List<Sensor> sensors;
	private boolean sensorRunning = false;
	private OnCompassChangedListener onCompassChangeListener;
	private OnPitchChangedListener onPitchChangedListener;
	private OnRollChangedListener onRollChangedListener;

	/***
	 * This constructor will generate and start a Compass Manager
	 * 
	 * @param activity
	 *            The activity where the service will work
	 */
	public CompassManager(Activity activity) {
		startSensor(activity);
	}

	/***
	 * This constructor will generate a Compass Manager, but it will need to be
	 * started manually using {@link #startSensor}
	 */
	public CompassManager() {

	}

	/***
	 * This method registers this class as a listener of the Sensor service
	 * 
	 * @param activity
	 *            The activity over this will work
	 */
	public void startSensor(Activity activity) {
		if (!sensorRunning) {
			sensorManager = (SensorManager) activity
					.getSystemService(Context.SENSOR_SERVICE);
			sensors = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
			if (sensors.size() > 0) {
				sensorManager.registerListener(this, sensors.get(0),
						SensorManager.SENSOR_DELAY_NORMAL);
				sensorRunning = true;
			}
		}
	}
	/***
	 * Detects a change in a sensor and warns the appropiate listener.
	 */
	@Override
	public void onSensorChanged(SensorEvent event) {
		// Check azimuth (N - S - W - E)
		if (this.azimuth != event.values[0]) {
			this.azimuth = event.values[0];
			if (getOnCompassChangeListener() != null) {
				getOnCompassChangeListener().onCompassChanged(azimuth);
			}
		}
		// Check pitch - phone flat or standing up
		if (this.pitch != event.values[1]) {
			this.pitch = event.values[1];
			if (getOnPitchChangedListener() != null) {
				getOnPitchChangedListener().onPitchChanged(roll);
			}
		}
		// Check roll - driving wheel movement
		if (this.roll != event.values[2]) {
			this.roll = event.values[2];
			if (getOnRollChangedListener() != null) {
				getOnRollChangedListener().onRollChanged(roll);
			}
		}
	}

	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		//Space for rent
	}
	
	/***
	 * We stop "hearing" the sensors
	 */
	private void stopSensor() {
		if (sensorRunning) {
			sensorManager.unregisterListener(this);
		}
	}

	/***
	 * Just in case, we stop the sensor
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		stopSensor();
	}

	// Setters and getter for the three listeners (Bob, Moe and Curly)
	
	public OnCompassChangedListener getOnCompassChangeListener() {
		return onCompassChangeListener;
	}

	public void setOnCompassChangeListener(
			OnCompassChangedListener onCompassChangeListener) {
		this.onCompassChangeListener = onCompassChangeListener;
	}

	public OnPitchChangedListener getOnPitchChangedListener() {
		return onPitchChangedListener;
	}

	public void setOnPitchChangedListener(OnPitchChangedListener onPitchChangedListener) {
		this.onPitchChangedListener = onPitchChangedListener;
	}

	public OnRollChangedListener getOnRollChangedListener() {
		return onRollChangedListener;
	}

	public void setOnRollChangedListener(OnRollChangedListener onRollChangedListener) {
		this.onRollChangedListener = onRollChangedListener;
	}

	
	public interface OnCompassChangedListener {
		/***
		 * This method will be invoked when the magnetic orientation of the phone
		 * changed
		 * @param azimuth Orientation on degrees. 360-0 is north.
		 */
		public void onCompassChanged(float azimuth);
	}

	public interface OnPitchChangedListener {
		/***
		 * This method will be invoked when the pitch of the phone changes. 
		 * @param pitch 0 standing up, 90 flat, 180 upside-down, etc
		 */
		public void onPitchChanged(float pitch);
	}

	public interface OnRollChangedListener {
		/***
		 * This method will be invoked when the roll of the phone changes. 
		 * @param roll Changes when moving phone as a steering wheel
		 */
		public void onRollChanged(float roll);
	}
}
