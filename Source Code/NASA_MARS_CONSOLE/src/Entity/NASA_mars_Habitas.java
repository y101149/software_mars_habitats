package Entity;

import java.io.Serializable;

public class NASA_mars_Habitas implements Serializable {
	
	protected double temperature;
	protected double humidity;
	protected double oxygenLevel;
	protected double pressure;
	protected double smokeDetection;
	protected double gasDetection;
	protected String errorMessage = "NORMAL";
	
    private static final long serialVersionUID = 1L;

	public String checkForTemperature(double temp){
		
		return "NORMAL";
	}
	
	public String checkForHumidity (double hum) {
		return "NORMAL";
	}
	
	public String checkForOxygenLevel (double oxygen){
		return "Normal";
	}
	
	public String checkForPressure (double pressure) {
		return "Normal";
	}
	public String checkForSmoke (double smoke) {
		return "Normal";
	}
	public String checkForGas(double gas) {
		return "Normal";
	}
	
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public double getHumidity() {
		return humidity;
	}
	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	public double getOxygenLevel() {
		return oxygenLevel;
	}
	public void setOxygenLevel(double oxygenLevel) {
		this.oxygenLevel = oxygenLevel;
	}
	public double getPressure() {
		return pressure;
	}
	public void setPressure(double pressure) {
		this.pressure = pressure;
	}
	public double getSmokeDetection() {
		return smokeDetection;
	}
	public void setSmokeDetection(double smokeDetection) {
		this.smokeDetection = smokeDetection;
	}
	public double getGasDetection() {
		return gasDetection;
	}
	public void setGasDetection(double gasDetection) {
		this.gasDetection = gasDetection;
	}
	
	
	

}
