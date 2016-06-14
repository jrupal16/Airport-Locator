package Homework3;


public class ZipcodeBean
{
	private int zip;
    private String city;
    private String state;
    private double latitude;
    private double longitude;
    private int timezone;
    private int dst;
    
    public ZipcodeBean(int zip, String city, String state, double latitude, double longitude, 
            int timezone, int dst)
    {
        this.zip = zip;
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.dst = dst;
    }

    public int getZip()
    {
        return zip;
    }

    public void setZip(int zip)
    {
        this.zip = zip;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
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

    public int getTimezone()
    {
        return timezone;
    }

    public void setTimezone(int timezone)
    {
        this.timezone = timezone;
    }

    public int getDst()
    {
        return dst;
    }

    public void setDst(int dst)
    {
        this.dst = dst;
    }

    
}
