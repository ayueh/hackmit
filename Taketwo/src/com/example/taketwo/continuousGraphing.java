package com.example.taketwo;

import org.achartengine.GraphicalView;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class continuousGraphing extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	private double gravity[] = new double[3];
	private final double alpha = .8f;
	private double time = 0;
	private double x;
	private double y;
	private double z;
	
	private static GraphicalView view;
	private LineGraph line = new LineGraph();
	private static Thread thread;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamicgraph);
		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		thread = new Thread() {
			public void run()
			{
				while (true) 
				{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Point p = new Point(time, x); // We got new data!
					line.addNewPoints(new Point(time, x), new Point(time, y), new Point(time, z));
					// line.setBounds(time);	only want to get when there is a finger press, not an update every time
					
					 // Add it to our graph
					line.setBounds(time - 2, time + 2);

	
						view.repaint();
					time += .1;
					//view.repaint();	only want to get when there is a finger tap, not an update at every iteration
				}
			}
		};
		thread.start();
	}
	public void onSensorChanged(SensorEvent event){
			
			// check sensor type
			if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
				
				// assign directions
				//double x=event.values[0];
				//double y=event.values[1];
				//double z=event.values[2];
				
	
		          gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
		          gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
		          gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];
	
		        x = event.values[0] - gravity[0];
		        y = event.values[1] - gravity[1];
		        z = event.values[2] - gravity[2];
			}
	}

	@Override
	protected void onStart() {
		super.onStart();
		view = line.getView(this);
		setContentView(view);
	}
	
	public void onClick()
	{
		
	}
	
	public void onAccuracyChanged(Sensor sensor,int accuracy){
			
		}

}