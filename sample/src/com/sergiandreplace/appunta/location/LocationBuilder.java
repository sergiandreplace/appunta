package com.sergiandreplace.appunta.location;

import android.location.Location;

public class LocationBuilder {
	public static Location createLocation (double latitude, double longitude, double altitude) {
		Location l=new Location("");
		l.setLatitude(latitude);
		l.setLongitude(longitude);
		l.setAltitude(altitude);
		l.setAccuracy(0);
		return l;
		
	}
	
	public static Location createLocation (double latitude, double longitude) {
		return createLocation(latitude, longitude,0);
		
	}
}
