package myVelibCore.utilities;


/**
 * <b>GPS coordinates</b>
 * <p>They are characterized by two Double parameters :
 * <ul>
 * <li>The latitude</li>
 * <li>The longitude</li>
 * </ul>
 * @author Simon Lecler
 *
 */
public class GPSLocation {
	/**
	 * The latitude (first GPS coordinate)
	 * <p>Can be changed
	 * @see GPSLocation#getLatitude()
	 * @see GPSLocation#setLongitude(double)
	 */
	private double latitude;
	/**
	 * The longitude (second GPS coordinate)
	 * <p> Can be changed
	 * @see GPSLocation#getLongitude()
	 * @see GPSLocation#setLongitude()
	 */
	private double longitude;
	
	//To compute distance with GPS Coordinates : https://geodesie.ign.fr/contenu/fichiers/Distance_longitude_latitude.pdf

	public GPSLocation() {
		super();
	}
	
	
	public static double getMaxLatitude(double sidearea) {
		// You input a maximum sidearea and the method return the maximum latitude possible to obtain if you consider a line coming from the point(0,0) 
		return 0;
		
		// A implémenter !!!
	}
	
	public static double getMaxLongitude(double sidearea) {
		// You input a maximum sidearea and the method return the maximum longitude possible to obtain if you consider a line coming from the point(0,0)
		return 0;
		
		// A implémenter !!!
	}
	
	public GPSLocation(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	

	/**
	 * 
	 * @param input
	 * 		Angle in degree
	 * @return Angle converted in radian
	 */
	public double convertRad(double input){
        return (Math.PI * input)/180;
	}	


/**
 * 
 * @param distantLocation
 * 		The GPS coordinates of the location 
 * @return The distance between the calling location and the location passes as a parameter
 */
	public double getDistance (GPSLocation distantLocation) {
        double R = 6378000; //Earth radius in meters		 
        double lat_a = convertRad(this.latitude);
        double lon_a = convertRad(this.longitude);
        double lat_b = convertRad(distantLocation.latitude);
        double lon_b = convertRad(distantLocation.longitude);
        double d = R * (Math.PI/2 - Math.asin( Math.sin(lat_b) * Math.sin(lat_a) + Math.cos(lon_b - lon_a) * Math.cos(lat_b) * Math.cos(lat_a)));
        return d;
	}

	@Override
public String toString() {
	return "GPSLocation [latitude=" + latitude + ", longitude=" + longitude + "]";
}

	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
