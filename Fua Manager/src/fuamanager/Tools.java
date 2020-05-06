package fuamanager;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class Tools {

	public static LocalDateTime getSunrise() {
		Calendar[] sunriseSunset = ca.rmen.sunrisesunset.SunriseSunset.getSunriseSunset(Calendar.getInstance() , 39.199445, -8.229450);
		LocalDateTime sr = sunriseSunset[0].toInstant().atZone(ZoneId.of("Z")).toLocalDateTime();
		
		return sr;
		
	}
	
	public static LocalDateTime getSunset() {
		Calendar[] sunriseSunset = ca.rmen.sunrisesunset.SunriseSunset.getSunriseSunset(Calendar.getInstance() , 39.199445, -8.229450);
		LocalDateTime sr = sunriseSunset[1].toInstant().atZone(ZoneId.of("Z")).toLocalDateTime();
		
		return sr;
		
	}
}
