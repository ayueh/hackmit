package com.example.taketwo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class TenValues extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	private double gravity[] = new double[3];
	private final double alpha = .8f;
	private double time = 0;
	private double x[] = new double[10];
	private double y[] = new double[10];
	private double z[] = new double[10];
	private int count;
	private TextView xMax;
	private int finalTime = 100;
	private double show[];
	private TextView z1, z2, z3, z4, z5, z6, z7, z8, z9, z10;
	private TextView xCoor; // declare X axis object

	
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ten_values);
		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		// add listener. The listener will be HelloAndroid (this) class
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		
		xCoor=(TextView)findViewById(R.id.xcoor); // create X axis object
		z1=(TextView)findViewById(R.id.z1);
		z2=(TextView)findViewById(R.id.z2);
		z3=(TextView)findViewById(R.id.z3);
		z4=(TextView)findViewById(R.id.z4);
		z5=(TextView)findViewById(R.id.z5);
		z6=(TextView)findViewById(R.id.z6);
		z7=(TextView)findViewById(R.id.z7);
		z8=(TextView)findViewById(R.id.z8);
		z9=(TextView)findViewById(R.id.z9);
		z10=(TextView)findViewById(R.id.z10);
		
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
	
		        x[count] = event.values[0] - gravity[0];
		        y[count] = event.values[1] - gravity[1];
		        z[count] = event.values[2] - gravity[2];
		        
		        xCoor.setText("X: "+x);
		        /*
		        if(x[count]>4){
		        	finalTime = count + 5;
		        }
		        if(count == finalTime)
		        {
		        	finalTime = 100;
		        	show = new concatenateArray(x, count).getValues();
		        	
		        	z1.setText("" + show[0]);
		        	z2.setText("" + show[1]);
		        	z3.setText("" + show[2]);
		        	z4.setText("" + show[3]);
		        	z5.setText("" + show[4]);
		        	z6.setText("" + show[5]);
		        	z7.setText("" + show[6]);
		        	z8.setText("" + show[7]);
		        	z9.setText("" + show[8]);
		        	z10.setText("" + show[9]);
		        	
		        	
		        }
		        count = count+1%100;
		        */
			}
	}

	
	public void onAccuracyChanged(Sensor sensor,int accuracy){
			
		}

}