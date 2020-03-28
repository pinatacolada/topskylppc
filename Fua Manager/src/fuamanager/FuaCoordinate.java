package fuamanager;

import org.locationtech.spatial4j.context.SpatialContextFactory;

public class FuaCoordinate {
	String lat;
	String lon;
	//Coordinate[] coordinates;

	
	public FuaCoordinate(String lat, String lon) {
		this.lat=lat;
		this.lon=lon;
	}
	
	public FuaCoordinate(String string) {
		//N059.54.15.000 E025.15.06.000
		//N059.54.15.000:E025.15.06.000
		//59.904167:25.251667 maybe? who knows
		
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
		
		convertCoord(lat, lon);

		
	}

	private String convertCoord(String lat, String lon) {
		// TODO Auto-generated method stub
		//FUCKING MESS
		String[] split = lat.split("[\\.]");
		
		if(split.length == 2) {
			//59.904167
			//.904167
			int degrees;
			int minutes;
			int seconds;
			String latSign;
			String lonSign;
			
			degrees = Integer.parseInt(split[0]);
			
			//54.25002
			double tempMin = Double.parseDouble(split[1]) * 0.00006;
			minutes = (int)tempMin;
			
			double tempSeconds = tempMin - Double.valueOf(minutes) * 60;//0.25002
			seconds = (int)tempSeconds;
			
			//N059.54.15.000
			if(degrees > 0) {
				lat = "N";
			}
			else {
				lat = "S";
			}
			return latSign+Integer.toString(degrees)+"."+Integer.toString(minutes)+"."+Integer.toString(seconds)+"000";
		}
		else {
			return split[0];
		}
	}
	
}
