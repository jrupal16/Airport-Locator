package Homework3;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.gjt.mm.mysql.Driver;

@WebServlet("/Homework3/AirController")

public class AirController extends HttpServlet {
    private static final long serialVersionUID = 1;
    @SuppressWarnings("unused")
	private double haversine(double lat1, double lon1, double lat2, double lon2) {
        double R = 6372.8;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double a = Math.sin(dLat / 2.0) * Math.sin(dLat / 2.0) + Math.sin(dLon / 2.0) * Math.sin(dLon / 2.0) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2.0 * Math.asin(Math.sqrt(a));
        double miles = 6372.8 * c * 0.62137;
        return miles;

    }

   

    @SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	String host = "cs3.calstatela.edu";
		String port = "3306";
		String dbName = "cs320stu46";
		String username = "cs320stu46";
		String password = "5ex!zk9.";
		
		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
		
		try{
			// Dynamically include the MySQL Driver
			Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			
			// Instantiate the Driver
			Driver driver = new org.gjt.mm.mysql.Driver();
			
			// Connect to the Database
			Connection connection = 
					DriverManager.getConnection(url, username, password);
			System.out.println("1");
			// Create a Statement
			Statement statement = connection.createStatement();
	            String query = "SELECT * FROM Airport";
            ResultSet rs = statement.executeQuery(query);
            ArrayList<AirportBean> airports = new ArrayList<AirportBean>();
            while (rs.next()) {
                String airport = rs.getString("Airport");
                double latitude = rs.getDouble("Latitude");
                double longitude = - rs.getDouble("Longitude");
                airports.add(new AirportBean(airport, latitude, longitude));
            }
            System.out.println("2");
            query = "SELECT * FROM Zipcode";
            rs = statement.executeQuery(query);
            ArrayList<ZipcodeBean> zc = new ArrayList<ZipcodeBean>();
            while (rs.next()) {
                int zip = rs.getInt("zip");
                String city = rs.getString("city");
                String state = rs.getString("state");
                double latitude = rs.getDouble("latitude");
                double longitude = rs.getDouble("longitude");
                int timezone = rs.getInt("timezone");
                int dst = rs.getInt("dst");
                zc.add(new ZipcodeBean(zip, city, state, latitude, longitude, timezone, dst));
            }
            connection.close();
            System.out.println("3");

            String param_zipcode = request.getParameter("zipcode");
            String param_radius = request.getParameter("radius");
            String param_city = request.getParameter("city");
            if (param_zipcode != null && param_radius != null) {
                int zipcode = Integer.parseInt(param_zipcode);
                int radius = Integer.parseInt(param_radius);
                ArrayList<AirportBean> results = new ArrayList<>();
                for (int i = 0; i < zc.size(); ++i) {
                    if (zipcode != ((ZipcodeBean)zc.get(i)).getZip()) continue;
                    double latitude = ((ZipcodeBean)zc.get(i)).getLatitude();
                    double longitude = ((ZipcodeBean)zc.get(i)).getLongitude();
                    for (int j = 0; j < airports.size(); ++j) {
                        double distance = this.haversine(latitude, longitude, ((AirportBean)airports.get(j)).getLatitude(), ((AirportBean)airports.get(j)).getLongitude());
                        if (distance > (double)radius)
                        	continue;
                        results.add(new AirportBean(((AirportBean)airports.get(j)).getAirport(),((AirportBean)airports.get(j)).getLatitude(),((AirportBean)airports.get(j)).getLongitude()));
                    }
                    break;
                }
                request.setAttribute("airports", results);
    			System.out.println("4");

            }
           
            if (param_city != null) {
                ArrayList<ZipcodeBean> results = new ArrayList<>();
                for (int i = 0; i < zc.size(); ++i) {
                    if (!param_city.equals(((ZipcodeBean)zc.get(i)).getCity())) continue;
                    results.add(new ZipcodeBean(((ZipcodeBean)zc.get(i)).getZip(),((ZipcodeBean)zc.get(i)).getCity(),((ZipcodeBean)zc.get(i)).getState(),
                  ((ZipcodeBean)zc.get(i)).getLatitude(),((ZipcodeBean)zc.get(i)).getLongitude(),((ZipcodeBean)zc.get(i)).getTimezone(),
                   ((ZipcodeBean)zc.get(i)).getDst()));
                }
                request.setAttribute("zc", results);
            }
			System.out.println("5");

            request.getRequestDispatcher("/WEB-INF/HW3/LocatorDisplay.jsp").forward((ServletRequest)request, (ServletResponse)response);
        }
        catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
		System.out.println("6");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    	System.out.println("7");

    }

    

}
