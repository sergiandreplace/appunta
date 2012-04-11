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

package com.sergiandreplace.appunta.orientation;

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
public class OrientationManager implements SensorEventListener {
	
	private static final float SMOOTH_FACTOR = 0.3f;
	private static final float SMOOTH_THRESHOLD = 30.0f;

	private SensorManager sensorManager;
	private Orientation orientation = new Orientation();
	private Orientation oldOrientation;
	private List<Sensor> sensors;
	private boolean sensorRunning = false;
	private OnOrientationChangedListener onOrientationChangeListener;

	/***
	 * This constructor will generate and start a Compass Manager
	 * 
	 * @param activity
	 *            The activity where the service will work
	 */
	public OrientationManager(Activity activity) {
		startSensor(activity);
	}

	/***
	 * This constructor will generate a Compass Manager, but it will need to be
	 * started manually using {@link #startSensor}
	 */
	public OrientationManager() {

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
						SensorManager.SENSOR_DELAY_UI);
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
		if (oldOrientation==null) {
			orientation.setAzimuth(event.values[0]);
			orientation.setPitch(event.values[1]);
			orientation.setRoll(event.values[2]);
		}else{
			orientation.setAzimuth(lowPass(event.values[0], oldOrientation.getAzimuth()));
			orientation.setPitch(lowPass(event.values[1], oldOrientation.getPitch()));
			orientation.setRoll(lowPass(event.values[2], oldOrientation.getRoll()));
		}
		oldOrientation=orientation;
		if (getOnCompassChangeListener() != null) {
			getOnCompassChangeListener().onOrientationChanged(orientation);
		}
	}
	
	
	/**
	 * Applies a lowpass filter to the change in the lecture of the sensor
	 * @param newValue the new sensor value
	 * @param lowValue the old sensor value
	 * @return and intermediate value
	 */
	public float lowPass(float newValue, float lowValue) {
		float compass;
		if (Math.abs(newValue - lowValue) < 180) {
		    if (Math.abs(newValue - lowValue) > SMOOTH_THRESHOLD) {
		    	compass = newValue;
		    }
		    else {
		    	compass = lowValue + SMOOTH_FACTOR * (newValue - lowValue);
		    }
		}
		else {
		    if (360.0 - Math.abs(newValue - lowValue) > SMOOTH_THRESHOLD) {
		    	compass = newValue;
		    }
		    else {
		        if (lowValue > newValue) {
		        	compass = (lowValue + SMOOTH_FACTOR * ((360 + newValue - lowValue) % 360) + 360) % 360;
		        } 
		        else {
		        	compass = (lowValue - SMOOTH_FACTOR * ((360 - newValue + lowValue) % 360) + 360) % 360;
		        }
		    }
		}
		return compass;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Space for rent
	}

	/***
	 * We stop "hearing" the sensors
	 */
	public void stopSensor() {
		if (sensorRunning) {
			sensorManager.unregisterListener(this);
			sensorRunning=false;
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

	public OnOrientationChangedListener getOnCompassChangeListener() {
		return onOrientationChangeListener;
	}

	public void setOnOrientationChangeListener(
			OnOrientationChangedListener onOrientationChangeListener) {
		this.onOrientationChangeListener = onOrientationChangeListener;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public interface OnOrientationChangedListener {
		/***
		 * This method will be invoked when the magnetic orientation of the
		 * phone changed
		 * 
		 * @param azimuth
		 *            Orientation on degrees. 360-0 is north.
		 */
		public void onOrientationChanged(Orientation orientation);
	}

}
