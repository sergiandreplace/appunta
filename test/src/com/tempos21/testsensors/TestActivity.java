package com.tempos21.testsensors;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class TestActivity extends Activity implements SensorEventListener {

	// Accelerometer X, Y, and Z values
	private TextView accelXValue;
	private TextView accelYValue;
	private TextView accelZValue;

	// Orientation X, Y, and Z values
	private TextView orientXValue;
	private TextView orientYValue;
	private TextView orientZValue;

	private SensorManager sensorManager = null;
	private float[] accelValues;
	private float[] magValues;
	private float[] r=new float[9];
	private float[] i=new float[9];

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get a reference to a SensorManager
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		setContentView(R.layout.main);

		// Capture accelerometer related view elements
		accelXValue = (TextView) findViewById(R.id.accel_x_value);
		accelYValue = (TextView) findViewById(R.id.accel_y_value);
		accelZValue = (TextView) findViewById(R.id.accel_z_value);

		// Capture orientation related view elements
		orientXValue = (TextView) findViewById(R.id.orient_x_value);
		orientYValue = (TextView) findViewById(R.id.orient_y_value);
		orientZValue = (TextView) findViewById(R.id.orient_z_value);

		// Initialize accelerometer related view elements
		accelXValue.setText("0.00");
		accelYValue.setText("0.00");
		accelZValue.setText("0.00");

		// Initialize orientation related view elements
		orientXValue.setText("0.00");
		orientYValue.setText("0.00");
		orientZValue.setText("0.00");
	}

	// This method will update the UI on new sensor events
	public void onSensorChanged(SensorEvent sensorEvent) {
		synchronized (this) {
			if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				accelXValue.setText(Float
						.toString((int) (+sensorEvent.values[0])));
				accelYValue.setText(Float
						.toString((int) (+sensorEvent.values[1])));
				accelZValue.setText(Float
						.toString((int) (+sensorEvent.values[2])));
				this.accelValues = sensorEvent.values;
			}

			// if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			// orientXValue.setText(Float.toString(sensorEvent.values[0]));
			// orientYValue.setText(Float.toString(sensorEvent.values[1]));
			// orientZValue.setText(Float.toString(sensorEvent.values[2]));
			// }
			//
			if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
				this.magValues = sensorEvent.values;

			}
			if (magValues != null && accelValues != null) {
				SensorManager.getRotationMatrix(r, i, accelValues,magValues);
				if(r!=null) {

					orientXValue.setText(String.format("%.2f", r[2]));
					orientYValue.setText(String.format("%.2f", r[5]));
					orientZValue.setText(String.format("%.2f", r[8]));
				}
			}

		}

	}

	// I've chosen to not implement this method
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onResume() {
		super.onResume();
		// Register this class as a listener for the accelerometer sensor
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		// ...and the orientation sensor
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_NORMAL);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
				SensorManager.SENSOR_DELAY_NORMAL);

	}

	@Override
	protected void onStop() {
		// Unregister the listener
		sensorManager.unregisterListener(this);
		super.onStop();
	}

}
