package com.example.takethree;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class accelerometerlist extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	private float gravity[] = new float[3];
	private final float alpha = .8f;
	
	TextView xCoor; // declare X axis object
	TextView yCoor; // declare Y axis object
	TextView zCoor; // declare Z axis object\
	TextView xMax;
	TextView xMin;
	TextView yMax;
	TextView yMin;
	TextView zMax;
	TextView zMin;
	float xmin, xmax, ymin, ymax, zmin, zmax;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accelerometerlist);
		
		xCoor=(TextView)findViewById(R.id.xcoor); // create X axis object
		yCoor=(TextView)findViewById(R.id.ycoor); // create Y axis object
		zCoor=(TextView)findViewById(R.id.zcoor); // create Z axis object
		xMax = (TextView)findViewById(R.id.xMax); 
		xMin =(TextView)findViewById(R.id.xMin); 
		yMax=(TextView)findViewById(R.id.yMax); 
		yMin =(TextView)findViewById(R.id.yMin); 
		zMax=(TextView)findViewById(R.id.zMax); 
		zMin =(TextView)findViewById(R.id.zMin); 
		
		
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
		xmax = xmin = ymax = ymin = zmax = zmin = 0;
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

	        float x = event.values[0] - gravity[0];
	        float y = event.values[1] - gravity[1];
	        float z = event.values[2] - gravity[2];

			xCoor.setText("X: "+x);
			yCoor.setText("Y: "+y);
			zCoor.setText("Z: "+z);
			if (x> xmax)
			{
				xmax = x;
				xMax.setText("" + x);
			}
			else if (x < xmin)
			{
				xmin = x;
				xMin.setText("" + x);
			}
			
			if (y > ymax)
			{
				ymax = y;
				yMax.setText("" + y);
			}
			else if (y < ymin)
			{
				ymin = y;
				yMin.setText("" + y);
			}
			
			if (z> zmax)
			{
				zmax = z;
				zMax.setText("" + z);
			}
			else if (z < zmin)
			{
				zmin = z;
				zMin.setText("" + z);
			}
			
		}
	}
}