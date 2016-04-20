package Entity;

import java.io.Serializable;

public class belowWellArea extends NASA_mars_Habitas implements Serializable{
	
    private static final long serialVersionUID = 1L;

	
	public belowWellArea(double temperature,double humidity, double oxygenLevel, double pressure, double smokeDetection, double gasDetection, String errorMessage) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.oxygenLevel = oxygenLevel;
		this.pressure = pressure;
		this.smokeDetection = smokeDetection;
		this.gasDetection = gasDetection;
		this.errorMessage = errorMessage;
		
	}
	
	@Override
	public String checkForTemperature(double temp) {
		// TODO Auto-generated method stub
		double var1 = Math.abs(temp - 35.0);
		double var2 = Math.abs(temp - 72.0);
		double per1 = var1 / 35.0;
		double per2 = var2 / 72.0;
		
		if (temp >= 35 && temp <= 72){
			return "NORMAL";
		}
		else if (temp < 35) {
			if (per1 <= 0.02) {
				return "NORMAL";
			}
			else if (per1 > 0.02 && per1 <= 0.05){
				return "WARNING";
			}
			else if (per1 > 0.05 && per1 <= 0.07){
				return "ERROR";
			}
			else {
				return "EMERGENCY";
			}
			
		}
		else {
			if (per2 <= 0.02) {
				return "NORMAL";
			}
			else if (per2 > 0.02 && per2 <= 0.05){
				return "WARNING";
			}
			else if (per2 > 0.05 && per2 <= 0.07){
				return "ERROR";
			}
			else {
				return "EMERGENCY";
			}	
		}	
	}
	
	@Override
	public String checkForHumidity(double hum) {
		
		double var1 = Math.abs(hum - 45.0);
		double var2 = Math.abs(hum - 75.0);
		double per1 = var1 / 45.0;
		double per2 = var2 / 75.0;
		
		if (hum >= 45 && hum <= 75){
			return "NORMAL";
		}
		else if (hum < 45) {
			if (per1 <= 0.02) {
				return "NORMAL";
			}
			else if (per1 > 0.02 && per1 <= 0.05){
				return "WARNING";
			}
			else if (per1 > 0.05 && per1 <= 0.07){
				return "ERROR";
			}
			else {
				return "EMERGENCY";
			}
			
		}
		else {
			if (per2 <= 0.02) {
				return "NORMAL";
			}
			else if (per2 > 0.02 && per2 <= 0.05){
				return "WARNING";
			}
			else if (per2 > 0.05 && per2 <= 0.07){
				return "ERROR";
			}
			else {
				return "EMERGENCY";
			}	
		}	
		
	}
	
	@Override
	public String checkForOxygenLevel(double oxygen) {
		double variation = Math.abs(oxygen - 21.0);
		double percent = variation / 21.0;

		if (percent <= 0.02) {
			return "NORMAL";
		} else if (percent > 0.02 && percent <= 0.05) {
			return "WARNING";
		} else if (percent > 0.05 && percent <= 0.07) {
			return "ERROR";
		} else {
			return "EMERGENCY";
		}
	}
	
	@Override
	public String checkForPressure(double pressure) {
		// TODO Auto-generated method stub
		double variation = Math.abs(pressure - 14.5);
		double percent = variation / 14.5;

		if (percent <= 0.02) {
			return "NORMAL";
		} else if (percent > 0.02 && percent <= 0.05) {
			return "WARNING";
		} else if (percent > 0.05 && percent <= 0.07) {
			return "ERROR";
		} else {
			return "EMERGENCY";
		}
	}
	
	@Override
	public String checkForSmoke(double smoke) {
		// TODO Auto-generated method stub
		if (smoke == 0.0) {
			return "NORMAL";
		}
		else {
			return "EMERGENCY";
		}
	}
	
	@Override
	public String checkForGas(double gas) {
		// TODO Auto-generated method stub
		if (gas == 0.0) {
			return "NORMAL";
		}
		else {
			return "EMERGENCY";
		}
	}






	

}
