package keydetector;

public class letter {
	private double xVals[] = new double[10];
	private double yVals[] = new double[10];
	private double zVals[] = new double[10];
	
	public letter(double xInfo[], double yInfo[], double zInfo[]){
		xVals = xInfo;
		yVals = yInfo;
		zVals = zInfo;
	}
	
	public void printX(){
		for (int i=0; i<10; i++){
			System.out.printf("%f, ", xVals[i]);
		}
	}
	
	public void printY(){
		for (int i=0; i<10; i++){
			System.out.printf("%f, ", yVals[i]);
		}
	}
	
	public void printZ(){
		for (int i=0; i<10; i++){
			System.out.printf("%f, ", zVals[i]);
		}
	}
		
	public double[] getX(){
		return xVals;
	}
	
	public double[] getY(){
		return yVals;
	}
	
	public double[] getZ(){
		return zVals;
	}

}