package fuamanager;

/**
 * @author rtpso
 *
 */
public class FuaCoordinate {
	String lat;
	String lon;
	//Coordinate[] coordinates;

	
	/**
	 * @param lat Latitude in DDM or decimal degrees
	 * @param lon Longitude in DDM or decimal degrees
	 */
	public FuaCoordinate(String lat, String lon) {
		this.lat=correctFuaCoordinate(lat, true);
		this.lon=correctFuaCoordinate(lon, false);
	}
	
	/**
	 * @param string Unparsed string straight from TopSkyAreas.txt in any of the possible formats supported by the plugin.
	 * Examples
	 * N059.54.15.000 E025.15.06.000
	 * N059.54.15.000:E025.15.06.000
	 * 59.904167:25.251667 maybe? who knows
	 */
	public FuaCoordinate(String string) {
		if(string.length() > 0 && !string.startsWith("//")) {
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
			
			lat = correctFuaCoordinate(lat, true);
			lon = correctFuaCoordinate(lon, false);
		}
		
	}

	/**
	 * Converts decimal degrees coordinates to DDM
	 * @param coord Coordinate to be converted
	 * @param isLatitude If the coordinate is a latitude or a longitude. Use true for latitude, false for longitude
	 * @return The coordinate in DDM format
	 */
	public String correctFuaCoordinate(String coord, boolean isLatitude) {
		
		int degrees;
		int minutes;
		int seconds;
		int miliseconds;
		
		String sDegrees;
		String sMinutes;
		String sSeconds;
		String sMiliseconds;
		String sign = "N";
		
		String[] split = coord.split("[\\.]");
		
		if(split.length == 2) {//decimal degrees time bois
			//59.904167
		
			degrees = Integer.parseInt(split[0]);
			//54.25002
			double tempMin = Double.parseDouble(split[1]) * 0.00006;
			minutes = (int)tempMin;
		
			double tempSeconds = tempMin - Double.valueOf(minutes) * 60;//0.25002
			seconds = (int)tempSeconds;
			
			miliseconds = (int) ((tempSeconds - seconds) * 1000);
		
			if(isLatitude) {
				if(degrees < 0) {
					sign = "S";
				}
			}
			else {
				if(degrees > 0) {
					sign = "E";
				}
				else {
					sign = "W";
				}
			}
			
			//N038.49.15.000 W008.53.20.000
			degrees = Math.abs(degrees);

			sDegrees = String.format("%3s", Integer.toString(degrees));
			sMinutes = String.format("%2s", Integer.toString(minutes));
			sSeconds = String.format("%2s", Integer.toString(seconds));
			sMiliseconds = String.format("%3s", Integer.toString(miliseconds));
			
			return sign+sDegrees+"."+sMinutes+"."+sSeconds+sMiliseconds;
		}
		return coord;//everything was fine already you dingus
	}
	
	public static String icaoToFuaCoordinate(String rawCoord) {
		String lat = null;
		String lon = null;
		
		if(rawCoord.contains("N")) {
			lat = "N0"+rawCoord.split("N")[0];
			lon = rawCoord.split("N")[1];
		}
		if(rawCoord.contains("S")) {
			lat = "S0"+rawCoord.split("S")[0];
			lon = rawCoord.split("S")[1];
		}
		
		lon = lon.subSequence(lon.length()-1, lon.length()) + lon;
		lon = lon.substring(0, lon.length()-1);
		
		lat = formatToFua(lat);
		lon = formatToFua(lon);
		
		return lat+" "+lon;
	}
	
	public static String formatToFua(String raw) {
		if(raw.length() < 7) {
			raw += "00.000";
		}
		
		raw = raw.substring(0, 4)+"."+raw.substring(4);
		raw = raw.substring(0, 7)+"."+raw.substring(7);
		
		return raw;
		
	}
	
	public static String formatToNotam(String raw) {
		raw = raw.replaceAll("\\.", "");
		raw = raw.substring(0, raw.length() - 5);
		
		String sign = raw.substring(0, 1);
		if(raw.contains("N") || raw.contains("S")) {
			raw = raw.substring(2, raw.length())+sign;
		}
		else {
			raw = raw.substring(1, raw.length())+sign;
		}
		
		
		return raw;
	}
	public String printNotam() {
		return formatToNotam(lat)+formatToNotam(lon);
	}
	
	public String printTopsky() {
		//N040.00.20.000 W010.39.16.000
		return lat + lon;
	}
	
}
