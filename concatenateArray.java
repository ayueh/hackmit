package keydetector;


public class concatenateArray{
	private double xArray[];
	
	public concatenateArray(double xData[], int index){
		xArray = new double[xData.length];
		System.arraycopy(xData, index, xArray, 0, xData.length-index);
		System.arraycopy(xData, 0, xArray, xData.length-index, index);
	}
	
	public void printVals(){
		for(int i = 0; i<(xArray.length); i++)
		{
			System.out.printf("%f, ", xArray[i]);
		}
	}
	
	public double[] getValues(){
		return xArray;
	}
}