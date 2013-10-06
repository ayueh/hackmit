package keydetector;

import java.util.Arrays;

public class letterDict{
	
	private letter letters[];
	private boolean full;
	private int lettersInDict;
	private double squaresumX;
	private double squaresumY;
	private double squaresumZ;
	private double diffX;
	private double diffY;
	private double diffZ;
	private double currentXMins[] = new double[3];
	private double currentYMins[] = new double[3];
	private double currentZMins[] = new double[3];
	private letter currentTopX[] = new letter[3];
	private letter currentTopY[] = new letter[3];
	private letter currentTopZ[] = new letter[3];
	private letter topChoices[] = new letter[5];
	
	public letterDict(){
		letters = new letter[28];
	}
	
	public void insert(letter input, int ind)
	{
		letters[ind] = input;
		lettersInDict++;
		if (lettersInDict==28)
		{
			full = true;
		}
	}
				
	public letter[] compare(double xVals[], double yVals[], double zVals[]){
		if (!full)
		{
			//error
		}
		else
		{
		for (int i=0; i<28; i++){
			squaresumX = 0;
			squaresumY = 0;
			squaresumZ = 0;
			for (int j=0; j<xVals.length; j++){
				squaresumX += Math.pow(xVals[j]-letters[i].getX()[j], 2.0);
				squaresumY += Math.pow(yVals[j]-letters[i].getY()[j], 2.0);
				squaresumZ += Math.pow(zVals[j]-letters[i].getZ()[j], 2.0);
			};
			diffX = Math.sqrt(squaresumX);
			diffY = Math.sqrt(squaresumY);
			diffZ = Math.sqrt(squaresumZ);
			for (int k=0; k<3; k++){
				if (diffX < currentXMins[k]){
					currentXMins[k] = diffX;
					currentTopX[k] = letters[k];
				}
			for (int l=0; l<3; l++){
				if (diffY < currentYMins[l]){
					currentYMins[l] = diffY;
					currentTopY[l] = letters[l];
				}
			}
			for (int m=0; m<3; m++){
				if (diffZ < currentZMins[m]){
					currentZMins[m] = diffZ;
					currentTopZ[m] = letters[m];
				}
			}
			for (int n=0; n<currentTopX.length; n++){
				if (Arrays.asList(currentTopY).contains(currentTopX[n]) && Arrays.asList(currentTopZ).contains(currentTopX[n])){
					topChoices[n] = currentTopX[n];
				}
			}
			}
		}
		}
		return topChoices;
	}
}
