package Entity;

import java.io.Serializable;

public class externalWall extends NASA_mars_Habitas implements Serializable{
	
	
    private static final long serialVersionUID = 1L;

	
	private double externalWallTemp;
	private double outsideTemp;//only reported without alarms
	
	
	public externalWall(double externalWallTemp, double outsideTemp) {
		this.externalWallTemp = externalWallTemp;
		this.outsideTemp = outsideTemp;
		
	}
	public double getExternalWallTemp() {
		return externalWallTemp;
	}
	public void setExternalWallTemp(double externalWallTemp) {
		this.externalWallTemp = externalWallTemp;
	}
	public double getOutsiteTemp() {
		return outsideTemp;
	}
	public void setOutsiteTemp(double outsiteTemp) {
		this.outsideTemp = outsiteTemp;
	}
	
	@Override
	public String checkForTemperature(double temp) {
		// TODO Auto-generated method stub
		double variation = Math.abs(temp - 28.0);
		double percent = variation / 28.0;
		
		if (percent <= 0.02) {
			return "NORMAL";
		}
		else if (percent > 0.02 && percent <= 0.05){
			return "WARNING";
		}
		else if (percent > 0.05 && percent <= 0.07){
			return "ERROR";
		}
		else {
			return "EMERGENCY";
		}
	
	}
	
}
