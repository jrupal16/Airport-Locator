
package Homework3;


public class AirportBean
{

    public AirportBean(String airport, double latitude, double longitude)
    {
        this.airport = airport;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAirport()
    {
        return airport;
    }

    public void setAirport(String airport)
    {
        this.airport = airport;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    private String airport;
    private double latitude;
    private double longitude;
}

