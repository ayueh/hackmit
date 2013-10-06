package com.example.taketwo;

import android.R;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class tenVals extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	private float gravity[] = new float[3];
	private final float alpha = .8f;
	
	private TextView xCoor; // declare X axis object
	private TextView yCoor; // declare Y axis object
	private TextView zCoor; // declare Z axis object\
	private TextView xMax;
	private TextView xMin;
	private TextView yMax;
	private TextView yMin;
	private TextView zMax;
	private TextView zMin;
	private TextView magnitude;
	private double xmin, xmax, ymin, ymax, zmin, zmax, mag, maxMag;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ten_values);
		
		xCoor=(TextView)findViewById(R.id.xcoor); // create X axis object
		yCoor=(TextView)findViewById(R.id.ycoor); // create Y axis object
		zCoor=(TextView)findViewById(R.id.zcoor); // create Z axis object
		xMax = (TextView)findViewById(R.id.xMax); 
		xMin =(TextView)findViewById(R.id.xMin); 
		yMax=(TextView)findViewById(R.id.yMax); 
		yMin =(TextView)findViewById(R.id.yMin); 
		zMax=(TextView)findViewById(R.id.zMax); 
		zMin =(TextView)findViewById(R.id.zMin);
		magnitude = (TextView)findViewById(R.id.magnitude);
		
		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		/*	More sensor speeds (taken from api docs)
		    SENSOR_DELAY_FASTEST get sensor data as fast as possible
		    SENSOR_DELAY_GAME	rate suitable for games
		 	SENSOR_DELAY_NORMAL	rate (default) suitable for screen orientation changes
		*/
	}

	public void onAccuracyChanged(Sensor sensor,int accuracy){
		
	}
	
	public void reset(View view) {
	    // Do something in response to button
		xmax = xmin = ymax = ymin = zmax = zmin = maxMag = 0;
	}
	
	public void onSensorChanged(SensorEvent event){
		
		// check sensor type
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			
			// assign directions
			//float x=event.values[0];
			//float y=event.values[1];
			//float z=event.values[2];
			

	          gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
	          gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
	          gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

	        double x = event.values[0] - gravity[0];
	        double y = event.values[1] - gravity[1];
	        double z = event.values[2] - gravity[2];
	        mag = Math.sqrt(Math.pow(xmax,2) + Math.pow(ymax,2) + Math.pow(zmax,2));

			xCoor.setText("X: "+x);
			yCoor.setText("Y: "+y);
			zCoor.setText("Z: "+z);
			if (x> xmax)
			{
				xmax = x;
				xMax.setText("xMax: " + x);
			}
			else if (x < xmin)
			{
				xmin = x;
				xMin.setText("xMin: " + x);
			}
			
			if (y > ymax)
			{
				ymax = y;
				yMax.setText("yMax: " + y);
			}
			else if (y < ymin)
			{
				ymin = y;
				yMin.setText("yMin: " + y);
			}
			
			if (z> zmax)
			{
				zmax = z;
				zMax.setText("zMax: " + z);
			}
			else if (z < zmin)
			{
				zmin = z;
				zMin.setText("zMin: " + z);
			}
			if(mag > maxMag)
			{
				magnitude.setText("Max Mag: " + mag);
				maxMag = mag;
			}
		}
	}
}