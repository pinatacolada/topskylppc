package fuamanager;

public class Coordinate {
	String lat;
	String lon;
	
	public Coordinate(String lat, String lon) {
		this.lat=lat;
		this.lon=lon;
	}
	
	public Coordinate(String string) {
		//N059.54.15.000 E025.15.06.000
		//N059.54.15.000:E025.15.06.000
		
		if(string.contains(":")) {
			String[] coord = string.split("[\\:]");
			lat = coord[0];
			lon = coord[1];
		}
		else {
			String[] coord = string.split("\\s+");//regex for whitespaces, or thats the intention
			lat = coord[0];
			lon = coord[1];
		}
		
		
	}
	
}
