package com.example.vibe;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	private double gravity[] = new double[3];
	private final double alpha = .8;
	private int count;
	
	private double x[];
	private double y[];
	private double z[];
	private double magnitude[];
	
	public double xKeyPress[];
	public double yKeyPress[];
	public double zKeyPress[];
	public double magnitudeKeyPress[];
	
	private double max = 0;
	
	private boolean calibrate = false;
	private boolean acceptKeyPress = true;
	
	private String debug;
	private int timeEnd = 7;
	
	TextView xCoor; // declare X axis object
	
	TextView yCoor; // declare Y axis object
	TextView zCoor; // declare Z axis object
	TextView mag;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		x = new double[7];
		y = new double[7];
		z = new double[7];
		magnitude = new double[7];
		
		xKeyPress = new double[7];
		yKeyPress = new double[7];
		zKeyPress = new double[7];
		magnitudeKeyPress = new double[7];
		
		xCoor=(TextView)findViewById(R.id.xcoor); // create X axis object
		
		yCoor=(TextView)findViewById(R.id.ycoor); // create Y axis object
		zCoor=(TextView)findViewById(R.id.zcoor); // create Z axis object
		mag = (TextView)findViewById(R.id.mag);
		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_FASTEST);
		
		/*	More sensor speeds (taken from api docs)
		    SENSOR_DELAY_FASTEST get sensor data as fast as possible
		    SENSOR_DELAY_GAME	rate suitable for games
		 	SENSOR_DELAY_NORMAL	rate (default) suitable for screen orientation changes
		*/
	}

	public void onAccuracyChanged(Sensor sensor,int accuracy){
		
	}
	
	public void onSensorChanged(SensorEvent event){
		
		// check sensor type
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			
			// assign directions
			gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
	        gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
	        gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

	        x[count] = event.values[0] - gravity[0];
	        
	        //if (Math.abs(x[count]) < .01){x[count] = 0;}
	        
	        y[count] = event.values[1] - gravity[1];
	        z[count] = event.values[2] - gravity[2];
	        magnitude[count] = Math.sqrt(Math.pow(x[count],  2) + Math.pow(y[count],  2) + Math.pow(z[count],  2));
	        
	        if (magnitude[count] > .3 && acceptKeyPress)
	        {
	        	timeEnd = (count + 3)%7;
	        	max = magnitude[count];
	        	magnitude[count] = 99999999;
	        	acceptKeyPress = false;
	        }
	        if (count == timeEnd)
	        {
	        	xKeyPress = new concatenateArray(x, (count-3+7)%7).getValues();
	        	yKeyPress = new concatenateArray(y, (count-3+7)%7).getValues();
	        	zKeyPress = new concatenateArray(z, (count-3+7)%7).getValues();
	        	magnitudeKeyPress = new concatenateArray(magnitude, (count+1)%6).getValues();
	        	timeEnd = 7;
	        	debug = "\n";
	        	for (int i = 0; i<7; i++)
	        	{
	        		debug += Double.toString(xKeyPress[i]) + "\n";
	        	}
	        	debug += "--------------------------------------\n";
	        	for (int i = 0; i<7; i++)
	        	{
	        		debug += Double.toString(yKeyPress[i]) + "\n";
	        	}
	        	debug += "---------------------------------------\n";
	        	for (int i = 0; i<7; i++)
	        	{
	        		debug += Double.toString(zKeyPress[i]) + "\n";
	        	}
	        	acceptKeyPress = true;
	        }
	        mag.setText("Mag: " + magnitudeKeyPress[count]);
			xCoor.setText(debug);

			
			//yCoor.setText("Y: "+y[count]);
			//zCoor.setText("Z: "+z[count]);
			count = (count + 1) % 7;
		}
	}
	public void calibrate()
	{
		calibrate = true;
	}
	public void showX(View view)
	{
		for (int i = 0; i<6; i++)
    	{
    		debug += Double.toString(xKeyPress[i]) + "\n";
    		xCoor.setText(debug);
    		debug = "";
    	}
	}
	public void showY(View view)
	{
		for (int i = 0; i<6; i++)
    	{
    		debug += Double.toString(yKeyPress[i]) + "\n";
    		xCoor.setText(debug);
    		debug = "";
    	}
	}
	public void showZ(View view)
	{
		for (int i = 0; i<6; i++)
    	{
    		debug += Double.toString(zKeyPress[i]) + "\n";
    		xCoor.setText(debug);
    		debug = "";
    	}
	}
	
}