
package com.sergiandreplace.appunta;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class CompassManager implements SensorEventListener {
	
	private SensorManager sensorManager;
	private float azimuth;
//	private float pitch;
//	private float roll;
	private List<Sensor> sensors;
	private boolean sensorRunning=false;
	private OnCompassChangedListener onCompassChangeListener;

	public CompassManager(Activity activity) {
		startSensor(activity);
	}
	
	public CompassManager() {
		
	}
	
	public void startSensor(Activity activity) {
		if (!sensorRunning){
			sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
			sensors = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
			if (sensors.size() > 0) {
				sensorManager.registerListener(this,sensors.get(0),SensorManager.SENSOR_DELAY_NORMAL);
				sensorRunning=true;
			}
		}
	}

		@Override
		public void onSensorChanged(SensorEvent event) {
			this.azimuth=event.values[0];
			if (getOnCompassChangeListener()!=null) {
				getOnCompassChangeListener().onCompassChanged(azimuth);
			}
			//just in case we need it someday
			//this.pitch=event.values[1];
			//this.roll=event.values[2];

		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {

		}
		
		private void stopSensor(){
			if (sensorRunning) {
				sensorManager.unregisterListener(this);
			}
		}
		
		@Override
		protected void finalize() throws Throwable {
			super.finalize();
			stopSensor();
		}

		
		public OnCompassChangedListener getOnCompassChangeListener() {
			return onCompassChangeListener;
		}

		public void setOnCompassChangeListener(OnCompassChangedListener onCompassChangeListener) {
			this.onCompassChangeListener = onCompassChangeListener;
		}


		public interface OnCompassChangedListener {
			public void onCompassChanged(float azimuth);
		}
}
