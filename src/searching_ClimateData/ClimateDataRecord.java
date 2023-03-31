//Kathy Kanemoto
//3.12.23
//class to hold our record data for the climate change data

package searching_ClimateData;

public class ClimateDataRecord implements Comparable<ClimateDataRecord> {

    private String date;
    private double averageTemperature;
    private String averageTemperatureUncertainty;
    private String state;
    private String country;

    public ClimateDataRecord(String dataString) {
        String[] dataFields = dataString.split(",");
        date = dataFields[0];
        averageTemperature = dataFields[1].length() > 0 ? Double.parseDouble(dataFields[1]) : 0;
        averageTemperatureUncertainty = dataFields[2].length() > 0 ? dataFields[2] : "N/A";
        state = dataFields[3];
        country = dataFields[4];
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public String getAverageTemperatureUncertainty() {
        return averageTemperatureUncertainty;
    }

    public void setAverageTemperatureUncertainty(String averageTemperatureUncertainty) {
        this.averageTemperatureUncertainty = averageTemperatureUncertainty;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "TemperatureData{" +
                "date='" + date + '\'' +
                ", averageTemperature='" + averageTemperature + '\'' +
                ", averageTemperatureUncertainty='" + averageTemperatureUncertainty + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public int compareTo(ClimateDataRecord other) {
        //return this.date.compareTo(other.date);
    	//return this.country.compareTo(other.country);
    	if(averageTemperature == other.averageTemperature)
    		return 0;
    	if(averageTemperature > other.averageTemperature)
    		return 1;
    	return -1;
    }

}
