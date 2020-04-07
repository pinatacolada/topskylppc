package fuamanager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SchedAct extends Activation {
	//ACTIVE:SchedStartDate:SchedEndDate:SchedWeekdays:StartTime:EndTime[:Lower:Upper:UserText]
	private LocalDateTime schedStartDate;
	private LocalDateTime schedEndDate;
	private String schedWeekdays;
	private int lower;
	private int upper;
	private String userText;
	
	public SchedAct(String[] parts) {
		//ACTIVE:190101:201231:12345:0700:1630
		rules = "";
		
		schedStartDate = ParseDate(parts[1],parts[4]);
		schedEndDate = ParseDate(parts[2],parts[5]);
		schedWeekdays = parts[3];
		
		if(parts.length > 6) {
			lower = Integer.parseInt(parts[6]);
			upper = Integer.parseInt(parts[7]);
			userText = parts[8];
		}
	}
	
	public SchedAct(LocalDateTime schedStartDate, LocalDateTime schedEndDate, String schedWeekdays, int lower, int upper, String userText) {
		rules = "";
		
		this.schedStartDate=schedStartDate;
		this.schedEndDate=schedEndDate;
		this.schedWeekdays=schedWeekdays;
		this.lower=lower;
		this.upper=upper;
		this.userText=userText;
	}
	
	public static LocalDateTime ParseDate(String day, String hour) {
		LocalDate date = null;
		LocalTime time = LocalTime.parse(hour, DateTimeFormatter.ofPattern("HHmm"));
		
		if(day.length() == 6){
			date = LocalDate.parse(day, DateTimeFormatter.ofPattern("yyMMdd"));
		}
		else if(day.length() == 4){
			LocalDate currYear = LocalDate.now();
			
			String sYear = Integer.toString(currYear.getYear()).substring(2);
			date = LocalDate.parse(sYear+day, DateTimeFormatter.ofPattern("yyMMdd"));
		}

		LocalDateTime dateTime = date.atTime(time);	
	
		return dateTime;
	}
}
