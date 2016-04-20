package Entity;

import java.io.Serializable;

public class equipStorageSpace extends NASA_mars_Habitas implements Serializable{
    private static final long serialVersionUID = 1L;

	
	public equipStorageSpace(double temperature,double humidity, double oxygenLevel, double pressure, double smokeDetection, double gasDetection) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.oxygenLevel = oxygenLevel;
		this.pressure = pressure;
		this.smokeDetection = smokeDetection;
		this.gasDetection = gasDetection;
		
	}
	@Override
	public String checkForTemperature(double temp) {
		// TODO Auto-generated method stub
		double variation = Math.abs(temp - 72.0);
		double percent = variation / 72.0;
		
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
	
	@Override
	public String checkForHumidity(double hum) {
		// TODO Auto-generated method stub
		double variation = Math.abs(hum - 45.0);
		double percent = variation / 45.0;

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
