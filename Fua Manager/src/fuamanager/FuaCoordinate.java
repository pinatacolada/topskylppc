package fuamanager;

public class FuaCoordinate {
	String lat;
	String lon;
	Coordinate[] coordinates;
	
	
	public FuaCoordinate(String lat, String lon) {
		this.lat=lat;
		this.lon=lon;
	}
	
	public FuaCoordinate(String string) {
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
