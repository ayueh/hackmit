package com.example.taketwo;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;

public class LineGraph {

	private GraphicalView view;
	
	private TimeSeries datasetx = new TimeSeries("x"); 
	private TimeSeries datasety = new TimeSeries("y"); 
	private TimeSeries datasetz = new TimeSeries("z"); 


	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
	
	private XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
	private XYSeriesRenderer renderer1 = new XYSeriesRenderer();
	private XYSeriesRenderer renderer2 = new XYSeriesRenderer();
	
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
	
	public LineGraph()
	{
		// Add single dataset to multiple dataset
		mDataset.addSeries(datasetx);
		mDataset.addSeries(datasety);
		mDataset.addSeries(datasetz);

		
		// Customization time for line 1!
		renderer.setColor(Color.BLUE);
		renderer.setPointStyle(PointStyle.SQUARE);
		renderer.setFillPoints(true);
		
		renderer1.setColor(Color.RED);
		renderer1.setPointStyle(PointStyle.SQUARE);
		renderer1.setFillPoints(true);
		
		renderer2.setColor(Color.GREEN);
		renderer2.setPointStyle(PointStyle.SQUARE);
		renderer2.setFillPoints(true);

		
		// Enable Zoom
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setXTitle("Time");
		mRenderer.setYTitle("Acceleration");
		
		// Add single renderer to multiple renderer
		mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer1);
		mRenderer.addSeriesRenderer(renderer2);
		
		//set y axis
		mRenderer.setYAxisMax(2);
		mRenderer.setYAxisMin(-2);
	}
	
	public GraphicalView getView(Context context) 
	{
		view =  ChartFactory.getLineChartView(context, mDataset, mRenderer);
		return view;
	}
	
	public void addNewPoints(Point p, Point p1, Point p2)
	{
		datasetx.add(p.getX(), p.getY());
		datasety.add(p1.getX(), p1.getY());
		//datasetz.add(p2.getX(), p2.getY());
		
	}
	
	public void setBounds(double time){
		double min = time - 20;
		if (min<0)
		{
			min=(double)(0);
		}
		mRenderer.setXAxisMin(min);
		mRenderer.setXAxisMax(time);

	}
	public void setBounds(double timeStart, double timeEnd){
		mRenderer.setXAxisMin(timeStart);
		mRenderer.setXAxisMax(timeEnd);
	}
	
}
